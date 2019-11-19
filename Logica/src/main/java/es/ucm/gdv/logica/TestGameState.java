package es.ucm.gdv.logica;

import java.util.List;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.Sprite;
import es.ucm.gdv.interfaces.TouchEvent;

//TODO: BORRAR ESTO ANTES DE ENTREGAR, ENTORNO DE PRUEBAS
public class TestGameState implements GameState {
    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    Sprite testSprite;
    Sprite flechas; //OJO QUE LAS FLECHAS SE VAN A PINTAR EN TODOS LOS PUTOS ESTADOS

    Boton boton;

    public TestGameState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();
    }
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();

        Image test = _resourceManager.getImage(ResourceManager.GameSprites.HOWTOPLAY);
        Image flechasimg = _resourceManager.getImage(ResourceManager.GameSprites.ARROWS);
        Image botones = _resourceManager.getImage(ResourceManager.GameSprites.BUTTONS);

        flechas = new Sprite(flechasimg,0,0,flechasimg.getWidth(),flechasimg.getHeight());
        testSprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());

        boton = new Boton(game, botones, Boton.Buttons.AYUDA, Boton.Direcciones.IZQUIERDA, 30);

        _logica.SetClearColor(ResourceManager.GameColor.GREEN);
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    public void tick(double elapsedTime) {

        List<TouchEvent> touchEvents = _game.getInput().getTouchEvents();
        for (TouchEvent touchEvent : touchEvents) {
            if (touchEvent.get_touchEvent() == TouchEvent.TouchType.click) {
                int pulsacionX = touchEvent.get_x();
                int pulsacionY = touchEvent.get_y();
                if (boton.isPressed(pulsacionX, pulsacionY)) {
                    boton.move();
                }
            }
        }


    }



    public void render() {
        int x = 1080/2 + testSprite.getImage().getWidth()/2;
        int y = 290;

        //IDEA: Igual el escalado de cada imagen es independiente. En plan, quieres que alguno esté en X
        //O tener un "factor de escalado X e Y" que vamos a aplicar a todos los objetos.
        //testSprite.drawScaled(graphics, x, y, testSprite.getImage().getWidth(), testSprite.getImage().getHeight());
        //flechas.draw(graphics,0 + graphics.getCanvas().x,0, graphics.getCanvas().width, graphics.getCanvas().height, 0.65f);

        boton.render();
        int meLaSuda = 100;
        for ( Sprite s : _resourceManager.numbers) {
            s.drawScaled(graphics, meLaSuda, meLaSuda, s.getSpriteWidth(), s.getSpriteHeight());
            meLaSuda += 100;
        }
    }

    @Override
    public void handleInput() {

    }

}
