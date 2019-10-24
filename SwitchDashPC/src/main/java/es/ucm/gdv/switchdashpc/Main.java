package es.ucm.gdv.switchdashpc;

import es.ucm.gdv.motorpc.GamePC;

public class Main {
    public static void main(String[] args) {
        System.out.println("Switch Dash Game :D");
        GamePC game = new GamePC("Switch Dash - Practica de Moviles edition");
        game.run();
    }

}
