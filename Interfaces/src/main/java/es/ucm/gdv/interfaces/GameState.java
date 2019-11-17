package es.ucm.gdv.interfaces;


public interface GameState {

    public void init(Game game); //Inicia el gamestate

    public  void clear();//Limpia y establece el siguiente gamestate

    public void tick(double elapsedTime);

    public void render();

    public void handleInput();

}
