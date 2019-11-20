package es.ucm.gdv.motorandroid; //Igual debería ser es.ucm.gdv.interfaces.motorandroid

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Input;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.logica.Logica;

/*
* Clase que recubre
* */
public class GameAndroid extends SurfaceView implements Game, Runnable {

    //Referencias para el patrón Singleton
    private GraphicsAndroid _graphicsAndroid;
    private InputAndroid _inputAndroid;
    private Logica _currentGameState;

    //SurfaceView _surfaceView;

    //Para el update
    long lastFrameTime = System.nanoTime();
    long currentTime, nanoElapsedTime;

    //Para el hilo
    private volatile boolean _running; //Volatile hace que no revise en memoria
    private Thread _runningThread;     //Hilo de juego

    public GameAndroid(Activity activity, Context context){
        super(activity);

        activity.setContentView(this);
        _graphicsAndroid = new GraphicsAndroid(this, context.getAssets());
        _inputAndroid = new InputAndroid();

       setOnTouchListener(_inputAndroid);
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
        while (_graphicsAndroid.getWidth()<=0);
        _currentGameState = new Logica();
        _currentGameState.init(this);
        while (_running){
            SurfaceHolder sh = getHolder();
            _currentGameState.tick(CalculaDeltaTime());
            while (!sh.getSurface().isValid());
            CanvasManagePaint(sh);
        }
    }

    private void CanvasManagePaint(SurfaceHolder sh) {
        Canvas c = sh.lockHardwareCanvas();
        _graphicsAndroid.startFrame(c);
        _currentGameState.clear();
        _graphicsAndroid.setCanvasSize();
        _graphicsAndroid.DrawRect(_graphicsAndroid.getCanvas());
        _graphicsAndroid.DrawRect(new Rect(0,0,100,100));

        _currentGameState.render();
        sh.unlockCanvasAndPost(c);
    }

    private double CalculaDeltaTime(){
        currentTime = System.nanoTime();
        nanoElapsedTime = currentTime - lastFrameTime;
        lastFrameTime = currentTime;
        return (double) nanoElapsedTime / 1.0e9;
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
    public AbstractGraphics getGraphics() {
        return _graphicsAndroid;
    }

    @Override
    public Input getInput() {
        return _inputAndroid;
    }



}
