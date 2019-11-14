package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Pelota {
    public enum colorPelota{BLANCO, NEGRO};

    private ResourceManager _resourceManager;

    private Image imagenPelota;
    colorPelota _colorPelota;

    private Sprite pelotaNegra;
    private Sprite pelotaBlanca;

    private Sprite spritePelota;


    private int posY;

    public Pelota(ResourceManager res, colorPelota colPelota){
        _resourceManager = res;
        imagenPelota = _resourceManager.getImage(ResourceManager.GameSprites.BALLS);
        pelotaBlanca = new Sprite(imagenPelota, 0,0,128,128);
        pelotaNegra = new Sprite(imagenPelota, 0,128,128,128);

        _colorPelota = colPelota;
        changeColorPelota(_colorPelota);
    }

    public void changeColorPelota(colorPelota colorJugador){
        if(_colorPelota == colorPelota.BLANCO)
            spritePelota = pelotaBlanca;
        else if(_colorPelota == colorPelota.NEGRO){
            spritePelota = pelotaNegra;
        }
    }

    public void tick(double elapsedTime, int velocidad){

        posY += ((430 + velocidad) * elapsedTime * 2);

    }


    public void setPosY(int newPosY){
        posY = newPosY;
    }

    public int getPosY(){
        return posY;
    }

    public Sprite GetColorPelota(){
        return spritePelota;
    }
}
