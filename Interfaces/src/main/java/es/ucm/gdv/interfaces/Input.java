package es.ucm.gdv.interfaces;
import java.util.List;

public interface Input {

    /**
     * Devuelve la lista de eventos recibidos desde
     * la última invocación al método.
     * */
    List<TouchEvent> getTouchEvents();
}
