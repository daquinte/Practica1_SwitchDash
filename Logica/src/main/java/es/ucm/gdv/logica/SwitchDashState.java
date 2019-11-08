package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class SwitchDashState implements GameState {

    Game _game;
    AbstractGraphics graphics;

    Sprite testSprite;
    public SwitchDashState (){

    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();

        Image test = graphics.newImage("howtoplay.png");
        testSprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());
    }

    @Override
    public void clear() {

    }

    public void tick(double elapsedTime) {
    }

    public void render() {
        int x = graphics.getCanvas().width/2 - testSprite.getImage().getWidth()/2;
        int y = 0;

        //IDEA: Igual el escalado de cada imagen es independiente. En plan, quieres que alguno esté en X
        //O tener un "factor de escalado X e Y" que vamos a aplicar a todos los objetos.
        testSprite.draw(graphics, x, y);
    }


}
