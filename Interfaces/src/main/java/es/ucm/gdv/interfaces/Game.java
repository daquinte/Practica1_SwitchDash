package es.ucm.gdv.interfaces;

import jdk.nashorn.internal.objects.annotations.Getter;

public interface Game {

    //Devuelve la referencia de Game
    Game getGame();

    //Contiene la instancia de Graphics
    Graphics getGraphics();

    //Contiene la instancia de Input
    Input getInput();

    //Set del gameState que se llamará en logica
    void setGameState(GameState gameState);

}
