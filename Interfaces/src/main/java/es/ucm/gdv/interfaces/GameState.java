package es.ucm.gdv.interfaces;

public interface GameState {

    void init(Game game);            //Inicia el gamestate

    void clear();                   //Limpia la pantalla

    void tick(double elapsedTime);   //Actualiza el gamestate

    void render();                   //Renderiza los elementos del gamestate

    void handleInput();              //Si el estado tiene input se gestionará en este metodo

}
