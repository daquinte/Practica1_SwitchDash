package es.ucm.gdv.interfaces;
public interface Graphics {

    Image newImage(String name);                //Carga una imagen
    void clear(int color);                      //Limpia la pantalla, con el color dado por parámetro

    /**
     *
     * Dibuja completamente la imagen "image" en la posicion (x,y)
     * de la pantalla/ventana. Mapea cada pixel de la imagen en un pixel de la pantalla.
     * @param image         Imagen a pintar
     * @param destino       rectangulo destino
     * @param source        rectangulo fuente, extraido de la imagen
     * */
    void drawImage(Image image, Rect destino, Rect source);  //Imagen y coordenadas de pintado
    void drawImage(Image image, Rect destino, Rect source, float alpha);  //Imagen y coordenadas de pintado con alpha


    /**
     *  //The src parameters represent the area of the image to copy and draw.
     *  //The dst parameters display the area of the destination to cover by the the source
     *  @param image         Imagen a pintar
     *  @param destino       rectangulo destino
     *  @param source        rectangulo fuente, extraido de la imagen
     */
    void drawImageScaled(Image image, Rect destino, Rect source);

    /**
     * Pinta la imagen, escalada en coordenadas de canvas, aplicandole la componente alpha
     * Pintar con alfa es más caro, por eso vamos a tener un método aparte
     */
    void drawImageScaledWithAlpha(Image image, Rect destino, Rect source, float alpha);

    //Set y get del Canvas
    void setCanvasSize();
    Rect getCanvas();

    //Getters de la ventana
    int getWidth();                             //Ancho
    int getHeight();                            //Alto

}

