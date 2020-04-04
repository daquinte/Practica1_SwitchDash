package es.ucm.gdv.logica;

import java.util.List;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;

// TODO: Cuando arreglemos boton volver a activarlos
public class HowToPlayState implements GameState {
    public Boton close;

    public Sprite howToPlay;
    public Sprite instructions;
    public Sprite tapToPlay;

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    Graphics graphics;

    private float alpha;
    private float factor;


    public HowToPlayState(Logica l) {
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {
        _game = game;
        graphics = _game.getGraphics();
        resourcesInit();
        factor = 60;
        _logica.resetVelocidadFlechas();
    }

    private void resourcesInit () {
        Image imageBotones = _resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);
        Sprite closeSprite = new Sprite(imageBotones, Boton.Buttons.SALIR.ordinal() * 140, 0, 140, 140);
        close = new Boton(closeSprite, Boton.Direcciones.DERECHA, 30);

        Image howToPlayI = _resourceManager.getImage(ResourceManager.GameSprites.HOWTOPLAY);
        howToPlay = new Sprite(howToPlayI, 0, 0, howToPlayI.getWidth(), howToPlayI.getHeight());

        Image instructionsI = _resourceManager.getImage(ResourceManager.GameSprites.INSTRUCTIONS);
        instructions = new Sprite(instructionsI, 0, 0, instructionsI.getWidth(), instructionsI.getHeight());

        Image tapToPlayI = _resourceManager.getImage(ResourceManager.GameSprites.TAPTOPLAY);
        tapToPlay = new Sprite(tapToPlayI, 0, 0, tapToPlayI.getWidth(), tapToPlayI.getHeight());
    }

    @Override
    public void clear() {

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
    public void render() {
        //close.render();
        howToPlay.drawImage(graphics, 1080 / 2 - howToPlay.getImage().getWidth() / 2, 290, howToPlay.getImage().getWidth(), howToPlay.getImage().getHeight());
        instructions.drawImage(graphics, 1080 / 2 - instructions.getImage().getWidth() / 2, 768, instructions.getImage().getWidth(), instructions.getImage().getHeight());
        tapToPlay.drawImage(graphics, 1080 / 2 - tapToPlay.getImage().getWidth() / 2, 1464, tapToPlay.getImage().getWidth(), tapToPlay.getImage().getHeight(), alpha);
    }

    @Override
    public void handleInput() {
        List<TouchEvent> touchEvents = _game.getInput().getTouchEvents();
        for (TouchEvent touchEvent : touchEvents) {
            if (touchEvent.get_touchEvent() == TouchEvent.TouchType.click) {
                int pulsacionX = touchEvent.get_x();
                int pulsacionY = touchEvent.get_y();
                if (close.isPressed(pulsacionX, pulsacionY)) {
                    _logica.setCurrentGameState(new SwitchDashState(_logica));
                }
                else {
                    _logica.setCurrentGameState(new SwitchDashState(_logica));
                }
            }
        }
    }
}
