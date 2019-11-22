package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {
    protected final int baseWidthResolution = 9;
    protected final int baseHeighResolution = 16;
    protected final int baseSizeWidth = 1080;
    protected final int baseSizeHeight = 1920;


//TODO: Igual podriamos redimensionar solo cuando reciba eventos.

    //TODO: "Bandas negras" en forma de separación superior cuando alto >>>> ancho, para que los botones no desaparezcan.
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
