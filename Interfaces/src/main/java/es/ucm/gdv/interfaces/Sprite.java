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


    public void draw(AbstractGraphics graphics, int x, int y, int ancho, int alto){
        Rect _rectDestiny = new Rect(x, y, ancho, alto);
        graphics.drawImage(_spriteImage, _rectDestiny, _source);
    }

    public void draw(AbstractGraphics graphics, int x, int y, int ancho, int alto, float alpha){
        Rect _rectDestiny = new Rect(x, y, ancho, alto);
        graphics.drawImage(_spriteImage, _rectDestiny, _source, alpha);
    }


    /**
     * Pinta la imagen en coordenadas de pantalla, que ya tienen que venir escaladas y desplazadas(Juego -> Canvas!!).
     * @param x Posicion x destino en coodenadas de pantalla (0 - getWidth()-1 <- Escalado y reposicionamiento FUERA, es llamada desde el escalado.
     *
     * */
    public void drawScaled(AbstractGraphics graphics, int x, int y, int ancho, int alto) {
        Rect _rectDestiny = new Rect(x, y, ancho, alto);
        graphics.drawImageScaled(_spriteImage, _rectDestiny, _source);
    }

    public void drawWithAlphaScaled(AbstractGraphics graphics, int x, int y, int ancho, int alto, float alpha){
        Rect _rectDestiny =  new Rect(x, y, ancho, alto);
        graphics.drawImageScaledWithAlpha(_spriteImage, _rectDestiny, _source, alpha);
    }
    public Image getImage(){ return _spriteImage; }
}
