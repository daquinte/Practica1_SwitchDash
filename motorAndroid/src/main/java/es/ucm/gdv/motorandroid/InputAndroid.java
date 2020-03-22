package es.ucm.gdv.motorandroid;

import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import es.ucm.gdv.interfaces.Input;
import es.ucm.gdv.interfaces.TouchEvent;

public class InputAndroid implements Input, View.OnTouchListener{

    //Atributos
    private LinkedList<TouchEvent> inputList;       //Linked list porque es más rapido añadir/borrar


    InputAndroid(){
        inputList = new LinkedList<>();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        LinkedList<TouchEvent> auxIL;
        synchronized (this) {
            auxIL = new LinkedList<>(inputList);
            Clear();

        }
        return auxIL;
    }

    @Override
    public synchronized void Clear() {
        inputList.clear();
    }

    /*
    * Registra input en Android. Si devuelve true, se ha producido y almacenado un evento en la lista.
    * */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TouchEvent touchEvent;
        boolean tocado = false;
        switch (event.getAction()){
            //CLICK
            case MotionEvent.ACTION_DOWN:
                touchEvent = new TouchEvent((int)event.getX(), (int)event.getY(),
                        TouchEvent.TouchType.click, event.getActionIndex());
                synchronized (this){
                    inputList.add((touchEvent));
                }
                tocado = true;
                break;
            //RELEASE
            case MotionEvent.ACTION_UP:
                touchEvent = new TouchEvent((int)event.getX(), (int)event.getY(),
                        TouchEvent.TouchType.release, event.getActionIndex());
                synchronized (this){
                    inputList.add((touchEvent));
                }
                tocado = true;
                break;
        }
        return tocado;
    }
}
