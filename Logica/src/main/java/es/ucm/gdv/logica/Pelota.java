package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class Pelota {
    public enum colorPelota {BLANCO, NEGRO}

    private ResourceManager _resourceManager;

    private Image imagenPelota;
    private colorPelota _colorPelota;

    private Sprite pelotaNegra;
    private Sprite pelotaBlanca;

    private Sprite spritePelota;


    private Random rnd;
    private int posY;
    private int x;
    private int y;
    private int width;
    private int height;

    public Pelota(ResourceManager res) {
        _resourceManager = res;
        width = height = 100;
        int marco = 128;
        imagenPelota = _resourceManager.getImage(ResourceManager.GameSprites.BALLS);
        x = marco / 2 - width / 2;
        y = x * 2;
        pelotaBlanca = new Sprite(imagenPelota, x, y, width, height);
        pelotaNegra = new Sprite(imagenPelota, x, y + marco, width, height);

        rnd = new Random();
        initPelota();
    }


    private void initPelota() {
        int binaryRnd = rnd.nextInt(2);
        if (binaryRnd == 0)
            setColorPelota(colorPelota.BLANCO);
        else if (binaryRnd == 1)
            setColorPelota(colorPelota.NEGRO);
    }

    public void tick(double elapsedTime, int velocidad) {

        posY += ((430 + velocidad) * elapsedTime);

    }

    public void setColorPelota(colorPelota colorJugador) {
        _colorPelota = colorJugador;
        if (_colorPelota == colorPelota.BLANCO)
            spritePelota = pelotaBlanca;
        else if (_colorPelota == colorPelota.NEGRO) {
            spritePelota = pelotaNegra;
        }
    }

    public void setPosY(int newPosY) {
        posY = newPosY;
    }

    public int getPosY() {
        return posY;
    }

    public Sprite GetSpritePelota() {
        return spritePelota;
    }


    public colorPelota GetColorPelota() {
        return _colorPelota;
    }

    public colorPelota GetOppositeColorPelota() {
        colorPelota cp = null;
        if (_colorPelota == colorPelota.BLANCO) {
            cp = colorPelota.NEGRO;
        } else if (_colorPelota == colorPelota.NEGRO) {
            cp = colorPelota.BLANCO;
        }
        return cp;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
