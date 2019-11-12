package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

//TODO: BORRAR ESTO ANTES DE ENTREGAR, ENTORNO DE PRUEBAS
public class TestGameState implements GameState {
    Game _game;
    Logica _logica;
    ResourceManager _resourceManager;
    AbstractGraphics graphics;

    Sprite testSprite;
    Sprite flechas; //OJO QUE LAS FLECHAS SE VAN A PINTAR EN TODOS LOS PUTOS ESTADOS
    public TestGameState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();
    }
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();

        Image test = _resourceManager.getImage(ResourceManager.GameSprites.HOWTOPLAY);
        Image flechasimg = _resourceManager.getImage(ResourceManager.GameSprites.ARROWS);

        flechas = new Sprite(flechasimg,0,0,flechasimg.getWidth(),flechasimg.getHeight());
        testSprite = new Sprite(test, 0, 0, test.getWidth(), test.getHeight());

        _logica.SetClearColor(ResourceManager.GameColor.GREEN);
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    public void tick(double elapsedTime) {
    }

    public void render() {
        int x = 1080/2 + testSprite.getImage().getWidth()/2;
        int y = 290;

        //IDEA: Igual el escalado de cada imagen es independiente. En plan, quieres que alguno esté en X
        //O tener un "factor de escalado X e Y" que vamos a aplicar a todos los objetos.
        testSprite.drawScaled(graphics, x, y, testSprite.getImage().getWidth(), testSprite.getImage().getHeight());
        flechas.draw(graphics,0 + graphics.getCanvas().x,0, graphics.getCanvas().width, graphics.getCanvas().height, 0.65f); //Idea: Juntar el draw y llamarlo con X parametros
    }

}
