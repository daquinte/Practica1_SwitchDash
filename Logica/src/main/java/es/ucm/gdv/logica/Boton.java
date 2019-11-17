package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.Sprite;

public class Boton {

    Game _game;

    Sprite spriteBoton;
    int xLogica;
    int yLogica;

    public Boton (Game g, ResourceManager resourceManager, int x, int y){
        _game = g;
        Image imagebotones = resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);
        spriteBoton = new Sprite(imagebotones, 0,0, 140, 140);

        xLogica = x + 30;
        yLogica = y + 30;
    }

    public void isPressed(int pressX, int pressY){
        //SE ESCALA O QUÉ
    }

    public void render(){
        Rect botonEscalado = _game.getGraphics().coordenadasACanvas(xLogica, yLogica, spriteBoton.getSpriteWidth(), spriteBoton.getSpriteHeight());
        int x = botonEscalado.x - _game.getGraphics().getCanvas().x;
        int y = botonEscalado.y - _game.getGraphics().getCanvas().y;
        spriteBoton.draw(_game.getGraphics(), x+30, y + 30, botonEscalado.width, botonEscalado.height);
    }
}
