package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class SwitchDashState implements GameState {

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    //Atributos del juego
    Jugador jugador;
    int pelotasRecogidas;
    int velocidadActual;

    Sprite [] tusPelotas; //Vas a tener un array de 4-5 pelotas y las vas a ir subiendo xdd
    public SwitchDashState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();

    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();
        _logica.SetClearColor(ResourceManager.GameColor.BLACK); //Me lo darán antes, imagino

        //init de juego
        tusPelotas = new Sprite[5];
        //jugador = new Jugador(_resourceManager);
        pelotasRecogidas = 0;
        velocidadActual = 0;
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    public void tick(double elapsedTime) {

        /*
        * for (las pelotas)
        * pelota[i].tick()
        * if(i == laUltimaPelota)
        *  if( pelota[i].GetPosY es mayor que 1200) {COLISIONES()}
        *
        *
        * */


    }

    public void render() {
    }


}
