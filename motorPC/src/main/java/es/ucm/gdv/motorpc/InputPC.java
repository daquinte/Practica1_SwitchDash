package es.ucm.gdv.motorpc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import es.ucm.gdv.interfaces.Input;
import  es.ucm.gdv.interfaces.TouchEvent;
public class InputPC implements Input, MouseListener {

    //Lista de TouchEvents
    List <TouchEvent> inputList;

    public InputPC(JFrame jFrame){
        inputList = new ArrayList<>();
        jFrame.addMouseListener(this);
    }

    /**
     * Método que devuelve los métodos encolados.
     * Sincronized porque los vas a pedir desde la hebra.
     * */
    @Override
    public synchronized List<TouchEvent> getTouchEvents() {
        List<TouchEvent> aux;
        synchronized (this) {
            aux = new ArrayList<TouchEvent>(inputList);
            Clear();
        }
        return aux;
    }

    /**
     * Limpia la cola de comandos.
     * Sincronized porque se tiene que limpiar la cola cuando la hebra no lo use.
     * */
    @Override
    public synchronized void Clear() {
            inputList.clear();
    }

    //-----------------
    //From MouseListener
    //------------------
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //Nada para que no se haga dos veces
    }

    /**
     * Añade, de forma sincronizada, un evento de pulsación
     * * */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent touchEvent = new TouchEvent(mouseEvent, TouchEvent.TouchType.click);
        synchronized (this){
            inputList.add((touchEvent));
        }
    }

    /**
     *Añade, de forma sincronizada, un evento de soltar el dedo.
     * */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent touchEvent = new TouchEvent(mouseEvent, TouchEvent.TouchType.release);
        synchronized (this){
            inputList.add((touchEvent));
        }
    }

    // -- Métodos que no vamos a usar, probablemente --
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        //No se usa
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        //No se usa
    }
}
