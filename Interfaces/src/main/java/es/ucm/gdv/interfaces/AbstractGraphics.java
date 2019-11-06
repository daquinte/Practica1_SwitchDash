package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {
    private final int baseWidthResolution = 9;
    private final int baseHeighResolution = 16;
    private final int baseSizeWidth = 1080;
    private final int baseSizeHeight = 1920;

    private int actualResolutionWidth;
    //x e y estan en coordenadas ""logicas"" de canvas/juego
    public Rect Escalamelo(Image image) {
        //https://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio
        int original_width = image.getWidth();
        int original_height = image.getHeight();
        int new_width = original_width;
        int x = 0;
        int y = 0;

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
