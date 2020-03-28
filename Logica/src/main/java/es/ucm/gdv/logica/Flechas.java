package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Flechas {
    private Sprite sprite;

    private double posY;
    private int startPosition;
    private int velFlechas;

    Flechas(ResourceManager res) {
        Image flechasimg = res.getImage(ResourceManager.GameSprites.ARROWS);
        posY = startPosition = - flechasimg.getHeight() / 5;
        velFlechas = 0;
        sprite = new Sprite(flechasimg, 0, 0, flechasimg.getWidth(), flechasimg.getHeight());
    }

    public void tick(double elapsedTime) {
        posY += ((384 + velFlechas) * elapsedTime);
        if (posY >= 0) {
            posY = startPosition;
        }
    }


    public void render(Graphics graphics) {
        sprite.drawImage(graphics, 1080/2 - sprite.getImage().getWidth() / 2,
                (int)posY, sprite.getSpriteWidth(), sprite.getSpriteHeight(), 50);

    }

    void aumentaVelocidad() {
        velFlechas += 90;
    }

    void resetVelocidad() {
        velFlechas = 0;
    }

    Sprite getSprite() {
        return sprite;
    }
}
