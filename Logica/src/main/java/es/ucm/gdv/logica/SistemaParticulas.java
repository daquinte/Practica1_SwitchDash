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
    SistemaParticulas(Game g){
        particulas = new LinkedList<>();
        eliminadas = new LinkedList<>();
        _game = g;
    }

    void addParticles(Sprite baseSprite, int numParticles, int x){
        for(int i = 0; i < numParticles;i++){
            Particula p = new Particula(this, _game,  baseSprite, x);
            particulas.add(p);
        }
    }

    void eraseParticle(Particula p){
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

/* Particula del efecto de muerte de la pelota. El efecto se compone por varias particulas.
 * Cada particula tiene sus propios valores de escala, posicion y "fuerza"
 * -Evidentemente no hay fuerzas físicas, sólo desplazamientos-
 * */
class Particula{
    private Game _game;                             //Referencia al juego
    private SistemaParticulas sistemaP;             //Referencia al sistema de particulas

    private Sprite particleSprite;                  //Sprite base, obtenido de la pelota destruida


    private int alpha;                              //Componente alpha. Cuando llegue a cero, se destruye.
    private int randomXFactor;                      //Factor de "velocidad" en X
    private int randomYFactor;                      //Factor de "velocidad" en Y
    private int randomScaleFactor;                  //Escala de la particula

    private int posX, posY;

    Particula(SistemaParticulas sp, Game g, Sprite s, int x){
        _game = g;
        sistemaP = sp;
        particleSprite = s;
        //Random de la particula.
        Random rnd = new Random();

        posX = x;
        posY = 0;
        int MAXSCALE = 3;
        randomScaleFactor = rnd.nextInt(MAXSCALE) +1;
        int MAXSPEED = 200;
        randomXFactor = rnd.nextInt(MAXSPEED *2)- MAXSPEED;
        randomYFactor = rnd.nextInt(MAXSPEED *2)- MAXSPEED;


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
        //Factor por el cual decrece el valor de "alpha"
        int factor = 70;
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
