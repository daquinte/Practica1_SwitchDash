package es.ucm.gdv.motorandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Input;

public class GameAndroid implements Game, Runnable {

    //Referencias para el patrón Singleton
    private GraphicsAndroid _graphicsAndroid;
    private InputAndroid _inputAndroid;

    private GameState _currentGameState;
    private GameState _nextGameState;

    private SurfaceView _surfaceView;

    //Para el hilo
    private volatile boolean _running; //Volatile hace que no revise en memoria
    private Thread _runningThread;     //Hilo de juego

    public GameAndroid(Activity activity, Context context){
        _surfaceView = new SurfaceView(activity);
        activity.setContentView(_surfaceView);
        _graphicsAndroid = new GraphicsAndroid(_surfaceView, context.getAssets());
        _inputAndroid = new InputAndroid(_graphicsAndroid);
        _surfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        _surfaceView.setOnTouchListener(_inputAndroid);

    }

    /**
     * Pausa el hilo de ejecución
     * */
    public void Pause(){
        _running = false;
        while (true) {
            //Tenemos que parar el hilo bien, antes de empezarlo de nuevo
            try {
                _runningThread.join();
                break;
            } catch (InterruptedException ie) {
                System.err.println("interrupcion por excepcion");
            }
        }
    }

    public void Resume(){
        if(!_running){
            _running=true;
            _runningThread = new Thread(this);
            _runningThread.start();
        }
    }

    //-----------------------------------------------
    //                  Ciclo de juego
    //-----------------------------------------------

    //Runnable, para tener un hilo con el bucle ppal
    @Override
    public void run() {

        if (_runningThread != Thread.currentThread()) {
            throw new RuntimeException("run() should not be called directly in Android!");
        }

        //Espera a que se inicialice el surfaceView
        while(_graphicsAndroid.getWidth() <= 0);


        long lastFrameTime = System.nanoTime();
        while (_running){
            SurfaceHolder sh = _surfaceView.getHolder();

            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            if (_nextGameState != null) {
                _currentGameState = _nextGameState;
                _nextGameState = null;
                _currentGameState.init(this);
            }
            _currentGameState.tick(elapsedTime);
            while(!sh.getSurface().isValid());
            CanvasManagePaint(sh);
        }
    }

    //Métodos de render que vamos a llamar en el bucle ppal
    private void CanvasManagePaint(SurfaceHolder sh) {
        Canvas c = sh.lockHardwareCanvas();
        _graphicsAndroid.startFrame(c);
        _graphicsAndroid.updateCanvasSize();
        _currentGameState.clear();

        _currentGameState.render();
        sh.unlockCanvasAndPost(c);
    }

    @Override
    public Game getGame() {
        return null;
    }

    //-----------------------------------------------
    //                   Getters
    //-----------------------------------------------
    //From Game Interface
    @Override
    public Graphics getGraphics() {
        return _graphicsAndroid;
    }

    @Override
    public Input getInput() {
        return _inputAndroid;
    }

    @Override
    public void setGameState(GameState gameState) {
        _nextGameState = gameState;
    }
}
