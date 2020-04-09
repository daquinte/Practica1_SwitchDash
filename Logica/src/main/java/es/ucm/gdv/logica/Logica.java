package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;


public class Logica {
    private Game _game;
    private Graphics _graphics;
    private ResourceManager _resourceManager;

    private ResourceManager.GameColor currentColor;
    private Sprite bgSprite;
    private Sprite flash;
    private Flechas flechas;
    private Boolean activateFlash = false;
    private int alpha = 200;

    public Logica(Game game) {
        _game = game;
    }

    public void init() {
        _graphics = _game.getGraphics();

        //INICIA RESOURCE MANAGER
        currentColor = ResourceManager.GameColor.GREEN;
        _resourceManager = new ResourceManager(this);
        _game.setGameState(_resourceManager);
    }

    public void initFlechas(){
        //init del resource
        flechas = new Flechas(_resourceManager);

        Image flashI = _resourceManager.getImage(ResourceManager.GameSprites.WHITE);
        flash = new Sprite(flashI, 0, 0, flashI.getWidth(), flashI.getHeight());
    }

    public void clear() {
        ClearScreen(currentColor);
    }

    //La parte común del tick
    public void commonTick(double elapsedTime) {

        flechas.tick(elapsedTime);
        flashStuff(elapsedTime);
    }

    //La parte común del render
    public void commonRender() {
        flechas.render(_graphics);
    }

    ResourceManager getResourceManager() {
        return _resourceManager;
    }

    private void flashStuff(double elapsedTime) {
        if (activateFlash) {
            alpha -= (60 * elapsedTime);
            if (alpha <= 0) {
                activateFlash = false;
                alpha = 200;
            }
        }
    }

    void aumentaVelocidadFlechas() {
        flechas.aumentaVelocidad();
    }

    void resetVelocidadFlechas() {
        flechas.resetVelocidad();
    }

    void setCurrentGameState(GameState gameState) {
        activateFlash = true;
        alpha = 100;
        _game.setGameState(gameState);
    }

    void SetClearColor(ResourceManager.GameColor newColor) {
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

        //TODO: Pintamos el background
        /*bgSprite.drawImage(_game.getGraphics(), _game.getGraphics().getRectCanvas().x, _game.getGraphics().getRectCanvas().y
                , _game.getGraphics().getRectCanvas().width, _game.getGraphics().getRectCanvas().height);*/
        bgSprite.drawImage(_game.getGraphics(), 1080 / 2 - flechas.getSprite().getSpriteWidth() / 2, 0,
                flechas.getSprite().getSpriteWidth(), flechas.getSprite().getSpriteHeight());


        if (activateFlash) {
            flash.drawImage(_game.getGraphics(), 0, 0
                    , flash.getSpriteWidth(), flash.getSpriteHeight(), alpha);
        }
    }
}
