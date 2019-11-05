package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {

    //x e y estan en coordenadas ""logicas"" de canvas/juego
    public Rect Escalamelo(Image image) {
        //https://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio
        int original_width = image.getWidth();
        int original_height = image.getHeight();
        int bound_width = this.getWidth();
        int bound_height = this.getHeight();
        int new_width = original_width;
        int new_height = original_height;
        int x = 0;
        int y = 0;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
            y += bound_height / 2 - new_height / 2;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
            x += bound_width / 2 - new_width / 2;
        }
        return new Rect(x, y, new_width, new_height);
    }
}
