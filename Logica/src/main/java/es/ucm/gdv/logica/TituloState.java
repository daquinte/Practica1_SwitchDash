package es.ucm.gdv.logica;

import java.util.List;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.TouchEvent;


public class TituloState implements GameState {

    //Atributos del motor
    private Game _game;
    private ResourceManager _resourceManager;
    private Logica _logica;
    private Graphics _graphics;

    //Atributos del estado
    private Boton sonido;
    private Boton ayuda;

    private Sprite logo;
    private Sprite tapToPlay;

    private float alpha;
    private float factor;

    public TituloState() {
        factor = 60;
        alpha = 0;
    }

    @Override
    public void init(Game game) {
        _game = game;
        _graphics = _game.getGraphics();
        _resourceManager = ResourceManager.GetResourceManager();
        _logica = Logica.GetLogica();

        _resourceManager.init(_game.getGraphics());
        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
        resourcesInit();

    }

    private void resourcesInit() {

        Image imageBotones = _resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);

        Sprite soundSprite = new Sprite(imageBotones, Boton.Buttons.SONIDO.ordinal() * 140, 0, 140, 140);
        Sprite muteSprite = new Sprite(imageBotones, Boton.Buttons.MUTE.ordinal() * 140, 0, 140, 140);
        sonido = new Boton(new Sprite[]{soundSprite, muteSprite}, Boton.Direcciones.IZQUIERDA, 30);

        Sprite ayudaSprite = new Sprite(imageBotones, Boton.Buttons.AYUDA.ordinal() * 140, 0, 140, 140);
        ayuda = new Boton(ayudaSprite, Boton.Direcciones.DERECHA, 30);

        Image logoI = _resourceManager.getImage(ResourceManager.GameSprites.SWITCHDASHLOGO);
        logo = new Sprite(logoI, 0, 0, logoI.getWidth(), logoI.getHeight());

        Image tapToPlayI = _resourceManager.getImage(ResourceManager.GameSprites.TAPTOPLAY);
        tapToPlay = new Sprite(tapToPlayI, 0, 0, tapToPlayI.getWidth(), tapToPlayI.getHeight());

        _logica.initFlechas();
    }

    @Override
    public void clear() {
        _logica.clear(_graphics);
    }

    @Override
    public void tick(double elapsedTime) {
        _logica.commonTick(elapsedTime);
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
            if (touchEvent.getTouchType() == TouchEvent.TouchType.click) {
                int pulsacionX = touchEvent.get_x();
                int pulsacionY = touchEvent.get_y();
                if (sonido.isPressed(pulsacionX, pulsacionY)) {
                    sonido.toggleSprite();
                }
                else {
                    _logica.setCurrentGameState(_game, new HowToPlayState());
                }
            }
        }
    }

    @Override
    public void render() {
        _logica.commonRender(_graphics);
        sonido.render(_graphics);
        ayuda.render(_graphics);
        logo.drawImage(_graphics, 1080 / 2 - logo.getImage().getWidth() / 2, 356, logo.getImage().getWidth(), logo.getImage().getHeight(), true);
        tapToPlay.drawImage(_graphics, 1080 / 2 - tapToPlay.getImage().getWidth() / 2, 950, tapToPlay.getImage().getWidth(), tapToPlay.getImage().getHeight(), alpha, true);
    }
}
