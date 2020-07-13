package es.ucm.gdv.interfaces;

public interface Graphics {

    Image newImage(String name);                //Carga una imagen

    void clear(int color);                      //Limpia la pantalla, con el color dado por parámetro

    /**
     *  //The dst parameters display the area of the destination to cover by the the source
     *  //The src parameters represent the area of the image to copy and draw.
     *  @param image         Imagen a pintar
     *  @param destino       rectangulo destino
     *  @param source        rectangulo fuente, extraido de la imagen
     *  @param scale         flag para determinar si el sprite se escala
     */
    void drawImage(Image image, Rect destino, Rect source, Boolean scale);

    /**
     * Pinta la imagen, escalada en coordenadas de canvas, aplicandole la componente alpha
     * Pintar con alfa es mas costoso, por eso vamos a tener un metodo aparte
     */
    void drawImage(Image image, Rect destino, Rect source, float alpha, Boolean scale);

    // Actualiza constantemente las medidas del canvas
    void updateCanvasSize();

    // Getter del rectangulo del canvas
    Rect getRectCanvas();

    // Determina si el input esta dentro del canvas
    boolean isInCanvas(int x, int y);

    // hace un cambio de coordenadas logicas a fisicas
    int revertCoordinateX(int x);
    int revertCoordinateY(int y);

    //Getters de la ventana
    int getWidth();                             //Ancho
    int getHeight();                            //Alto
}

