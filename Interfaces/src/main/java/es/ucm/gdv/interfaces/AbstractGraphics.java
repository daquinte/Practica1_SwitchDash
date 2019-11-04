package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {

    int baseWidth = 1080;
    int baseHeight = 1920;

/*
    //Código común de escalado -> DEBERIA VOLVER A ABSTRACT GRAPHICS
    //x e y estan en coordenadas ""logicas"" de canvas/juego
    public Rect Escalamelo(Image image, int x, int y) {

        int sizeX =  Translate(baseWidth, image.getWidth(), getWidth());
        int sizeY =  Translate(baseHeight, image.getHeight(), getHeight());

        //int xFisico = Translate(baseWidth, x, getWidth());
        //Esto funciona para la mitad, hay que meterle X e Y de alguna manera.
        int xFisico = getWidth()/2 - sizeX/2;
        int yFisico = getHeight()/2 - sizeY/2;

        int a1 = yFisico;
        int a2 = getHeight() - (yFisico +sizeY);
        if(a1 != a2)
            System.out.println(" ESKEREEE");


        return new Rect(xFisico,yFisico, xFisico + sizeX, yFisico + sizeY);
    }
*/
//Código común de escalado -> DEBERIA VOLVER A ABSTRACT GRAPHICS
//x e y estan en coordenadas ""logicas"" de canvas/juego
public Rect Escalamelo(Image image, int x, int y) {
    //https://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio
    int original_width = image.getWidth();
    int original_height = image.getHeight();
    int bound_width = getWidth();
    int bound_height = getHeight();
    int new_width = original_width;
    int new_height = original_height;

    // first check if we need to scale width
    if (original_width > bound_width) {
        //scale width to fit
        new_width = bound_width;
        //scale height to maintain aspect ratio
        new_height = (new_width * original_height) / original_width;
    }

    // then check if we need to scale even with the new height
    if (new_height > bound_height) {
        //scale height to fit instead
        new_height = bound_height;
        //scale width to maintain aspect ratio
        new_width = (new_height * original_width) / original_height;
    }
    return  null;
    //return new Rect(xFisico,yFisico, xFisico + sizeX, yFisico + sizeY);
}

    private int Translate(int screenSize, int param, int physicSize){
        return (physicSize * param) / screenSize;
    }

}
