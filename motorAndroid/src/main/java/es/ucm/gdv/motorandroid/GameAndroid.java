package es.ucm.gdv.motorandroid; //Igual debería ser es.ucm.gdv.interfaces.motorandroid

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Input;

public class GameAndroid implements Game, Runnable {

    //Referencias para el patrón Singleton
    private GraphicsAndroid _graphicsAndroid;
    private InputAndroid _inputAndroid;

    SurfaceView _surfaceView;

    //Para el update
    long lastFrameTime = System.nanoTime();
    long currentTime, nanoElapsedTime;

    //Para el hilo
    private volatile boolean _running; //Volatile hace que no revise en memoria
    private Thread _runningThread;     //Hilo de juego

    public GameAndroid(Activity a, Context context){
         _surfaceView = new SurfaceView(context);
         a.setContentView(_surfaceView);
        _graphicsAndroid = new GraphicsAndroid(_surfaceView, context.getAssets());
        _inputAndroid = new InputAndroid();

        //surfaceView.setOnTouchListener(_inputAndroid);
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
            throw new RuntimeException("run() should not be called directly!");
        }

        //Espera a que se inicialice el surfaceView
        while (_graphicsAndroid.getWidth()<=0);
        while (_running){
            SurfaceHolder sh = _surfaceView.getHolder();
            //_logicaJuego.tick(CalculaDeltaTime());
            while (!sh.getSurface().isValid());
            CanvasManagePaint(sh); //TODO: pasar esto a que use Sprite y tal
        }
    }

    private void CanvasManagePaint(SurfaceHolder sh) {
        Canvas c = sh.lockHardwareCanvas();
        _graphicsAndroid.startFrame(c);
        //_logicaJuego.render();
        _graphicsAndroid.clear(0xFF000000);
        Image test = _graphicsAndroid.newImage("howToPlay.png");
        _graphicsAndroid.drawImage(test, 100, 100);
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
    public Graphics getGraphics() {
        return _graphicsAndroid;
    }

    @Override
    public Input getInput() {
        return _inputAndroid;
    }



}
