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

    public enum Direcciones {IZQUIERDA, DERECHA}

    public enum Buttons {AYUDA, SALIR, SONIDO, MUTE, HOME}

    public Boton(Game g, Image imagebotones, Buttons spriteIndex, Direcciones x, int y) {
        _game = g;
        spriteBoton = new Sprite(imagebotones, spriteIndex.ordinal() * 140, 0, 140, 140);
        int _x;
        switch (x) {
            case IZQUIERDA:
                _x = -170;
                break;
            case DERECHA:
                _x = 1130;
                break;
            default:
                _x = -170;
                break;
        }
        logicRect = new Rect(_x, y + 60, 140, 140);
    }

    public void toggleSprite(Image imagebotones, Buttons spriteIndex) {
        spriteBoton = new Sprite(imagebotones,spriteIndex.ordinal() * 140, 0, 140, 140);
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
        int _y = (_width * y / width) + _game.getGraphics().getCanvas().y;
        int _x = (_width * x / width) + _game.getGraphics().getCanvas().x;
        physicRect = new Rect(_x, _y, _width, _width);
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
