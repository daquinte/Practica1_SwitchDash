package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {
    protected final int baseWidthResolution = 9;
    protected final int baseHeighResolution = 16;
    protected final int baseSizeWidth = 1080;
    protected final int baseSizeHeight = 1920;


    //TODO: Multiplicas por el rect y luego divides entre la resolucion correspondiente para cada imagen
    // segun Blanca sería algo como "widthLogico * rect.w / 16" porque 16 es la resolucion.
    // Nosotros no estamos respetando el aspect ratio sino que escalamos las imagenes.
    // Además habría que hacerlo en funcion del alto tambien???
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

        int _y = (getCanvas().height * y / baseSizeHeight) + getCanvas().y;
        int _x = (getCanvas().width * x / baseSizeWidth) + getCanvas().x;
        return new Rect(_x, _y, _width, _height);
    }
}
