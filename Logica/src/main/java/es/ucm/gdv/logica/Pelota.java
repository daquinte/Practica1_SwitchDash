package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Pelota {

    private ResourceManager _resourceManager;

    private Image imagenPelota;
    private enum colorPelota{BLANCO, NEGRO};
    colorPelota _colorPelota;

    private Sprite pelotaNegra;
    private Sprite pelotaBlanca;

    private int posY;

    public Pelota(ResourceManager res, colorPelota colPelota){
        _resourceManager = res;
        imagenPelota = _resourceManager.getImage(ResourceManager.GameSprites.BALLS);
        pelotaNegra = new Sprite(imagenPelota, 0,0,128,128);
        pelotaBlanca = new Sprite(imagenPelota, 128,128,256,256);

        _colorPelota = colPelota;
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
    public Sprite GetColorPelota(){
        Sprite currentSprite = null;
        switch (_colorPelota){
        case NEGRO:
            currentSprite = pelotaNegra;
        case BLANCO:
            currentSprite =  pelotaBlanca;

    }
        return currentSprite;
    }
}
