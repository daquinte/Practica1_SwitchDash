package es.ucm.gdv.logica;

import java.util.LinkedList;
import java.util.Random;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.Pair;
import es.ucm.gdv.interfaces.Sprite;

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

class Particula{
    Game _game;                             //Referencia al juego
    SistemaParticulas sistemaP;             //Referencia al sistema de particulas

    enum Direccion {DERECHA, IZQUIERDA}     //Direccion de la particula
    int alpha;
    int randomXFactor;
    int randomYFactor;
    int randomScaleFactor;
    int factor = 70;

    final int MAXSPEED = 200;
    final int MAXSCALE = 3;


    Sprite particleSprite;
    Direccion pDirection;
    Random rnd;

    int posX, posY;
    public Particula(SistemaParticulas sp, Game g, Sprite s, Pair originalPos){
        _game = g;
        sistemaP = sp;
        particleSprite = s;
        rnd = new Random();

        pDirection = (rnd.nextInt(2) == 0) ? Direccion.DERECHA : Direccion.IZQUIERDA;
        posX = originalPos._first;
        posY = 0;
        randomScaleFactor = rnd.nextInt(MAXSCALE) +1;
        randomXFactor = rnd.nextInt(MAXSPEED*2)-MAXSPEED;
        randomYFactor = rnd.nextInt(MAXSPEED*2)-MAXSPEED;


        // smoothing out the diagonal speed
        if (randomXFactor * randomXFactor + randomYFactor * randomYFactor > MAXSPEED * MAXSPEED) {
            randomXFactor *= 0.7;
            randomYFactor *= 0.7;
        }

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
            posX += randomXFactor /** ((pDirection == Direccion.IZQUIERDA) ? -1 : 1) */* elapsedTime;


        }
    }

    public void render(){
        particleSprite.drawWithAlphaScaled(_game.getGraphics(), posX, 1150 + posY, particleSprite.getSpriteWidth()/randomScaleFactor, particleSprite.getSpriteHeight()/randomScaleFactor, alpha);
    }
}
