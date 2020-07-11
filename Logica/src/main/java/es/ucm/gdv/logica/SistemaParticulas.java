package es.ucm.gdv.logica;

import java.util.LinkedList;
import java.util.Random;

import es.ucm.gdv.interfaces.Game;

/**
 * Clase que representa el sistema de particulas.
 * Contiene todas las particulas que estén activas en la ejecución y
 * se encarga tanto de actualizarlas como de eliminarlas cuando sea necesario.
 * */
public class SistemaParticulas {

    private LinkedList<Particula> particulas;
    private LinkedList<Particula> eliminadas;

    private Game _game;
    public SistemaParticulas(Game g){

        particulas = new LinkedList<>();
        eliminadas = new LinkedList<>();
        _game = g;

    }

    public void addParticles(Sprite baseSprite, int numParticles, Pair originalPos){
        for(int i = 0; i < numParticles;i++){
            Particula p = new Particula(this, _game,  baseSprite, originalPos);
            particulas.add(p);
        }
    }

    public void eraseParticle(Particula p){
        eliminadas.add(p);
    }

    public void tick(double elapsedTime){
        for (Particula p : particulas) {
            p.tick(elapsedTime);
        }

        for(Particula p : eliminadas){
           particulas.remove(p);
        }
        eliminadas.clear();

    }

    public void  render(){
        for (Particula p : particulas) {
            p.render();
        }
    }
}

/*Particula del efecto de muerte de la pelota. El efecto se compone por varias particulas.
* Cada particula tiene sus propios valores de escala, posicion y "fuerza"
* -Evidentemente no hay fuerzas físicas, sólo desplazamientos-
* */
class Particula{
    Game _game;                             //Referencia al juego
    SistemaParticulas sistemaP;             //Referencia al sistema de particulas

    Sprite particleSprite;                  //Sprite base, obtenido de la pelota destruida
    Random rnd;                             //Random de la particula.


    int alpha;                              //Componente alpha. Cuando llegue a cero, se destruye.
    int randomXFactor;                      //Factor de "velocidad" en X
    int randomYFactor;                      //Factor de "velocidad" en Y
    int randomScaleFactor;                  //Escala de la particula
    int factor = 70;                        //Factor por el cual decrece el valor de "alpha"

    final int MAXSPEED = 200;
    final int MAXSCALE = 3;



    int posX, posY;
    public Particula(SistemaParticulas sp, Game g, Sprite s, Pair originalPos){
        _game = g;
        sistemaP = sp;
        particleSprite = s;
        rnd = new Random();

        posX = originalPos._first;
        posY = 0;
        randomScaleFactor = rnd.nextInt(MAXSCALE) +1;
        randomXFactor = rnd.nextInt(MAXSPEED*2)-MAXSPEED;
        randomYFactor = rnd.nextInt(MAXSPEED*2)-MAXSPEED;


        // Ajustamos la velocidad diagonal
        if (randomXFactor * randomXFactor + randomYFactor * randomYFactor > MAXSPEED * MAXSPEED) {
            randomXFactor *= 0.7;
            randomYFactor *= 0.7;
        }

        //Valor de alpha
        do {
            alpha = rnd.nextInt(20) + 75;
        } while (alpha < 0);
    }

    public void tick(double elapsedTime){
        alpha -= (factor * elapsedTime);
        if (alpha <= 0) {
            sistemaP.eraseParticle(this);
        }
        else {
            posY += randomYFactor * elapsedTime;
            posX += randomXFactor * elapsedTime;


        }
    }

    public void render(){
        particleSprite.drawImage(_game.getGraphics(), posX, 1150 + posY, particleSprite.getSpriteWidth()/randomScaleFactor, particleSprite.getSpriteHeight()/randomScaleFactor, alpha, true);
    }
}
