package es.ucm.gdv.logica;

import java.util.LinkedList;
import java.util.Queue;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Flechas {
    Sprite [] flechas;
    Game _game;
    ResourceManager _resourceManager;

    private int posY;
    private int velFlechas;

    public Flechas(Game game, ResourceManager res){
        _game = game;
        _resourceManager = res;
        initFlechas();
    }

    private void initFlechas(){
        flechas = new Sprite[2];
        posY = 0;
        velFlechas = 0;
        Image flechasimg = _resourceManager.getImage(ResourceManager.GameSprites.ARROWS);

        for (int i = 0; i < 2; i++){
          flechas[i] = (new Sprite(flechasimg,0,0,flechasimg.getWidth(),flechasimg.getHeight()));
        }

    }

    public void tick(double elapsedTime){
        posY += ((384 + velFlechas) * elapsedTime);
        if(posY >= _game.getGraphics().getCanvas().height){
            Sprite aux = flechas[0];
            flechas[0] = flechas[1];
            flechas[1] = aux;

            posY = 0;
        }
    }


    //TODO: Va lento porque pintamos dos bandas pero bueno such is life
    public void render(){
        flechas[0].draw( _game.getGraphics(),_game.getGraphics().getCanvas().x,posY
                ,_game.getGraphics().getCanvas().width, _game.getGraphics().getCanvas().height, 50);

        flechas[1].draw( _game.getGraphics(),_game.getGraphics().getCanvas().x,posY - _game.getGraphics().getCanvas().height
                ,_game.getGraphics().getCanvas().width, _game.getGraphics().getCanvas().height, 50);

        //System.out.println(posY - _game.getGraphics().getCanvas().height);
    }


    public void aumentaVelocidad(){
        velFlechas += 90;
    }

    public void resetVelocidad(){
        velFlechas = 0;
    }
}
