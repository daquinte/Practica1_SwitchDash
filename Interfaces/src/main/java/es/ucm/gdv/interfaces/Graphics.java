package es.ucm.gdv.interfaces;
public interface Graphics {

    Image newImage(String name);                //Carga una imagen
    void clear(int color);                      //Limpia la pantalla, con el color dado por parámetro

    /**
     *
     * Dibuja completamente la imagen "image" en la posicion (x,y)
     * de la pantalla/ventana. Mapea cada pixel de la imagen en un pixel de la pantalla.
     * @param image Imagen a pintar
     * @param x Posición X en la pantalla/ventana
     * @param y Posicion Y en la pantalla/ventana
     * */
    void drawImage(Image image, int x, int y);  //Imagen y coordenadas de pintado

    /**
     *  //The src parameters represent the area of the image to copy and draw.
     *  //The dst parameters display the area of the destination to cover by the the source
     * @param image
     * @param destino
     * @param spriteFromSpriteSheet
     */
    void drawImageScaled(Image image, Rect destino, Rect spriteFromSpriteSheet);

    /**
     * Pintar con alfa es más caro, por eso vamos a tener un método aparte
     * @param image
     * @param destino
     * @param spriteFromSpriteSheet
     * @param alpha
     */
    void drawImageScaledWithAlpha(Image image, Rect destino, Rect spriteFromSpriteSheet, float alpha);

    void setCanvasSize();
    Rect getCanvas();

    //Getters de la ventana
    int getWidth();                             //Ancho
    int getHeight();                            //Alto

}

