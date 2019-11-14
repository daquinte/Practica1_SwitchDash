package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

/*Controla la logica del jugador*/
public class Jugador {
    public enum colorJugador {BLANCO, NEGRO};

    private ResourceManager _resourceManager;

    private Image imagenJugador;
    colorJugador _colorJugador = colorJugador.NEGRO;

    private Sprite jugadorNegro;
    private Sprite jugadorBlanco;
    private Sprite spriteJugador;

    public Jugador(ResourceManager res, colorJugador colJugador){
        _resourceManager = res;
        imagenJugador = _resourceManager.getImage(ResourceManager.GameSprites.PLAYERS);
        jugadorBlanco = new Sprite(imagenJugador, 0, 0, imagenJugador.getWidth(),imagenJugador.getHeight()/2);
        jugadorNegro  = new Sprite(imagenJugador, 0, imagenJugador.getHeight()/2, imagenJugador.getWidth(),imagenJugador.getHeight());

        _colorJugador = colJugador;
        ChangeColorJugador(_colorJugador);
    }

    public void ChangeColorJugador(colorJugador colorJugador){
        if(_colorJugador == Jugador.colorJugador.BLANCO)
            spriteJugador = jugadorBlanco;
        else if(_colorJugador == Jugador.colorJugador.NEGRO){
            spriteJugador = jugadorNegro;
        }
    }

    public Sprite GetColorJugador(){
        return spriteJugador;
    }
}
