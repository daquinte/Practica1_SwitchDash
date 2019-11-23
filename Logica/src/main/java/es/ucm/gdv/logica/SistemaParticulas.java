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
    enum Direccion {DERECHA, IZQUIERDA}
    Game _game;
    int alpha;
    int factor = 05;

    Sprite particleSprite;
    SistemaParticulas sistemaP;
    Direccion pDirection;
    Random rnd;

    int posX, posY;
    public Particula(SistemaParticulas sp, Game g, Sprite s, Pair originalPos){
        _game = g;
        rnd = new Random();
        sistemaP = sp;
        particleSprite = s;

        pDirection = (rnd.nextInt(2) == 0) ? Direccion.DERECHA : Direccion.IZQUIERDA;
        posX = originalPos._first;
        posY = 0;
        do {
            alpha = rnd.nextInt(100) + 50;
        } while (alpha < 0);
    }

    public void tick(double elapsedTime){
        alpha -= (factor * elapsedTime);
        if (alpha <= 0) {
            sistemaP.eraseParticle(this);
        }
        else {
            posX = (int) ((pDirection == Direccion.IZQUIERDA) ? posX-(60*elapsedTime) : posX+(60*elapsedTime));
            posY-= 100 * elapsedTime;
        }
    }

    public void render(){
        particleSprite.drawWithAlphaScaled(_game.getGraphics(), posX, 1100 - posY, particleSprite.getSpriteWidth(), particleSprite.getSpriteHeight(), alpha);
    }
}
