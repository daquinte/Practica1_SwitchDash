package es.ucm.gdv.practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.ucm.gdv.motorandroid.GameAndroid;

//Punto de entrada para Android//
public class MainActivity extends AppCompatActivity {

    GameAndroid _gameAndroid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _gameAndroid = new GameAndroid(this, this);

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
