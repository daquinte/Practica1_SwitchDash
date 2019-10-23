package es.ucm.gdv.interfaces;

//Estado del juego que define la lógica

public interface GameState {

    public void init(); //Limpia y establece el siguiente gamestate

    public  void clear();

    public void tick(double elapsedTime);

    public void render();

    public float getVelocity();

    public boolean getStateOver();

}

