package es.ucm.gdv.interfaces;

public class TouchEvent {
    public enum TouchType {
        click, release, drag
    }

    private int _x;
    private int _y;
    private int _inputID;
    private TouchType _touchType;

    public TouchEvent(int x, int y, TouchType touchType, int inputID) {
        _x = x;
        _y = y;
        _touchType = touchType;
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

    public TouchType getTouchType() {
        return _touchType;
    }
}


