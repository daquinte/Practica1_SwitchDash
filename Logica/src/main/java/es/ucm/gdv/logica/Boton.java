package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.Sprite;

public class Boton {

    Game _game;

    Sprite spriteBoton;
    private Rect physicRect;
    private Rect logicRect;
    private Random rnd;

    public Boton(Game g, ResourceManager resourceManager, int x, int y) {
        _game = g;
        Image imagebotones = resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);
        spriteBoton = new Sprite(imagebotones, 0, 0, 140, 140);
        logicRect = new Rect(x + 60, y + 60, 140, 140);
    }

    public Boolean isPressed(int pressX, int pressY) {
        System.out.println("Rect: (" + physicRect.x + ", " + physicRect.y + ", " + (physicRect.x + physicRect.width) + ", " + (physicRect.y + physicRect.height) + ")");
        System.out.println("Pair: (" + pressX + ", " + pressY + ")");
        System.out.println("Sprite: (" + spriteBoton.getSpriteWidth() + ", " + spriteBoton.getSpriteHeight() + ")");
        return (pressX > physicRect.x && pressX < physicRect.x + physicRect.width
                && pressY > physicRect.y && pressY < physicRect.y + physicRect.height);
    }

    public void coordenadasACanvas(int x, int y, int width) {
        int _width = (width * _game.getGraphics().getCanvas().width / 1080);
        int _y = (_width * y / width);
        int _x = (_width * x / width);
        physicRect = new Rect (_x, _y, _width, _width);
    }

    public void render() {
        coordenadasACanvas(logicRect.x, logicRect.y, spriteBoton.getSpriteWidth());
        spriteBoton.draw(_game.getGraphics(), physicRect.x, physicRect.y, physicRect.width, physicRect.height);
    }

    public void move() {
        rnd = new Random();
        logicRect.x = rnd.nextInt(800) + 60;
        logicRect.y = rnd.nextInt(800) + 60;
    }

}
