package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Pair;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/*Estado principal del juego*/
public class SwitchDashState implements GameState {

    //Atributos del motor de juego
    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    Graphics graphics;
    private Random rnd;

    //Atributos del juego
    Jugador jugador;                            //Referencia a la clase jugador.

    Queue<Pelota> pelotas;                      //Cola con las pelotas que se reciclan a lo largo del juego.
    Pelota ultimaPelota;                        //Referencia a la última pelota, la superior, para recolocar las pelotas destruidas.
    int pelotasRecogidas;                       //Contador de pelotas. Cada 10, sube la velocidad.
    int velocidadActual;                        //Velocidad actual de las pelotas

    boolean isGameOver;                         //Bool que determina si ha perdido o no
    double gameOverTimeTrack;                      //Contador del tiempo hasta que termine
    SistemaParticulas sistemaParticulas;        //Sistema de particulas para cuando se destruye una pelota

    int puntosTotales;                          //Contador de puntos
    Sprite[] puntuacionSprite;                  //Sprites con los números de los puntos.

    //CONST
    private final int SeparacionPelotas = 395;
    private  final double TimeUntilSceneChange = 0.8;


    public SwitchDashState(Logica l) {
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {
        _game = game;
        graphics = _game.getGraphics();
        rnd = new Random();

        //Init
        puntosTotales = 0;
        pelotasRecogidas = 0;
        velocidadActual = 0;

        jugador = new Jugador(_resourceManager);
        sistemaParticulas = new SistemaParticulas(_game);
        isGameOver = false;
        gameOverTimeTrack = 0;

        pelotas = new LinkedList<>();
        initPelotas();

        puntuacionSprite = new Sprite[3];
        initSpritePuntos();

        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
        _logica.resetVelocidadFlechas();
    }

    /*Inicializa las pelotas
    situandolas fuera de la pantalla*/
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



            handleInput();

            for (Pelota p : pelotas) {
                p.tick(elapsedTime, velocidadActual);
            }
            CompruebaColision(pelotas.peek());

            //Particulas
            sistemaParticulas.tick(elapsedTime);

        if(isGameOver) {
            gameOverTimeTrack += elapsedTime;
            if(gameOverTimeTrack >= TimeUntilSceneChange){
                _logica.setCurrentGameState(new GameOverState(_logica, puntosTotales));
            }
        }

    }

    @Override
    public void render() {

        //JUGADOR
        if(!isGameOver) {
            Sprite auxJugador = jugador.GetSpriteJugador();
            auxJugador.drawImage(graphics, jugador.getX(), jugador.getY(), auxJugador.getSpriteWidth(), auxJugador.getSpriteHeight());
        }
        //PELOTAS
        for (Pelota p : pelotas) {
            Sprite spriteP = p.GetSpritePelota();
            spriteP.drawImage(graphics, 1080 / 2 - spriteP.getSpriteWidth() / 2, (int)p.getPosY(), p.getWidth(), p.getWidth());
        }

        //Puntos
        for (int i = 0; i < 3; i++) {
            puntuacionSprite[i].drawImage(graphics, 1100 + (i * 100), 200, puntuacionSprite[2].getSpriteWidth(), puntuacionSprite[2].getSpriteHeight());
        }

        //Particulas
        sistemaParticulas.render();
    }

    @Override
    public void handleInput() {
        List<TouchEvent> touchEvents = _game.getInput().getTouchEvents();
        for (TouchEvent touchEvent : touchEvents) {
            if (touchEvent.get_touchEvent() == TouchEvent.TouchType.click) {
                jugador.ToggleColorJugador();
            }
        }
    }

    /**Comprueba si el color de la pelota coincide con el del jugador
    * En caso favorable, reinicia la pelota según la última.
    * Si no, pasara al estado de GameOver
    *
    * @param  p Pelota que ha colisionado con el jugador. Normalmente, la más baja.
    * */
    private void CompruebaColision(Pelota p) {

        int yJugador = jugador.getY();
        int yPelota = (int)pelotas.peek().getPosY();
        if ((yPelota + pelotas.peek().getHeight() / 2) >= yJugador) {
            int colorJugador = jugador.GetColorJugador().ordinal();
            int colorPelota = p.GetColorPelota().ordinal();
            if (colorJugador != colorPelota) {
               isGameOver = true;
            } else {
                sistemaParticulas.addParticles(p.GetSpritePelota(), 15, new Pair(1080 / 2 - p.GetSpritePelota().getSpriteWidth() / 2, (int)p.getPosY()));
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
        p.setPosY((int)ultimaPelota.getPosY() - SeparacionPelotas);
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
