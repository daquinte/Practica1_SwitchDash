package es.ucm.gdv.interfaces;


public interface GameState {

    public void init(Game game);            //Inicia el gamestate

    public  void clear();                   //Limpia la pantalla

    public void tick(double elapsedTime);   //Actualiza el gamestate

    public void render();                   //Renderiza los elementos del gamestate

    public void handleInput();              //Si el estado tiene input se gestionará en este método

}
