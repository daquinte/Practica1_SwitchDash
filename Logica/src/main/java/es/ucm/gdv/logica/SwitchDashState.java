package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
public class SwitchDashState implements GameState {

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    //Atributos del juego
    Jugador jugador;
    //Pelota [] pelotas; //Vas a tener un array de 4-5 pelotas y las vas a ir subiendo xdd
    Queue<Pelota> pelotas;
    Pelota ultimaPelota;
    int pelotasRecogidas;
    int velocidadActual;


    private Random rnd;

    //CONST
    private final int SeparacionPelotas = 395;

    public SwitchDashState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics =(AbstractGraphics)_game.getGraphics();
        rnd = new Random();

        //init de juego
        //pelotas = new Pelota[5];
        pelotas =  new LinkedList<>();

        jugador = new Jugador(_resourceManager);
        jugador.SetColorJugador(Jugador.colorJugador.NEGRO);
        initPelotas();

        //jugador = new Jugador(_resourceManager);
        velocidadActual = 0;
        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
    }

    private void initPelotas(){
        for (int i = 0; i < 5; i++){
            Pelota aux = new Pelota(_resourceManager);
            aux.setPosY(SeparacionPelotas * -i);
            pelotas.add(aux);

            if(i == 4) ultimaPelota = aux;
        }
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    @Override
    public void tick(double elapsedTime) {

        List<TouchEvent> touchEvents = _game.getInput().getTouchEvents();
        for (TouchEvent touchEvent : touchEvents) {
            if (touchEvent.get_touchEvent() == TouchEvent.TouchType.click) {
                jugador.ToggleColorJugador();
            }
        }

        for (Pelota p : pelotas) {
            p.tick( elapsedTime, velocidadActual);
        }


        if(pelotas.peek().getPosY() + pelotas.peek().GetSpritePelota().getSpriteHeight() >= 1200){
                CompruebaColision(pelotas.peek());
        }
    }

    @Override
    public void render() {

        //JUGADOR
        Sprite auxJugador = jugador.GetSpriteJugador();
        int x = 1080/2 - auxJugador.getSpriteWidth()/2;
        int y = 1200;

        auxJugador.drawScaled(graphics, x, y, auxJugador.getSpriteWidth(), auxJugador.getSpriteHeight()/2);

        //PELOTAS
        for (Pelota p : pelotas) {
          Sprite spriteP = p.GetSpritePelota();
          x = 1080/2 - spriteP.getSpriteWidth()/2;
          y = p.getPosY();

          //TODO: se ve como un huevo porque tiene que estar en un 128 en vez de 100, o algo asi
          spriteP.drawScaled(graphics, x, y, 128, 100);
        }
    }

    @Override
    public void handleInput() {

    }

    private void CompruebaColision(Pelota p){
        int colorJugador = jugador.GetColorJugador().ordinal();
        int colorPelota = p.GetColorPelota().ordinal();
        if(colorJugador != colorPelota){
            _logica.setCurrentGameState(new TestGameState(_logica));
        }
        else if(colorJugador == colorPelota){
            ResetPelota(p);
            pelotasRecogidas ++;
            if(pelotasRecogidas >= 10){
                velocidadActual += 90;
                pelotasRecogidas = 0;
            }

        }
    }


    //Ajusta posicion y color de la nueva bola
    private void ResetPelota(Pelota p){
        p.setPosY(ultimaPelota.getPosY() - SeparacionPelotas);
        ultimaPelota = p;
        pelotas.poll();
        pelotas.add(p);

        //Random de colores
        int rndColor= rnd.nextInt(10);
        if(rndColor >=7) {
            p.setColorPelota(ultimaPelota.GetColorPelota());
        }
        else{
            p.setColorPelota(ultimaPelota.GetOppositeColorPelota());
        }
    }

}
