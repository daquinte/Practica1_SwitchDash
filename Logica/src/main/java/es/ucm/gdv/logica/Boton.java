package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.Sprite;

public class Boton {

    Game _game;

    Sprite spriteBoton;                                     //
    private Rect physicRect;                                //Rectangulo fisico del botón. Su representacion en la pantalla
    private Rect logicRect;                                 //Rectangulo logico para la resolucion base de la lógica.
    private Random rnd;

    public enum Direcciones {IZQUIERDA, DERECHA}             //Orientacion de los botones
    public enum Buttons {AYUDA, SALIR, SONIDO, MUTE, HOME}   //Tipos de botones

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
        return (pressX > physicRect.x && pressX < physicRect.x + physicRect.width
                && pressY > physicRect.y && pressY < physicRect.y + physicRect.height);
    }

    public void coordenadasACanvas(int x, int y, int width) {
        /*int _width = (width * _game.getGraphics().getRectCanvas().width / 1080);
        int _y = (_width * y / width) + _game.getGraphics().getRectCanvas().y;
        int _x = (_width * x / width) + _game.getGraphics().getRectCanvas().x;
        physicRect = new Rect(_x, _y, _width, _width);*/
    }

    public void render() {
        coordenadasACanvas(logicRect.x, logicRect.y, spriteBoton.getSpriteWidth());
        spriteBoton.drawImage(_game.getGraphics(), physicRect.x, physicRect.y, physicRect.width, physicRect.height);
    }


}
