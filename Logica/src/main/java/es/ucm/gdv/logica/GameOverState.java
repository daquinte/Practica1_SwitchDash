package es.ucm.gdv.logica;

import java.util.List;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;
// TODO: Cuando arreglemos boton volver a activarlos
public class GameOverState implements GameState {

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    Graphics _graphics;


    //Atributos del estado
    public Boton sonido;
    public Boton ayuda;

    public Sprite gameOver;
    public Sprite playAgain;

    private Sprite [] puntuacionSprite;
    private Sprite [] points;
    private int puntuacionConseguida;           //Puntos obtenidos en el estado de juego.

    private Boolean mute = false;

    private float alpha;
    private float factor;

    public GameOverState(Logica l, int puntosConseguidos){
        _logica = l;
        _resourceManager = l.getResourceManager();
        puntuacionConseguida = puntosConseguidos;
    }

    @Override
    public void init(Game game) {
        _game = game;
        _graphics = _game.getGraphics();

        factor = 60;
        alpha = 0;


        initResources();
        initSpritePuntos();
        char [] puntos = {'P','O', 'I', 'N', 'T', 'S'};
        initSpritePoints(puntos);
        SeparaPuntuacion();
    }

    private void initResources(){
        // Sprites //
        Image imgGO = _resourceManager.getImage(ResourceManager.GameSprites.GAMEOVER);
        gameOver = new Sprite(imgGO, 0, 0, imgGO.getWidth(), imgGO.getHeight());

        Image imgPA = _resourceManager.getImage(ResourceManager.GameSprites.PLAYAGAIN);
        playAgain = new Sprite(imgPA, 0, 0, imgPA.getWidth(), imgPA.getHeight());

        // Botones //
        Image imageBotones = _resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);
        Sprite soundSprite = new Sprite(imageBotones, Boton.Buttons.SONIDO.ordinal() * 140, 0, 140, 140);
        Sprite muteSprite = new Sprite(imageBotones, Boton.Buttons.MUTE.ordinal() * 140, 0, 140, 140);
        sonido = new Boton(new Sprite[]{soundSprite, muteSprite}, Boton.Direcciones.IZQUIERDA, 30);

        Sprite ayudaSprite = new Sprite(imageBotones, Boton.Buttons.AYUDA.ordinal() * 140, 0, 140, 140);
        ayuda = new Boton(ayudaSprite, Boton.Direcciones.DERECHA, 30);
    }

    private void initSpritePuntos() {
        puntuacionSprite = new Sprite[3];
        for (int i = 0; i < 3; i++) {
            puntuacionSprite[i] = _resourceManager.numbers[0];
        }
    }
    private void initSpritePoints(char [] p) {
        points = new Sprite[6];
       for(int i = 0; i < points.length; i++){
           points[i] = _resourceManager.alphabet[p[i]%65];
       }
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    @Override
    public void tick(double elapsedTime) {
        alpha += (factor * elapsedTime);
        if (alpha >= 100 || alpha <= 0) {
            factor = -factor;
        }
        handleInput();
    }


    @Override
    public void handleInput() {
        List<TouchEvent> touchEvents = _game.getInput().getTouchEvents();
        for (TouchEvent touchEvent : touchEvents) {
            if (touchEvent.get_touchEvent() == TouchEvent.TouchType.click) {
                int pulsacionX = touchEvent.get_x();
                int pulsacionY = touchEvent.get_y();
                if (sonido.isPressed(pulsacionX, pulsacionY)) {
                    sonido.toggleSprite();
                }
                else if(ayuda.isPressed(pulsacionX,pulsacionY)){
                    _logica.setCurrentGameState(new HowToPlayState(_logica));
                }
                else {
                    _logica.setCurrentGameState(new SwitchDashState(_logica));
                }
            }
        }
    }

    private void SeparaPuntuacion() {

        if(puntuacionConseguida <= 999) {
            int resultadoDivision = puntuacionConseguida;
            int indexSprite = 2;
            while (resultadoDivision > 0) {
                puntuacionSprite[indexSprite] = _resourceManager.numbers[resultadoDivision % 10];
                resultadoDivision /= 10;
                indexSprite--;
            }
        }
    }

    @Override
    public void render() {
        gameOver.drawImage(_graphics, 1080 / 2 - gameOver.getImage().getWidth() / 2,
                364, gameOver.getImage().getWidth(), gameOver.getImage().getHeight());

        playAgain.drawImage(_graphics, 1080 / 2 - playAgain.getImage().getWidth() / 2, 1396,
                playAgain.getImage().getWidth(), playAgain.getImage().getHeight(), alpha);

        sonido.render(_graphics);
        ayuda.render(_graphics);

        //Puntos
        for (int i = 0; i < puntuacionSprite.length; i++) {
            puntuacionSprite[i].drawImage(_graphics, (1080 / 2 - gameOver.getImage().getWidth() / 2 - 200) + ( i *200), 700,
                    puntuacionSprite[2].getSpriteWidth() * 2, puntuacionSprite[2].getSpriteHeight() *2);

        }

        for (int i = 0; i < points.length; i++) {
            points[i].drawImage(_graphics, (1080 / 2 - gameOver.getImage().getWidth() / 2 - 190) + ( i *100), 1000,
                    puntuacionSprite[2].getSpriteWidth(), puntuacionSprite[2].getSpriteHeight());

        }
    }

}
