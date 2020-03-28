package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.Sprite;

public class Boton {

    private Sprite[] spriteBotons;
    private int index;
    private Rect logicRect;                                 //Rectangulo logico para la resolucion base de la lógica.

    public enum Direcciones {IZQUIERDA, DERECHA}             //Orientacion de los botones
    public enum Buttons {AYUDA, SALIR, SONIDO, MUTE, HOME}   //Tipos de botones

    Boton(Sprite sprite, Direcciones x, int y) {
        spriteBotons = new Sprite[1];
        spriteBotons[0] = sprite;
        init(x, y);
    }

    Boton(Sprite[] sprites, Direcciones x, int y) {
        spriteBotons = sprites;
        init(x, y);
    }

    private void init(Direcciones x, int y) {
        index = 0;

        //new Sprite(imagebotones, spriteIndex.ordinal() * 140, 0, 140, 140);
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

    void toggleSprite() {
        index = (spriteBotons.length == index - 1)? 0 : index + 1;
    }

    Boolean isPressed(int pressX, int pressY) {
        return (pressX > logicRect.x && pressX < logicRect.x + logicRect.width
                && pressY > logicRect.y && pressY < logicRect.y + logicRect.height);
    }

    public void render(Graphics graphics) {
        spriteBotons[index].drawImage(graphics, logicRect.x, logicRect.y, logicRect.width, logicRect.height);
    }
}
