package es.ucm.gdv.interfaces;

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
        }
        else {
            new_width = calculateNewAspectRatio(this.getWidth());
        }
        x += (this.getWidth() / 2) - (new_width / 2);


        return new Rect(x, y, new_width, this.getHeight());
    }

    public int calculateNewAspectRatio(int param) {
        return baseWidthResolution * param / baseHeighResolution;
    }
}
