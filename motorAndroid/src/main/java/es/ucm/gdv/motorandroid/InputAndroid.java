package es.ucm.gdv.motorandroid;

import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Input;
import es.ucm.gdv.interfaces.TouchEvent;

public class InputAndroid implements Input, View.OnTouchListener{

    //Atributos
    private LinkedList<TouchEvent> inputList;       //Linked list porque es más rapido añadir/borrar
    private Graphics _graphics;

    InputAndroid(Graphics graphics){
        _graphics = graphics;
        inputList = new LinkedList<>();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        LinkedList<TouchEvent> auxIL;
        synchronized (this) {
            auxIL = new LinkedList<>(inputList);
            synchronized (this) {
                inputList.clear();
            }
        }
        return auxIL;
    }

    /*
    * Registra input en Android. Si devuelve true, se ha producido y almacenado un evento en la lista.
    * */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TouchEvent touchEvent;
        boolean tocado = false;
        TouchEvent.TouchType touchType = null;
        switch (event.getAction()){
            //CLICK
            case MotionEvent.ACTION_DOWN:
                touchType = TouchEvent.TouchType.click;
                break;
            //RELEASE
            case MotionEvent.ACTION_UP:
                touchType = TouchEvent.TouchType.release;
                break;
        }
        if (touchType != null) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (_graphics.isInCanvas(x, y)) {
                x = _graphics.revertCoordinateX(x - _graphics.getRectCanvas().x);
                y = _graphics.revertCoordinateY(y -  _graphics.getRectCanvas().y);
            }
            touchEvent = new TouchEvent(x, y, touchType,
                    event.getActionIndex());
            synchronized (this) {
                inputList.add((touchEvent));
            }
            tocado = true;
        }
        return tocado;
    }
}
