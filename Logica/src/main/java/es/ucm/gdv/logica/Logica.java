package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;


public class Logica implements GameState {
    private Game _game;
    private Graphics _graphics;
    private GameState _currentGameState;
    private ResourceManager _resourceManager;

    private ResourceManager.GameColor currentColor;
    private Sprite bgSprite;
    private Sprite flash;
    private Flechas flechas;
    private Boolean activateFlash = false;
    private int alpha = 200;

    public Logica() {
        _currentGameState = this;
    }

    @Override
    public void init(Game game) {
        _game = game;
        _graphics = _game.getGraphics();

        //INICIA RESOURCE MANAGER
        currentColor = ResourceManager.GameColor.GREEN;
        setCurrentGameState(new ResourceManager(this));
        _resourceManager = (ResourceManager) _currentGameState;
        flechas = new Flechas(_game, _resourceManager);

        Image flashI = _resourceManager.getImage(ResourceManager.GameSprites.WHITE);
        flash = new Sprite(flashI, 0, 0, flashI.getWidth(), flashI.getHeight());
    }

    @Override
    public void clear() {
        ClearScreen(currentColor);
    }

    @Override
    public void tick(double elapsedTime) {
        flechas.tick(elapsedTime);
        _currentGameState.tick(elapsedTime);
        if (activateFlash) {
            alpha -= (60 * elapsedTime);
            if (alpha <= 0) {
                activateFlash = false;
                alpha = 200;
            }
        }

    }

    @Override
    public void render() {

        flechas.render();
        _currentGameState.render();
    }

    @Override
    public void handleInput() {
        //Logica no hace handle input, sólo sus estados.
    }

    public ResourceManager getResourceManager() {
        return _resourceManager;
    }


    public void aumentaVelocidadFlechas() {
        flechas.aumentaVelocidad();
    }

    public void resetVelocidadFlechas() {
        flechas.resetVelocidad();
    }


    public void setCurrentGameState(GameState gameState) {
        activateFlash = true;
        alpha = 100;
        _currentGameState = gameState;
        _currentGameState.init(_game);
    }

    public void SetClearColor(ResourceManager.GameColor newColor) {
        currentColor = newColor;
        bgSprite = _resourceManager.bgColours[newColor.ordinal()];
    }

    //Métodos privados de la lógica
    private void ClearScreen(ResourceManager.GameColor gameColor) {
        switch (gameColor) {
            case GREEN:
                _graphics.clear(0xFF41a85f);
                break;
            case GREEN_BLUE:
                _graphics.clear(0xFF00a885);
                break;
            case CYAN:
                _graphics.clear(0xFF3d8eb9);
                break;
            case LIGHT_BLUE:
                _graphics.clear(0xFF2969b0);
                break;
            case PURPLE:
                _graphics.clear(0xFF553982);
                break;
            case DARK_BLUE:
                _graphics.clear(0xFF28324e);
                break;
            case ORANGE:
                _graphics.clear(0xFFf37934);
                break;
            case RED:
                _graphics.clear(0xFFd14b41);
                break;
            case BEIGE:
                _graphics.clear(0xFF75706b);
                break;
            default:
                break;
        }

        //Pintamos el background
        /*bgSprite.drawImage(_game.getGraphics(), _game.getGraphics().getRectCanvas().x, _game.getGraphics().getRectCanvas().y
                , _game.getGraphics().getRectCanvas().width, _game.getGraphics().getRectCanvas().height);*/
        bgSprite.drawImage(_game.getGraphics(), 0, 0,
                _game.getGraphics().getWidth(), _game.getGraphics().getHeight());


        if (activateFlash) {
            flash.drawImage(_game.getGraphics(), 0, 0
                    , flash.getSpriteWidth(), flash.getSpriteHeight(), alpha);
        }
    }
}
