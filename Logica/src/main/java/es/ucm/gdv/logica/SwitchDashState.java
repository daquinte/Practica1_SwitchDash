package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

public class SwitchDashState implements GameState {

    Game _game;
    public SwitchDashState (){

    }

    @Override
    public void init(Game game) {
        _game = game;
    }

    @Override
    public void clear() {

    }

    public void tick(double elapsedTime) {
    }

    public void render() {
        Graphics graphics = _game.getGraphics();
        Image test = graphics.newImage("howToPlay.png");
        Sprite sprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());
        sprite.draw(graphics, 0, 0);
    }


}
