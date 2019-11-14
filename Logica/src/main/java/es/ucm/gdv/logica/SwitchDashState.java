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
        pelotas = new Pelota[1];
        jugador = new Jugador(_resourceManager, Jugador.colorJugador.BLANCO);
        initPelotas();

        //jugador = new Jugador(_resourceManager);
        pelotasRecogidas = 0;
        velocidadActual = 90;
        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
    }

    private void initPelotas(){
        for (int i = 0; i < 1; i++){
            Pelota aux = new Pelota(_resourceManager, Pelota.colorPelota.NEGRO);
            aux.setPosY(400);
            pelotas[i] = aux;
        }
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    @Override
    public void tick(double elapsedTime) {

        /*
        * for (las pelotas)
        * pelota[i].tick()
        * if(i == laUltimaPelota)
        *  if( pelota[i].GetPosY es mayor que 1200) {COLISIONES()}
        *
        *
        * */

        for (Pelota p : pelotas) {
            p.tick( elapsedTime, velocidadActual);
        }


    }

    @Override
    public void render() {

        //JUGADOR
        Sprite auxJugador = jugador.GetColorJugador();
        int x = 1080/2 - auxJugador.getSpriteWidth()/2;
        int y = 1200;

        auxJugador.drawScaled(graphics, x, y, auxJugador.getSpriteWidth(), auxJugador.getSpriteHeight()/2);

        //PELOTAS
        for (Pelota p : pelotas) {
          Sprite spriteP = p.GetColorPelota();
          x = 1080/2 - spriteP.getSpriteWidth()/2;
          y = p.getPosY();

          //TODO: se ve como un huevo porque tiene que estar en un 128 en vez de 100, o algo asi
          spriteP.drawScaled(graphics, x, y, spriteP.getSpriteWidth(), spriteP.getSpriteHeight());
        }
    }


}
