package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

/*Controla la logica del jugador*/
public class Jugador {

    private ResourceManager _resourceManager;

    private Image imagenJugador;
    private int colorJugador;

    private Sprite spriteJugador;
    private Sprite jugadorNegro;
    private Sprite jugadorBlanco;

    public Jugador(ResourceManager res, int colJugador){
        _resourceManager = res;
        imagenJugador = _resourceManager.getImage(ResourceManager.GameSprites.PLAYERS);
        jugadorNegro = new Sprite(imagenJugador, 0, 0, imagenJugador.getWidth(),150);
        jugadorBlanco = new Sprite(imagenJugador, 150, 150, imagenJugador.getWidth(),imagenJugador.getWidth());

        colorJugador = colJugador;
        if(colorJugador == 1) {
            spriteJugador = jugadorNegro;
        }
        else spriteJugador = jugadorBlanco;
    }
}
