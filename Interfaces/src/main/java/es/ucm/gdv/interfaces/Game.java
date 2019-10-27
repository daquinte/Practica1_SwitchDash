package es.ucm.gdv.interfaces;

import jdk.nashorn.internal.objects.annotations.Getter;

public interface Game {

    //Contiene la instancia de Graphics
    Graphics getGraphics();

    //Contiene la instancia de Input
    Input getInput();


}
