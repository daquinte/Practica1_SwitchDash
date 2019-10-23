package es.ucm.gdv.motorpc; //Igual debería ser es.ucm.gdv.interfaces.motorpc

import  es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Input;

public class GamePC implements Game {

    //Referencias para el patrón Singleton
    private GraphicsPC _graphicsPC;
    private InputPC _inputPC;


    @Override
    public Graphics GetGraphics() {
        return _graphicsPC;
    }

    @Override
    public Input GetInput() {
        return _inputPC;
    }
}
