package es.ucm.gdv.logica;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Sprite;

/*"ESTADO DE JUEGO" EN EL QUE CARGAMOS LOS RECURSOS, para tenerlos disponibles*/
public class ResourceManager implements GameState {

    //UTILS
    public enum GameSprites {
        ARROWS, BACKGROUNDS, BALLS, BUTTONS, GAMEOVER, HOWTOPLAY, INSTRUCTIONS,
        PLAYAGAIN, PLAYERS, SCOREFRONT, SWITCHDASHLOGO, TAPTOPLAY, WHITE, TOTALSPRITES
    }

    public enum GameColor {GREEN, GREEN_BLUE, CYAN, LIGHT_BLUE, PURPLE, DARK_BLUE, ORANGE, RED, BEIGE}

    //
    public Sprite [] numbers;
    public Sprite [] alphabet;
    public Sprite [] bgColours;
    public boolean allLoaded = false;

    //Private
    private Game _game;
    private Logica _logica;
    private Graphics _graphics;
    private ArrayList<Image> gameImages;

    private int gameColorSize;
    private GameColor[] gameColors;

    //Random para devolver colores aleatorios
    Random rnd;

    //Para las imagenes del juego
    //En pc, se encuentran en la carpeta images
    private String[] gameImagesRoute;


    public ResourceManager(Logica l) {
        _logica = l;
        gameImages = new ArrayList<>();
    }

    @Override
    public void init(Game game) {
        _game = game;
        _graphics = _game.getGraphics();

        CargaRutasDeImagenes();
        CargaImagenes();
        CargaImagenesFondo();

        numbers = new Sprite[10];
        alphabet = new Sprite[27];

        rnd = new Random(); //Para generar alturas aleatorias
        gameColors = GameColor.values();
        gameColorSize = gameColors.length;
        initPoints();
        initAlphabet();
    }



    @Override
    public void clear() {
        _logica.clear();
    }

    @Override
    public void tick(double elapsedTime) {
        //Comprueba si se ha cargado to.do los elementos
        if (!allLoaded) {
        }
        else _logica.setCurrentGameState(new TituloState(_logica)); //TODO: Asegurate que está en Titulo al final
    }

    @Override
    public void render() {
        //este método no pinta nada
    }

    @Override
    public void handleInput() {
        //No hay input
    }


    ///----------------
    /// Métodos propios
    ///----------------

    //Public
    public Image getImage(GameSprites imgNum) {
        return gameImages.get(imgNum.ordinal());
    }

    public GameColor getRandomGamecolor() {
        return gameColors[(rnd.nextInt(gameColorSize)+1)-1];
    }

    public int setGameColorSize() {
        return gameColorSize;
    }


    private void CargaRutasDeImagenes() {
        gameImagesRoute = new String[13];
        gameImagesRoute[0] = "arrowsBackground.png";
        gameImagesRoute[1] = "backgrounds.png";
        gameImagesRoute[2] = "balls.png";
        gameImagesRoute[3] = "buttons.png";
        gameImagesRoute[4] = "gameOver.png";
        gameImagesRoute[5] = "howToPlay.png";
        gameImagesRoute[6] = "instructions.png";
        gameImagesRoute[7] = "playAgain.png";
        gameImagesRoute[8] = "players.png";
        gameImagesRoute[9] = "scoreFont.png";
        gameImagesRoute[10] = "switchDashLogo.png";
        gameImagesRoute[11] = "tapToPlay.png";
        gameImagesRoute[12] = "white.png";

    }

    //Carga imagenes de las rutas obtenidas en Resource Manager
    private void CargaImagenes() {
        for (int i = 0; i < gameImagesRoute.length; i++) {
            Image aux = _graphics.newImage(gameImagesRoute[i]);
            gameImages.add(aux);
        }

        allLoaded = true;
    }

    private void initPoints() {
        Image pointsI = getImage(GameSprites.SCOREFRONT);

        int itX = 7;
        int itY = 3;
        for (int j = 0; j < numbers.length; j++) {
            numbers[j] = new Sprite(pointsI, itX * 125, itY *160, 125, 160);
            itX ++;
            if(j == 7){
                itX = 0;
                itY++;
            }
        }
    }

    private void initAlphabet() {
        Image alphabetI = getImage(GameSprites.SCOREFRONT);

        int itX = 0;
        int itY = 0;
        for (int j = 0; j < alphabet.length; j++) {
            alphabet[j] = new Sprite(alphabetI, itX * 125, itY *160, 125, 160);
            itX ++;
            if(j == 14){
                itX = 0;
                itY++;
            }
        }
    }

    private void CargaImagenesFondo(){
        bgColours = new Sprite[9];
        Image bgBase = getImage(GameSprites.BACKGROUNDS);
        int itX = 0;
        for(int i = 0; i < bgColours.length; i++){
            bgColours[i] = new Sprite(bgBase, i * 32, 0, 32, 32);
        }
    }



}
