package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Sprite;
import java.util.Random;
public class SwitchDashState implements GameState {

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    //Atributos del juego
    Jugador jugador;
    Pelota [] pelotas; //Vas a tener un array de 4-5 pelotas y las vas a ir subiendo xdd
    int pelotasRecogidas;
    int velocidadActual;



    public SwitchDashState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();


        //init de juego
        pelotas = new Pelota[3];
        jugador = new Jugador(_resourceManager, Jugador.colorJugador.BLANCO);

        //jugador = new Jugador(_resourceManager);
        pelotasRecogidas = 0;
        velocidadActual = 0;
        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
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

        Sprite auxJugador = jugador.GetColorJugador();
        int x = 1080/2 + auxJugador.getImage().getWidth()/2;
        int y = 1200;

        auxJugador.drawScaled(graphics, x, y, auxJugador.getImage().getWidth(), auxJugador.getImage().getHeight());
    }


}
