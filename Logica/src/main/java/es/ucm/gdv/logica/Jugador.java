package es.ucm.gdv.logica;

import java.util.Random;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

/*Controla la logica del jugador*/
public class Jugador {
    public enum colorJugador {BLANCO, NEGRO}

    ;

    private ResourceManager _resourceManager;

    private Image imagenJugador;
    private colorJugador _colorJugador = colorJugador.NEGRO;

    private Sprite jugadorNegro;
    private Sprite jugadorBlanco;
    private Sprite spriteJugador;

    private Random rnd;

    int x;
    int y;

    public Jugador(ResourceManager res) {
        _resourceManager = res;
        imagenJugador = _resourceManager.getImage(ResourceManager.GameSprites.PLAYERS);
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

    public void SetColorJugador(colorJugador colorJugador) {
        _colorJugador = colorJugador;
        if (_colorJugador == Jugador.colorJugador.BLANCO) {
            spriteJugador = jugadorBlanco;
        } else if (_colorJugador == Jugador.colorJugador.NEGRO) {
            spriteJugador = jugadorNegro;
        }
    }
    //Cambia el color al color opuesto

    public void ToggleColorJugador() {
        if (_colorJugador == Jugador.colorJugador.BLANCO) {
            spriteJugador = jugadorNegro;
            _colorJugador = colorJugador.NEGRO;
        } else if (_colorJugador == Jugador.colorJugador.NEGRO) {
            spriteJugador = jugadorBlanco;
            _colorJugador = colorJugador.BLANCO;
        }
    }

    public Sprite GetSpriteJugador() {
        return spriteJugador;
    }

    public colorJugador GetColorJugador() {
        return _colorJugador;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
