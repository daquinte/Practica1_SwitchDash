package es.ucm.gdv.switchdashpc;

import es.ucm.gdv.logica.SwitchDashState;
import es.ucm.gdv.motorpc.GamePC;

public class Main {
    public static void main(String[] args) {
        System.out.println("Switch Dash Game :D");
        SwitchDashState logica = new SwitchDashState();                                         //Lógica que vas a usar en el juego.
        GamePC game = new GamePC("Switch Dash - Practica de Moviles edition");
        //"EEEH TIO POR QUE HACES UN SET ANTES DEL INIT???"
        //Canuto sé que esto es una mierda pero quería que compilase, no me pegues
        game.setGameState(logica);
        game.init();
        game.run();
    }

}
