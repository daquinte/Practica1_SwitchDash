package es.ucm.gdv.interfaces;

public class TouchEvent {
    public enum TouchType {
        click, release, drag
    }

    private int _x;
    private int _y;
    private int _inputID;
    private TouchType _touchEvent;

    public TouchEvent(int x, int y, TouchType touchEvent, int inputID) {
        _x = x;
        _y = y;
        _touchEvent = touchEvent;
        _inputID = inputID;
    }

    public int get_x() {
        return _x;
    }
    public int get_y() {
        return _y;
    }

    public int getInputID() {
        return _inputID;
    }

    public TouchType get_touchEvent() {
        return _touchEvent;
    }
}


