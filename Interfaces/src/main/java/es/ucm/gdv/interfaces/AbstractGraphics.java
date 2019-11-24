package es.ucm.gdv.interfaces;

/*
* Clase abstracta que implementa graphics y contiene
* los métodos que son comunes a la implementacion de android y de pc.
* Concetramente, define el escalado del canvas físico donde se va a jugar
* y tiene funcionalidades para pasar de coordenadas logicas a fisicas.
* */
public abstract class AbstractGraphics implements Graphics {
    protected final int baseWidthResolution = 9;
    protected final int baseHeighResolution = 16;
    protected final int baseSizeWidth = 1080;
    protected final int baseSizeHeight = 1920;


    public Rect Escalamelo() {
        int new_width = 0;
        int x = 0;
        int y = 0;

        if (this.getWidth() > this.getHeight()) {
            new_width = calculateNewAspectRatio(this.getHeight());
        } else {
            new_width = calculateNewAspectRatio(this.getWidth());
        }
        x += (this.getWidth() / 2) - (new_width / 2);


        return new Rect(x, y, new_width, this.getHeight());
    }

    public int calculateNewAspectRatio(int param) {
        return baseWidthResolution * param / baseHeighResolution;
    }

    /*Pasa coordenadas lógicas en la resolucion base a coordenadas físicas con nuestra resolución.*/
    public Rect coordenadasACanvas(int x, int y, int width, int height) {
        int _width = (width * getCanvas().width / baseSizeWidth);
        int _height = height * _width / width;

        int _y = translateCoordinate(getCanvas().height, y, baseSizeHeight, getCanvas().y);
        int _x = (getCanvas().width * x / baseSizeWidth) + getCanvas().x;
        return new Rect(_x, _y, _width, _height);
    }

    public int translateCoordinate(int canvasSize, int coord, int resolution, int canvasCoord) {
        return (canvasSize * coord / resolution) + canvasCoord;
    }

    public int getBaseSizeHeight() {
        return baseSizeHeight;
    }
}
