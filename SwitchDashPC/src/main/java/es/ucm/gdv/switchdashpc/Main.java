package es.ucm.gdv.switchdashpc;

import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.logica.Logica;
import es.ucm.gdv.logica.ResourceManager;
import es.ucm.gdv.logica.TituloState;
import es.ucm.gdv.motorpc.GamePC;

public class Main {
    public static void main(String[] args) {
        GamePC game = new GamePC("Switch Dash - Practica de Moviles edition");
        game.init();

        GameState test = new TituloState();
        game.setGameState(test);
        game.run();
    }

}
