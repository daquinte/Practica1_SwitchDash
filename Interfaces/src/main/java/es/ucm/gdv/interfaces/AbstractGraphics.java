package es.ucm.gdv.interfaces;

/*
* Clase abstracta que implementa graphics y contiene
* los métodos que son comunes a la implementacion de android y de pc.
* Concetramente, define el escalado del canvas físico donde se va a jugar
* y tiene funcionalidades para pasar de coordenadas logicas a fisicas.
* */
public abstract class AbstractGraphics {

    private Rect _canvas;

    private int _referenceCanvasWidth = 1080;
    private int _referenceCanvasHeight = 1920;

    private Rect scale(Rect oldRect) {
        int x = 0;
        int new_width = (oldRect.width > oldRect.height)? calculateNewAspectRatio(oldRect.height)
                    : calculateNewAspectRatio(oldRect.width);
        x += (oldRect.width / 2) - (new_width / 2);
        return new Rect(x, 0, new_width, oldRect.height);
    }

    /*Pasa coordenadas lógicas en la resolucion base a coordenadas físicas con nuestra resolución.*/
    protected Rect coordinatesToCanvas(Rect destino) {
        int _width = (destino.width * _canvas.width / _referenceCanvasWidth);
        int _height = destino.height * _width / destino.width;

        int _y = translateCoordinate(_canvas.height, destino.y, _canvas.y);
        int _x = (_canvas.width * destino.x / _referenceCanvasWidth) + _canvas.x;
        return new Rect(_x, _y, _width, _height);
    }

    private int translateCoordinate(int canvasSize, int coord, int canvasCoord) {
        return (canvasSize * coord / _referenceCanvasHeight) + canvasCoord;
    }

    private int calculateNewAspectRatio(int param) {
        return 9 * param / 16;
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

    protected void setCanvasSize(Rect actualCanvas) {
        _canvas = scale(actualCanvas);
    }

    public Rect getRectCanvas() {
        return _canvas;
    }
}

