package es.ucm.gdv.logica;

import java.util.ArrayList;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

/*"ESTADO DE JUEGO" EN EL QUE CARGAMOS LOS RECURSOS, para tenerlos disponibles*/
public class ResourceManager implements GameState {

    //Private attributes
    private ArrayList<Image> gameImages;
    private ArrayList<ArrayList<Sprite>>gameSprites;
    private Graphics graphics;

    //Para las imagenes del juego
    //En pc, se encuentran en la carpeta images
    String [] gameImagesRoute;


    @Override
    public void init(Game game) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void tick(double elapsedTime) {

    }

    @Override
    public void render() {

    }


    ///----------------
    /// Métodos propios
    ///----------------

    private void CargaRutasDeImagenes(){
        gameImagesRoute[0]  = "./Sprites/arrowsBackground.png";
        gameImagesRoute[1]  = "./Sprites/backgrounds.png";
        gameImagesRoute[2]  = "./Sprites/balls.png";
        gameImagesRoute[3]  = "./Sprites/buttons.png";
        gameImagesRoute[4]  = "./Sprites/gameOver.png";
        gameImagesRoute[5]  = "./Sprites/howToPlay.png";
        gameImagesRoute[6]  = "./Sprites/instructions.png";
        gameImagesRoute[7]  = "./Sprites/playAgain.png";
        gameImagesRoute[8]  = "./Sprites/players.png";
        gameImagesRoute[9]  = "./Sprites/scoreFont.png";
        gameImagesRoute[10] = "./Sprites/switchDashLogo.png";
        gameImagesRoute[11] = "./Sprites/tapToPlay.png";
        gameImagesRoute[12] = "./Sprites/white.png";

    }

    //Carga imagenes de las rutas obtenidas en Resource Manager
    private void CargaImagenes(){
        for (int i = 0; i < gameImagesRoute.length; i++){

        }
    }

}
