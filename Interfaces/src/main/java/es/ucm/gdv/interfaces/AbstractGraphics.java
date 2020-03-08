package es.ucm.gdv.interfaces;

/*
* Clase abstracta que implementa graphics y contiene
* los métodos que son comunes a la implementacion de android y de pc.
* Concetramente, define el escalado del canvas físico donde se va a jugar
* y tiene funcionalidades para pasar de coordenadas logicas a fisicas.
* */
public abstract class AbstractGraphics {
    protected final int baseWidthResolution = 9;
    protected final int baseHeighResolution = 16;
    protected final int baseSizeWidth = 1080;
    protected final int baseSizeHeight = 1920;

    protected Rect escalamelo(Rect oldRect) {
        int x = 0;
        int y = 0;
        int new_width = (oldRect.width > oldRect.height)? calculateNewAspectRatio(oldRect.height)
                    : calculateNewAspectRatio(oldRect.width);
        x += (oldRect.width / 2) - (new_width / 2);
        return new Rect(x, y, new_width, oldRect.height);
    }

    /*Pasa coordenadas lógicas en la resolucion base a coordenadas físicas con nuestra resolución.*/
    protected Rect coordenadasACanvas(Rect canvas, Rect destino) {
        int _width = (destino.width * canvas.width / baseSizeWidth);
        int _height = destino.height * _width / destino.width;

        int _y = translateCoordinate(canvas.height, destino.y, baseSizeHeight, canvas.y);
        int _x = (canvas.width * destino.x / baseSizeWidth) + canvas.x;
        return new Rect(_x, _y, _width, _height);
    }

    private int translateCoordinate(int canvasSize, int coord, int resolution, int canvasCoord) {
        return (canvasSize * coord / resolution) + canvasCoord;
    }

    private int calculateNewAspectRatio(int param) {
        return baseWidthResolution * param / baseHeighResolution;
    }
}

