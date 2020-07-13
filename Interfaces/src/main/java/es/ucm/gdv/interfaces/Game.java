package es.ucm.gdv.interfaces;

public interface Game {

    //Devuelve la referencia de Game
    Game getGame();

    //Contiene la instancia de Graphics
    Graphics getGraphics();

    //Contiene la instancia de Input
    Input getInput();

    /*Cambia de estado activo en el juego*/
    void setGameState(GameState gameState);
}
