package es.ucm.gdv.switchdashpc;

import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.logica.TituloState;
import es.ucm.gdv.motorpc.GamePC;

public class Main {
    public static void main(String[] args) {
        GamePC game = new GamePC("Switch Dash");
        game.init();

        GameState tituloState = new TituloState();
        game.setGameState(tituloState);
        game.run();
    }

}
