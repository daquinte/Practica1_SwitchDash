package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {

    int baseWidth = 1080;
    int baseHeight = 1920;

    //Código común de escalado
    public void drawImage(Image image, int x, int y) {
        //x e y estan en coordenadas ""logicas"" de canvas/juego
        //int xFisico = CalculosParaPasarDeLogicasAFisicas;
        //int yFisico = CalculosParaPasarDeLogicasAFisicas;
        //drawImagePrivate(xFisico, YFisico); //No lo implementa, espera que lo implementen las interfaces

        //QUE NO SE TIENE QUE HACER EXACTAMENTE ASI.
        //drawImagePrivate llamaría entonces al drawImage de graphics con las coordenadas ya en físico.
    }

    private int TranslateCoordinates(int logicPosition) {
        int baseAspectRatio = baseHeight / baseWidth;
        int physicAspectRatio = getHeight() / getWidth();
        // Si el size basico es mayor que el fisico lo que hay que hacer es sumarle a la coordenada previamente dada.
        // En el caso contrario se resta. Este calculo sería el "factor" que se le aplica
        int difference = (physicAspectRatio - baseAspectRatio) / 2;
        return logicPosition + difference;
    }
}
