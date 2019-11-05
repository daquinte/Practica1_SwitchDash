package es.ucm.gdv.motorandroid;

import java.util.List;

import es.ucm.gdv.interfaces.Input;
import es.ucm.gdv.interfaces.TouchEvent;

public class InputAndroid implements Input{

    //Si es código común de PC e Input, igual es buena idea hacer un AbstractInput
    @Override
    public List<TouchEvent> getTouchEvents() {
        return null;
    }

    @Override
    public void Clear() {

    }

}
