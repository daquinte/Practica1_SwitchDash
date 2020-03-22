package es.ucm.gdv.interfaces;

/*
* Clase abstracta que implementa graphics y contiene
* los métodos que son comunes a la implementacion de android y de pc.
* Concetramente, define el escalado del canvas físico donde se va a jugar
* y tiene funcionalidades para pasar de coordenadas logicas a fisicas.
* */
public abstract class AbstractGraphics {

    protected Rect _canvas;

    private Rect escalamelo(Rect oldRect) {
        int x = 0;
        int new_width = (oldRect.width > oldRect.height)? calculateNewAspectRatio(oldRect.height)
                    : calculateNewAspectRatio(oldRect.width);
        x += (oldRect.width / 2) - (new_width / 2);
        return new Rect(x, 0, new_width, oldRect.height);
    }

    /*Pasa coordenadas lógicas en la resolucion base a coordenadas físicas con nuestra resolución.*/
    protected Rect coordenadasACanvas(Rect destino) {
        int _width = (destino.width * _canvas.width / 1080);
        int _height = destino.height * _width / destino.width;

        int _y = translateCoordinate(_canvas.height, destino.y, _canvas.y);
        int _x = (_canvas.width * destino.x / 1080) + _canvas.x;
        return new Rect(_x, _y, _width, _height);
    }

    private int translateCoordinate(int canvasSize, int coord, int canvasCoord) {
        return (canvasSize * coord / 1920) + canvasCoord;
    }

    private int calculateNewAspectRatio(int param) {
        return 9 * param / 16;
    }

    protected void setCanvasSize(Rect actualCanvas) {
        _canvas = escalamelo(actualCanvas);
    }
    public Rect getRectCanvas() {
        return _canvas;
    }
}

