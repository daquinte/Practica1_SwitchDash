package es.ucm.gdv.logica;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.TouchEvent;

/*Estado principal del juego*/
public class SwitchDashState implements GameState {

    //Atributos del motor de juego
    private Game _game;
    private ResourceManager _resourceManager;
    private Logica _logica;
    private Graphics _graphics;
    private Random rnd;

    //Atributos del juego
    private Jugador jugador;                            //Referencia a la clase jugador.

    private Queue<Pelota> pelotas;                      //Cola con las pelotas que se reciclan a lo largo del juego.
    private Pelota ultimaPelota;                        //Referencia a la última pelota, la superior, para recolocar las pelotas destruidas.
    private int pelotasRecogidas;                       //Contador de pelotas. Cada 10, sube la velocidad.
    private int velocidadActual;                        //Velocidad actual de las pelotas

    private boolean isGameOver;                         //Bool que determina si ha perdido o no
    private double gameOverTimeTrack;                   //Contador del tiempo hasta que termine
    private SistemaParticulas sistemaParticulas;        //Sistema de particulas para cuando se destruye una pelota

    private int puntosTotales;                          //Contador de puntos
    private Sprite[] puntuacionSprite;                  //Sprites con los números de los puntos.

    //CONST
    private final int SeparacionPelotas = 395;


    SwitchDashState() {
        _resourceManager = ResourceManager.GetResourceManager();
        _logica = Logica.GetLogica();
    }

    @Override
    public void init(Game game) {
        _game = game;
        _graphics = _game.getGraphics();
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
        puntosTotales--;
        CalculaPuntuacion();
    }

    @Override
    public void clear() {
        _logica.clear(_graphics);
    }

    @Override
    public void tick(double elapsedTime) {
            handleInput();

            _logica.commonTick(elapsedTime);

            for (Pelota p : pelotas) {
                p.tick(elapsedTime, velocidadActual);
            }
            if(!isGameOver)
                CompruebaColision(pelotas.peek());

            //Particulas
            sistemaParticulas.tick(elapsedTime);

        if(isGameOver) {
            gameOverTimeTrack += elapsedTime;
            if(gameOverTimeTrack >= 1.2){
                _logica.setCurrentGameState(_game,new GameOverState(puntosTotales));
            }
        }

    }

    @Override
    public void render() {

        _logica.commonRender(_graphics);

        //JUGADOR
        if(!isGameOver) {
            Sprite auxJugador = jugador.GetSpriteJugador();
            auxJugador.drawImage(_graphics, jugador.getX(), jugador.getY(), auxJugador.getSpriteWidth(), auxJugador.getSpriteHeight(), true);
        }
        //PELOTAS
        for (Pelota p : pelotas) {
            Sprite spriteP = p.GetSpritePelota();
            spriteP.drawImage(_graphics, 1080 / 2 - spriteP.getSpriteWidth() / 2, (int)p.getPosY(), p.getWidth(), p.getWidth(), true);
        }

        //Puntos
        int numuerosApintar = (puntosTotales >= 100) ? 2 : (puntosTotales >= 10) ? 1 : 0;
        int xOffset = numuerosApintar;
        for (int i = 2; i >= 2 - numuerosApintar; i--) {
            puntuacionSprite[i].drawImage(_graphics, (1080 * 2 / 3 + 145) + 95 * xOffset, 115, puntuacionSprite[2].getSpriteWidth(), puntuacionSprite[2].getSpriteHeight(), true);
            xOffset--;
        }

        //Particulas
        sistemaParticulas.render();
    }

    @Override
    public void handleInput() {
        List<TouchEvent> touchEvents = _game.getInput().getTouchEvents();
        for (TouchEvent touchEvent : touchEvents) {
            if (touchEvent.getTouchType() == TouchEvent.TouchType.click) {
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
        Pelota pelota = pelotas.peek();
        if (pelota == null) return;
        int yPelota = (int)pelota.getPosY();
        if ((yPelota + pelota.getHeight() / 2) >= yJugador) {
            int colorJugador = jugador.GetColorJugador().ordinal();
            int colorPelota = p.GetColorPelota().ordinal();
            if (colorJugador != colorPelota) {
               isGameOver = true;
            } else {
                sistemaParticulas.addParticles(p.GetSpritePelota(), 15, 1080 / 2 - p.GetSpritePelota().getSpriteWidth() / 2);
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
        if (puntosTotales <= 998) {
            puntosTotales++;
            int resultadoDivision = puntosTotales;
            int indexSprite = 2;
            while (resultadoDivision > 0) {
                puntuacionSprite[indexSprite] = _resourceManager.numbers[resultadoDivision % 10];
                resultadoDivision /= 10;
                indexSprite--;
            }
            while (indexSprite > 0) {
                puntuacionSprite[indexSprite] = _resourceManager.numbers[0];
                indexSprite--;
            }
        }
        else {
            puntosTotales = 0;
            initSpritePuntos();
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
