package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Pair;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class SwitchDashState implements GameState {

    //Atributos del motor de juego
    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    //Atributos del juego
    Jugador jugador;
    Queue<Pelota> pelotas;
    Pelota ultimaPelota;

    SistemaParticulas sistemaParticulas;



    int puntosTotales;
    Sprite[] puntuacionSprite;


    int pelotasRecogidas;           //Contador de pelotas. Cada 10, sube la velocidad.
    int velocidadActual;


    private Random rnd;

    //CONST
    private final int SeparacionPelotas = 395;

    public SwitchDashState(Logica l) {
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics) _game.getGraphics();
        rnd = new Random();

        sistemaParticulas = new SistemaParticulas(_game);

        //init de juego
        //pelotas = new Pelota[5];
        pelotas = new LinkedList<>();

        jugador = new Jugador(_resourceManager);
        initPelotas();

        //jugador = new Jugador(_resourceManager);
        puntosTotales = 0;
        puntuacionSprite = new Sprite[3];
        initSpritePuntos();
        pelotasRecogidas = 0;
        velocidadActual = 0;
        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
        _logica.resetVelocidadFlechas();
    }

    private void initPelotas() {
        for (int i = 0; i < 5; i++) {
            Pelota aux = new Pelota(_resourceManager);
            aux.setPosY(SeparacionPelotas * -i);
            pelotas.add(aux);

            if (i == 4) ultimaPelota = aux;
        }
    }

    private void initSpritePuntos() {
        for (int i = 0; i < 3; i++) {
            puntuacionSprite[i] = _resourceManager.numbers[0];
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
            p.tick(elapsedTime, velocidadActual);
        }
        CompruebaColision(pelotas.peek());

        //Particulas
        sistemaParticulas.tick(elapsedTime);
    }

    @Override
    public void render() {

        //JUGADOR
        Sprite auxJugador = jugador.GetSpriteJugador();
        auxJugador.drawScaled(graphics, jugador.getX(), jugador.getY(), auxJugador.getSpriteWidth(), auxJugador.getSpriteHeight());

        //PELOTAS
        for (Pelota p : pelotas) {
            Sprite spriteP = p.GetSpritePelota();
            spriteP.drawScaled(graphics, 1080 / 2 - spriteP.getSpriteWidth() / 2, p.getPosY(), p.getWidth(), p.getWidth());
        }

        //Puntos
        for (int i = 0; i < 3; i++) {
            puntuacionSprite[i].drawScaled(graphics, 1100 + (i * 100), 200, puntuacionSprite[2].getSpriteWidth(), puntuacionSprite[2].getSpriteHeight());
        }

        //Particulas
        sistemaParticulas.render();
    }

    @Override
    public void handleInput() {

    }

    private void CompruebaColision(Pelota p) {

        int yJugador = graphics.translateCoordinate(graphics.getCanvas().height, jugador.getY(), graphics.getBaseSizeHeight(), graphics.getCanvas().y);
        int yPelota = graphics.translateCoordinate(graphics.getCanvas().height, pelotas.peek().getPosY(), graphics.getBaseSizeHeight(), graphics.getCanvas().y);
        if ((yPelota + pelotas.peek().getHeight() / 2) >= yJugador) {
            int colorJugador = jugador.GetColorJugador().ordinal();
            int colorPelota = p.GetColorPelota().ordinal();
            if (colorJugador != colorPelota) {
                _logica.setCurrentGameState(new GameOverState(_logica, puntosTotales));
            } else {
                sistemaParticulas.addParticles(p.GetSpritePelota(), 15, new Pair(1080 / 2 - p.GetSpritePelota().getSpriteWidth() / 2, p.getPosY()));
                ResetPelota(p);
                CalculaPuntuacion();
                pelotasRecogidas++;
                if (pelotasRecogidas >= 10) {
                    velocidadActual += 90;
                    _logica.aumentaVelocidadFlechas();
                    pelotasRecogidas = 0;
                }
            }
        }
    }


    private void CalculaPuntuacion() {
        //No creo que nadie llegue, pero nunca sabes
        if (puntosTotales <= 999) {
            puntosTotales++;
            int resultadoDivision = puntosTotales;
            int indexSprite = 2;
            while (resultadoDivision > 0) {
                puntuacionSprite[indexSprite] = _resourceManager.numbers[resultadoDivision % 10];
                resultadoDivision /= 10;
                indexSprite--;
            }
        }
    }


    //Ajusta posicion y color de la nueva bola
    private void ResetPelota(Pelota p) {
        p.setPosY(ultimaPelota.getPosY() - SeparacionPelotas);
        ultimaPelota = p;
        pelotas.poll();
        pelotas.add(p);

        //Random de colores
        int rndColor = rnd.nextInt(10);
        if (rndColor >= 7) {
            p.setColorPelota(ultimaPelota.GetColorPelota());
        } else {
            p.setColorPelota(ultimaPelota.GetOppositeColorPelota());
        }
    }

}
