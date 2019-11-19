package es.ucm.gdv.switchdashpc;

import es.ucm.gdv.logica.Logica;
import es.ucm.gdv.motorpc.GamePC;

public class Main {
    public static void main(String[] args) {
        Logica logica = new Logica();                                         //Lógica que vas a usar en el juego.
        GamePC game = new GamePC("Switch Dash - Practica de Moviles edition");
        game.init(logica);
        game.run();
    }

}
