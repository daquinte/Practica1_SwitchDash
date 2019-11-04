package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.AbstractGraphics;
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
        AbstractGraphics graphics = _game.getGraphics();
        Image test = graphics.newImage("howToPlay.png");
        Sprite sprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());
        int x = graphics.getWidth()/2 - test.getWidth()/2;
        int y = 290;
        sprite.draw(graphics, x, y);
    }


}
