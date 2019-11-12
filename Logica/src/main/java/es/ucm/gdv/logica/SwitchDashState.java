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

   Sprite [] tusPelotas; //Vas a tener un array de 4-5 pelotas y las vas a ir subiendo xdd
    public SwitchDashState (Logica l){
        _logica = l;
        _resourceManager = l.getResourceManager();
        tusPelotas = new Sprite[5];
    }

    @Override
    public void init(Game game) {

        _game = game;
        graphics = (AbstractGraphics)_game.getGraphics();

        _logica.SetClearColor(ResourceManager.GameColor.NEGRO); //Me lo darán antes, imagino
    }

    @Override
    public void clear() {
        _logica.clear();
    }

    public void tick(double elapsedTime) {
    }

    public void render() {
    }


}
