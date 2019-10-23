package es.ucm.gdv.motorandroid; //Igual debería ser es.ucm.gdv.interfaces.motorandroid

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Input;

public class GameAndroid implements Game, Runnable {

    //Referencias para el patrón Singleton
    @Getter
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

    public void Init(){

    }

    @Override
    public Graphics GetGraphics() {
        return _graphicsAndroid;
    }

    @Override
    public Input GetInput() {
        return _inputAndroid;
    }


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

    //Runnable, para tener un hilo con el bucle ppal
    @Override
    public void run() {

        //Espera a que se inicialice el surfaceView
        while (_graphicsAndroid.getWidth()<=0);
        while (_running){
            SurfaceHolder sh = _surfaceView.getHolder();
            //_logicaJuego.tick(CalculaDeltaTime());
            while (!sh.getSurface().isValid());
            CanvasManagePaint(sh);
        }
    }

    private void CanvasManagePaint(SurfaceHolder sh) {
        Canvas c = sh.lockCanvas();
        _graphicsAndroid.startFrame(c);
        _graphicsAndroid.clear(0xFF000000);
        Image test = _graphicsAndroid.newImage("Java-logo.png");
        _graphicsAndroid.drawImage(test, 100, 100);
        sh.unlockCanvasAndPost(c);
    }

    private double CalculaDeltaTime(){
        currentTime = System.nanoTime();
        nanoElapsedTime = currentTime - lastFrameTime;
        lastFrameTime = currentTime;
        return (double) nanoElapsedTime / 1.0e9;
    }
}
