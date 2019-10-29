package es.ucm.gdv.interfaces;

public abstract class AbstractGraphics implements Graphics {

    //Código común se escalado

    public void drawImage(Image image, int x, int y){
        //x e y estan en coordenadas ""logicas"" de canvas/juego
        //int xFisico = CalculosParaPasarDeLogicasAFisicas;
        //int yFisico = CalculosParaPasarDeLogicasAFisicas;
        //drawImagePrivate(xFisico, YFisico); //No lo implementa, espera que lo implementen las interfaces

        //QUE NO SE TIENE QUE HACER EXACTAMENTE ASÍ.
        //drawImagePrivate llamaría entonces al drawImage de graphics con las coordenadas ya en físico.
    }


}
