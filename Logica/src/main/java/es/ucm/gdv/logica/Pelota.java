package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Image;

public class Pelota {
    public enum colorPelota {BLANCO, NEGRO}

    private colorPelota _colorPelota;

    private Sprite [] pelotasBlancas;
    private Sprite [] pelotasNegras;

    private Sprite spritePelota;


    private Random rnd;
    private Boolean pelotaPicuda;
    private double posY;
    private int width;
    private int height;

    Pelota(ResourceManager res) {
        width = height = 100;
        int marco = 128;
        Image imagenPelota = res.getImage(ResourceManager.GameSprites.BALLS);
        pelotasBlancas = new Sprite[10];
        pelotasNegras = new Sprite[10];
        int y = (marco - height);
        for(int i = 0; i < 10; i++) {
            int x = i * marco + (marco / 2 - width / 2);
            pelotasBlancas[i] = new Sprite(imagenPelota, x, y, width, height);
            pelotasNegras[i] = new Sprite(imagenPelota, x, y + marco, width, height);
        }
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

    void setColorPelota(colorPelota colorJugador) {
        _colorPelota = colorJugador;
        int nuevaPelota = rnd.nextInt(10);
        pelotaPicuda = (nuevaPelota == 2 || nuevaPelota == 3 || nuevaPelota == 4 || nuevaPelota == 6 || nuevaPelota == 8 || nuevaPelota == 9);
        if (_colorPelota == colorPelota.BLANCO) {
            spritePelota = pelotasBlancas[nuevaPelota];
        }
        else if (_colorPelota == colorPelota.NEGRO) {
            spritePelota = pelotasNegras[nuevaPelota];
        }
    }

    Boolean checkTypeAndColor(int colorJugador) {
        return (pelotaPicuda && colorJugador != _colorPelota.ordinal()) || (!pelotaPicuda && colorJugador == _colorPelota.ordinal());
    }

    void setPosY(int newPosY) {
        posY = newPosY;
    }

    double getPosY() {
        return posY;
    }

    Sprite GetSpritePelota() {
        return spritePelota;
    }


    colorPelota GetColorPelota() {
        return _colorPelota;
    }

    colorPelota GetOppositeColorPelota() {
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
