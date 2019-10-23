package es.ucm.gdv.interfaces;
import java.util.List;

public interface Input {
    List<TouchEvent> getTouchEvents();
    void Clear();
}
