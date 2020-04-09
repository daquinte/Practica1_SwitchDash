package es.ucm.gdv.switchdashpc;

import es.ucm.gdv.logica.Logica;
import es.ucm.gdv.motorpc.GamePC;

public class Main {
    public static void main(String[] args) {
        GamePC game = new GamePC("Switch Dash - Practica de Moviles edition");
        Logica logica = new Logica(game);
        logica.init();
        game.init();
        game.run();
    }

}
