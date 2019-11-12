package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Pelota {


    private int colorPelota;

    private ResourceManager _resourceManager;


    private Image imagenPelota;
    private Sprite spritePelota;
    private Sprite pelotaNegra;
    private Sprite pelotaBlanca;

    private int posY;

    public Pelota(ResourceManager res, int colPelota){
        _resourceManager = res;
        imagenPelota = _resourceManager.getImage(ResourceManager.GameSprites.BALLS);
        pelotaNegra = new Sprite(imagenPelota, 0,0,128,128);
        pelotaBlanca = new Sprite(imagenPelota, 128,128,256,256);

        colorPelota = colPelota;
        if(colorPelota == 1) {
            spritePelota = pelotaBlanca;
        }
        else spritePelota = pelotaNegra;
    }


    public void tick(int velocidad){
        posY += 430 + velocidad;
    }


    public void setPosY(int newPosY){
        posY = newPosY;
    }

    public int getPosY(){
        return posY;
    }
    public Sprite GetPelota(){
        return spritePelota;
    }
}
