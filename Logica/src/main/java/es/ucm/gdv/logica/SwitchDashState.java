package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class SwitchDashState implements GameState {

    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    Sprite testSprite;
    public SwitchDashState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();
    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();

        Image test = _resourceManager.getImage(ResourceManager.GameSprites.HOWTOPLAY);
        testSprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());

        _logica.SetClearColor(ResourceManager.GameColor.NEGRO);
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    public void tick(double elapsedTime) {
    }

    public void render() {
        int x = 1080/2 - testSprite.getImage().getWidth()/2;
        int y = 240;

        //IDEA: Igual el escalado de cada imagen es independiente. En plan, quieres que alguno esté en X
        //O tener un "factor de escalado X e Y" que vamos a aplicar a todos los objetos.
        testSprite.draw(graphics, x, y);
    }


}
