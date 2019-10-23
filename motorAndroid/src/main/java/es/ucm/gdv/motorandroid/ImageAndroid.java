package es.ucm.gdv.motorandroid;

import android.graphics.Bitmap;
import es.ucm.gdv.interfaces.Image;

public class ImageAndroid implements Image {

    ImageAndroid(Bitmap img){
        _imageBitmap = img;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    Bitmap _imageBitmap;
}
