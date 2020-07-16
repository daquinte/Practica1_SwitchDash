package es.ucm.gdv.practica1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.logica.TituloState;
import es.ucm.gdv.motorandroid.GameAndroid;

//Punto de entrada para Android//
public class MainActivity extends AppCompatActivity {

    GameAndroid _gameAndroid;


    /**
     * Método llamado por Android como parte del ciclo de vida de
     * la actividad. Se llama en el momento de lanzarla.
     *
     * @param savedInstanceState Información de estado de la actividad
     *                           previamente serializada por ella misma
     *                           para reconstruirse en el mismo estado
     *                           tras un reinicio. Será null la primera
     *                           vez.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Esto quita la barra de notificaciones del movil
        this.getWindow().setFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        _gameAndroid = new GameAndroid(this, this);
        GameState tituloState = new TituloState();
        _gameAndroid.setGameState(tituloState);
    }

    //Cuando la APP pasa a primer plano.
    @Override
    protected void onResume() {
        super.onResume();
        _gameAndroid.Resume();
    }

    //Cuando la App pasa a segundo plano.
    @Override
    protected void onPause() {
        super.onPause();
        _gameAndroid.Pause();
    }
}
