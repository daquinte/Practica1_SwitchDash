package es.ucm.gdv.interfaces;


/**
 * Clase que representa el fragmento autónomo de un image,
 * con un rectangulo especifico que representa el rectangulo que recortas de la imagen.
 * De esta manera, solo necesitas llamar a crear el rectangulo una vez. De otra forma, sería costoso.
 * */
public class Sprite {

    //Atributos
    private Image _spriteImage;
    private Rect _source;
    private Rect _rectDestiny;

    /**
     * X, Y, Width y Height vienen en coordenadas de pantalla
     * */
    public Sprite (Image image, int x, int y, int width, int height){
        _spriteImage = image;
        _source = new Rect(x, y, width, height);
    }


    /**
     * Pinta la imagen en coordenadas de pantalla, que ya tienen que venir escaladas y desplazadas(Juego -> Canvas!!).
     * @param x Posicion x destino en coodenadas de pantalla (0 - getWidth()-1 <- Escalado y reposicionamiento FUERA, es llamada desde el escalado.
     *
     * */
    public void draw(AbstractGraphics graphics, int x, int y) {

        Rect nuevaEscala = graphics.Escalamelo(_spriteImage);
        _rectDestiny = nuevaEscala;
        graphics.drawImageFromSpritesheet(_spriteImage, _rectDestiny, _source);
    }

    public Image getImage(){ return _spriteImage; }
}
