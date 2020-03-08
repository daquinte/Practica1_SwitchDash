package es.ucm.gdv.logica;

import java.util.LinkedList;
import java.util.Queue;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Flechas {
    Sprite flechas;
    Game _game;
    ResourceManager _resourceManager;

    private double posY;
    private int startPosition;
    private int velFlechas;

    public Flechas(Game game, ResourceManager res) {
        _game = game;
        _resourceManager = res;
        initFlechas();
    }

    private void initFlechas() {

        Image flechasimg = _resourceManager.getImage(ResourceManager.GameSprites.ARROWS);
        posY = startPosition = - flechasimg.getHeight() / 5;
        velFlechas = 0;
        flechas = new Sprite(flechasimg, 0, 0, flechasimg.getWidth(), flechasimg.getHeight());


    }

    public void tick(double elapsedTime) {
        posY += ((384 + velFlechas) * elapsedTime);
        if (posY >= 0) {
            posY = startPosition;
        }
    }


    public void render() {
        flechas.draw(_game.getGraphics(), _game.getGraphics().getCanvas().x, (int)posY
                , _game.getGraphics().getCanvas().width, flechas.getImage().getHeight(), 50);
    }


    public void aumentaVelocidad() {
        velFlechas += 90;
    }

    public void resetVelocidad() {
        velFlechas = 0;
    }
}
