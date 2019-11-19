package es.ucm.gdv.logica;

import java.util.List;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;

public class TituloState implements GameState {
    public Boton sonido;
    public Boton ayuda;

    public Sprite logo;
    public Sprite tapToPlay;

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    private float alpha;
    private float factor;

    private Boolean mute = false;


    public TituloState(Logica l) {
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {
        _game = game;
        graphics = (AbstractGraphics) _game.getGraphics();
        _logica.SetClearColor(_resourceManager.getRandomGamecolor());
        resourcesInit();
        factor = 0.9f;
    }

    private void resourcesInit() {
        Image imageBotones = _resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);
        sonido = new Boton(_game, imageBotones, Boton.Buttons.SONIDO, Boton.Direcciones.IZQUIERDA, 30);
        ayuda = new Boton(_game, imageBotones, Boton.Buttons.AYUDA, Boton.Direcciones.DERECHA, 30);

        Image logoI = _resourceManager.getImage(ResourceManager.GameSprites.SWITCHDASHLOGO);
        logo = new Sprite(logoI, 0, 0, logoI.getWidth(), logoI.getHeight());

        Image tapToPlayI = _resourceManager.getImage(ResourceManager.GameSprites.TAPTOPLAY);
        tapToPlay = new Sprite(tapToPlayI, 0, 0, tapToPlayI.getWidth(), tapToPlayI.getHeight());
    }

    @Override
    public void clear() {

    }

    @Override
    public void tick(double elapsedTime) {
        alpha += (factor * elapsedTime);
        if (alpha >= 1 || alpha <= 0) {
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
                    if (!mute) {
                        mute = true;
                        sonido.toggleSprite(_resourceManager.getImage(ResourceManager.GameSprites.BUTTONS), Boton.Buttons.MUTE);
                    }
                    else {
                        mute = false;
                        sonido.toggleSprite(_resourceManager.getImage(ResourceManager.GameSprites.BUTTONS), Boton.Buttons.SONIDO);
                    }
                }
                else {
                    _logica.setCurrentGameState(new HowToPlayState(_logica));
                }
            }
        }
    }

    @Override
    public void render() {
        sonido.render();
        ayuda.render();
        logo.drawScaled(graphics, 1080 / 2 - logo.getImage().getWidth() / 2, 356, logo.getImage().getWidth(), logo.getImage().getHeight());
        tapToPlay.drawWithAlphaScaled(graphics, 1080 / 2 - tapToPlay.getImage().getWidth() / 2, 950, tapToPlay.getImage().getWidth(), tapToPlay.getImage().getHeight(), alpha);
    }
}
