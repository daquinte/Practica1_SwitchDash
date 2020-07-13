package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Rect;

public class Boton {

    private Sprite[] spriteBotons;
    private int index;
    private Rect logicRect;                                 //Rectangulo logico para la resolucion base de la logica.

    public enum Direcciones {IZQUIERDA, DERECHA}            //Orientacion de los botones
    public enum Buttons {AYUDA, SALIR, SONIDO, MUTE}        //Tipos de botones

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
        int _x = (x == Direcciones.DERECHA)? 1080 - (30 + spriteBotons[0].getSpriteWidth()) : 30;
        logicRect = new Rect(_x, y + 60,  spriteBotons[0].getSpriteWidth(), spriteBotons[0].getSpriteHeight());
    }

    void toggleSprite() {
        index = (spriteBotons.length - 1 == index) ? 0 : index + 1;
    }

    Boolean isPressed(int pressX, int pressY) {
        return (pressX >= logicRect.x && pressX <= logicRect.x + logicRect.width
                && pressY >= logicRect.y && pressY <= logicRect.y + logicRect.height);
    }

    public void render(Graphics graphics) {
        spriteBotons[index].drawImage(graphics, logicRect.x, logicRect.y, logicRect.width, logicRect.height, true);
    }
}
