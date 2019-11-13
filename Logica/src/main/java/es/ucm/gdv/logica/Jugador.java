package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

/*Controla la logica del jugador*/
public class Jugador {
    public enum colorJugador {BLANCO, NEGRO};

    private ResourceManager _resourceManager;

    private Image imagenJugador;
    colorJugador _colorJugador = colorJugador.BLANCO;

    private Sprite jugadorNegro;
    private Sprite jugadorBlanco;

    public Jugador(ResourceManager res, colorJugador colJugador){
        _resourceManager = res;
        imagenJugador = _resourceManager.getImage(ResourceManager.GameSprites.PLAYERS);
        jugadorNegro = new Sprite(imagenJugador, 0, 0, imagenJugador.getWidth(),150);
        jugadorBlanco = new Sprite(imagenJugador, 150, 150, imagenJugador.getWidth(),imagenJugador.getWidth());

        _colorJugador = colJugador;
    }

    public void SetColorJugador(colorJugador colorJugador){
        _colorJugador = colorJugador;
    }

    public Sprite GetColorJugador(){
        Sprite currentSprite = null;
        switch (_colorJugador){
            case NEGRO:
               currentSprite = jugadorNegro;
            case BLANCO:
                currentSprite =  jugadorBlanco;

        }
        return currentSprite;
    }
}
