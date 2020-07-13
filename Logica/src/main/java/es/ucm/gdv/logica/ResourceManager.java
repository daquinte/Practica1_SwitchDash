package es.ucm.gdv.logica;

import java.util.ArrayList;
import java.util.Random;

import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;

/*"ESTADO DE JUEGO" EN EL QUE CARGAMOS LOS RECURSOS, para tenerlos disponibles --- SINGLETON ---*/
public class ResourceManager {

    //UTILS
    public enum GameSprites {
        ARROWS, BACKGROUNDS, BALLS, BUTTONS, GAMEOVER, HOWTOPLAY, INSTRUCTIONS,
        PLAYAGAIN, PLAYERS, SCOREFRONT, SWITCHDASHLOGO, TAPTOPLAY, WHITE, TOTALSPRITES
    }

    //Enumerado de colores para el fondo
    public enum GameColor {GREEN, GREEN_BLUE, CYAN, LIGHT_BLUE, PURPLE, DARK_BLUE, ORANGE, RED, BEIGE}


    private static ResourceManager instance = null;

    Sprite [] numbers;
    Sprite [] alphabet;
    Sprite [] bgColours;

    //Private
    private Graphics _graphics;
    private ArrayList<Image> gameImages;

    private int gameColorSize;
    private GameColor[] gameColors;

    //Random para devolver colores aleatorios
    private Random rnd;

    //Para las imagenes del juego
    //En pc, se encuentran en la carpeta images
    private String[] gameImagesRoute;


    private ResourceManager() {
        gameImages = new ArrayList<>();
    }


    public void init(Graphics graphics) {
        _graphics = graphics;

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

    static ResourceManager GetResourceManager(){
        if(instance == null) {
            instance = new ResourceManager();
        }
        return  instance;
    }

    public Image getImage(GameSprites imgNum) {
        return gameImages.get(imgNum.ordinal());
    }

    GameColor getRandomGamecolor() {
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
        for (String s : gameImagesRoute) {
            Image aux = _graphics.newImage(s);
            gameImages.add(aux);
        }
    }

    private void initPoints() {
        Image pointsI = getImage(GameSprites.SCOREFRONT);

        int itX = 7;
        int itY = 3;
        for (int j = 0; j < numbers.length; j++) {
            numbers[j] = new Sprite(pointsI, itX * 125, itY *160, 125, 160);
            itX++;
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
