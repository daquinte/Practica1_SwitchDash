package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;

public class Sprite {

    public Sprite (Image image, int x, int y, int width, int height){
        _spriteImage = image;
        _source = new Rect(x, y, width, height);
    }
    private Image _spriteImage;
    private Rect _source;
    private Rect _rectDestiny;

    public void render(Graphics graphics, int x, int y) {
        int width = graphics.getWidth() * _source.width / 1080;
        int height = _source.height * width / _source.width;
        _rectDestiny = new Rect(x, y, width, height);
        graphics.drawImageFromSpritesheet(_spriteImage, _rectDestiny, _source);
    }
}
