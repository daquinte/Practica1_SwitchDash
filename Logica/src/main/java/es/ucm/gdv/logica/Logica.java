package es.ucm.gdv.logica;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;

/*
 * Clase que contiene todos los metodos
 * que son comunes entre las implementaciones de los estados de juego
 * Para que la instancia sea comun, esta clase cumple con el patron singleton
 * */
public class Logica {

    private ResourceManager.GameColor currentColor;
    private Sprite bgSprite;
    private Sprite flash;
    private Flechas flechas;
    private Boolean activateFlash = false;
    private int alpha = 200;

    private static Logica instance = null;

    static Logica GetLogica(){
        if(instance == null) {
            instance = new Logica();
        }
        return  instance;
    }

    void initFlechas(){
        //init del resource
        ResourceManager rm = ResourceManager.GetResourceManager();
        flechas = new Flechas(rm);

        Image flashI = rm.getImage(ResourceManager.GameSprites.WHITE);
        flash = new Sprite(flashI, 0, 0, flashI.getWidth(), flashI.getHeight());
    }


    //La parte comun del tick
    void commonTick(double elapsedTime) {
        flechas.tick(elapsedTime);
        flashStuff(elapsedTime);
    }

    //La parte comun del render
    void commonRender(Graphics g) {
        flechas.render(g);
    }

    private void flashStuff(double elapsedTime) {
        if (activateFlash) {
            alpha -= (60 * elapsedTime);
            if (alpha <= 0) {
                activateFlash = false;
                alpha = 200;
            }
        }
    }

    void aumentaVelocidadFlechas() {
        flechas.aumentaVelocidad();
    }

    void resetVelocidadFlechas() {
        flechas.resetVelocidad();
    }

    void setCurrentGameState(Game g, GameState gameState) {
        activateFlash = true;
        alpha = 100;
        g.setGameState(gameState);
    }

    //CLEAR DE LA PANTALLA
    public void clear(Graphics g ) {
        ClearScreen(g, currentColor);
    }

    void SetClearColor(ResourceManager.GameColor newColor) {
        currentColor = newColor;
        bgSprite = ResourceManager.GetResourceManager().bgColours[newColor.ordinal()];
    }

    private void ClearScreen(Graphics _graphics, ResourceManager.GameColor gameColor) {
        switch (gameColor) {
            case GREEN:
                _graphics.clear(0xFF41a85f);
                break;
            case GREEN_BLUE:
                _graphics.clear(0xFF00a885);
                break;
            case CYAN:
                _graphics.clear(0xFF3d8eb9);
                break;
            case LIGHT_BLUE:
                _graphics.clear(0xFF2969b0);
                break;
            case PURPLE:
                _graphics.clear(0xFF553982);
                break;
            case DARK_BLUE:
                _graphics.clear(0xFF28324e);
                break;
            case ORANGE:
                _graphics.clear(0xFFf37934);
                break;
            case RED:
                _graphics.clear(0xFFd14b41);
                break;
            case BEIGE:
                _graphics.clear(0xFF75706b);
                break;
            default:
                break;
        }

        bgSprite.drawImage(_graphics, 1080 / 2 - flechas.getSprite().getSpriteWidth() / 2, 0,
                flechas.getSprite().getSpriteWidth(), flechas.getSprite().getSpriteHeight(), true);

        if (activateFlash) {
            flash.drawImage(_graphics, 0, 0, _graphics.getWidth(), _graphics.getHeight(), alpha, false);
        }
    }
}
