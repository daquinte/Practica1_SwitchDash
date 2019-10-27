package es.ucm.gdv.logica;

public interface GameState {

    public void init(); //Limpia y establece el siguiente gamestate

    public  void clear();

    public void tick(double elapsedTime);

    public void render();
}
