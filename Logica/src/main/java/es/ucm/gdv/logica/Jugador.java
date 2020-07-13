package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Image;

/*Controla la logica del jugador*/
public class Jugador {
    public enum colorJugador {BLANCO, NEGRO}

    private colorJugador _colorJugador = colorJugador.NEGRO;

    private Sprite jugadorNegro;
    private Sprite jugadorBlanco;
    private Sprite spriteJugador;

    private Random rnd;

    private int x;
    private int y;


    Jugador(ResourceManager res) {
        Image imagenJugador = res.getImage(ResourceManager.GameSprites.PLAYERS);
        jugadorBlanco = new Sprite(imagenJugador, 0, 0, imagenJugador.getWidth(), imagenJugador.getHeight() / 2);
        jugadorNegro = new Sprite(imagenJugador, 0, imagenJugador.getHeight() / 2, imagenJugador.getWidth(), imagenJugador.getHeight() / 2);
        rnd = new Random();
        initJugador();
        x = 1080 / 2 - spriteJugador.getSpriteWidth() / 2;
        y = 1200;
    }

    private void initJugador() {
        int binaryRnd = rnd.nextInt(2);
        if (binaryRnd == 0)
            SetColorJugador(colorJugador.BLANCO);
        else if (binaryRnd == 1)
            SetColorJugador(colorJugador.NEGRO);
    }

    private void SetColorJugador(colorJugador colorJugador) {
        _colorJugador = colorJugador;
        if (_colorJugador == Jugador.colorJugador.BLANCO) {
            spriteJugador = jugadorBlanco;
        } else if (_colorJugador == Jugador.colorJugador.NEGRO) {
            spriteJugador = jugadorNegro;
        }
    }

    //Cambia el color al color opuesto
    void ToggleColorJugador() {
        if (_colorJugador == Jugador.colorJugador.BLANCO) {
            spriteJugador = jugadorNegro;
            _colorJugador = colorJugador.NEGRO;
        } else if (_colorJugador == Jugador.colorJugador.NEGRO) {
            spriteJugador = jugadorBlanco;
            _colorJugador = colorJugador.BLANCO;
        }
    }

    Sprite GetSpriteJugador() {
        return spriteJugador;
    }

    colorJugador GetColorJugador() {
        return _colorJugador;
    }

    public int getY() {
        return y;
    }

    int getX() {
        return x;
    }
}
