package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Rect;

/*
* Clase abstracta que contiene
* los métodos que son comunes a la implementacion de android y de pc.
* Concetramente, define el escalado del canvas físico donde se va a jugar
* y tiene funcionalidades para pasar de coordenadas logicas a fisicas.
* */
public abstract class AbstractGraphics {

    private Rect _canvas;

    private final int _referenceCanvasWidth = 1080;
    private final int _referenceCanvasHeight = 1920;

    /*Pasa coordenadas lógicas en la resolucion base a coordenadas físicas con nuestra resolución.*/
    protected Rect coordinatesToCanvas(Rect destino) {
        int _width = (destino.width * _canvas.width / _referenceCanvasWidth);
        int _height = destino.height * _width / destino.width;

        int _y = (_canvas.height * destino.y / _referenceCanvasHeight) + _canvas.y;
        int _x = (_canvas.width * destino.x / _referenceCanvasWidth) + _canvas.x;
        return new Rect(_x, _y, _width, _height);
    }

    public boolean isInCanvas(int x, int y) {
        return (x >= _canvas.x && x <= _canvas.x + _canvas.width
                && y >= _canvas.y && y <= _canvas.y + _canvas.height);
    }

    public int revertCoordinateX(int x) {
        return (x * _referenceCanvasWidth) / _canvas.width;
    }

    public int revertCoordinateY(int y) {
        return (y * _referenceCanvasHeight) / _canvas.height;
    }

    protected void setCanvasSize(Rect frameCanvas) {
        int new_height = frameCanvas.height;
        int new_width = (new_height * _referenceCanvasWidth) / _referenceCanvasHeight;

        int x = 0;
        x += (frameCanvas.width / 2) - (new_width / 2);
        _canvas = new Rect(x, 0, new_width, new_height);
    }

    public Rect getRectCanvas() {
        return _canvas;
    }
}

