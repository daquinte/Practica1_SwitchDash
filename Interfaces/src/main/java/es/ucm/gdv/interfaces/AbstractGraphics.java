package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {
    protected final int baseWidthResolution = 9;
    protected final int baseHeighResolution = 16;
    protected final int baseSizeWidth = 1080;
    protected final int baseSizeHeight = 1920;

    private int actualResolutionWidth;
    public Rect Escalamelo() {
        int new_width = 0;
        int x = 5;
        int y = 30;

        if (this.getWidth() > this.getHeight()) {
            new_width = calculateNewAspectRatio(this.getHeight());
        }
        else {
            new_width = calculateNewAspectRatio(this.getWidth());
        }
        actualResolutionWidth = new_width;
        x += (this.getWidth() / 2) - (new_width / 2);
        new_width += x;

        return new Rect(x, y, new_width, this.getHeight());
    }

    public int calculateNewAspectRatio(int param) {
        return baseWidthResolution * param / baseHeighResolution;
    }
    public Pair calculateNewCoordinates(Pair logicCoords) {
        int newX = logicCoords._first * actualResolutionWidth / baseSizeWidth;
        int newY = logicCoords._second * this.getHeight() / baseSizeHeight;
        return new Pair(newX, newY);
    }
}
