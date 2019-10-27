package es.ucm.gdv.logica;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;

public class Logica {

    public Logica (){

    }
    private Game game;
    public void init(Game game){
        this.game = game;
    }

    public void tick(double elapsedTime) {

    }

    public void render() {
        Graphics graphics = game.getGraphics();
        Image test = graphics.newImage("howToPlay.png");
        Sprite sprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());
        sprite.render(graphics, 0, 0);
    }
}
