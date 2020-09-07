package es.ucm.gdv.motorpc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Input;
import es.ucm.gdv.interfaces.TouchEvent;

public class InputPC implements Input, MouseListener {

    //Lista de TouchEvents
    private LinkedList<TouchEvent> inputList;
    private Graphics _graphics;

    InputPC(JFrame jFrame, Graphics graphics) {
        _graphics = graphics;
        inputList = new LinkedList<>();      //Linked list porque es más rapido añadir/borrar
        jFrame.addMouseListener(this);
    }

    /**
     * Método que devuelve los métodos encolados.
     * Sincronized porque los vas a pedir desde la hebra.
     */
    @Override
    public synchronized List<TouchEvent> getTouchEvents() {
        List<TouchEvent> aux;
        synchronized (this) {
            aux = new ArrayList<>(inputList);
            synchronized (this) {
                inputList.clear();
            }
        }
        return aux;
    }

    //-----------------
    //From MouseListener
    //------------------
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (_graphics.isInCanvas(x, y)) {
            x = _graphics.revertCoordinateX(x - _graphics.getRectCanvas().x);
            y = _graphics.revertCoordinateY(y - _graphics.getRectCanvas().y);
        }
        TouchEvent touchEvent = new TouchEvent(x, y, TouchEvent.TouchType.click,
                mouseEvent.getID());
        synchronized (this) {
            inputList.add((touchEvent));
        }
    }

    /**
     * Añade, de forma sincronizada, un evento de pulsación
     * *
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        TouchEvent touchEvent = new TouchEvent(x, y, TouchEvent.TouchType.drag,
                mouseEvent.getID());
        synchronized (this) {
            inputList.add((touchEvent));
        }
    }

    /**
     * Añade, de forma sincronizada, un evento de soltar el dedo.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent touchEvent = new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), TouchEvent.TouchType.release, mouseEvent.getID());
        synchronized (this) {
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
