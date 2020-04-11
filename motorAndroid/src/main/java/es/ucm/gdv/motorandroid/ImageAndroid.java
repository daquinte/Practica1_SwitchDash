package es.ucm.gdv.motorandroid;

import android.graphics.Bitmap;

import es.ucm.gdv.interfaces.Image;

public class ImageAndroid implements Image {

    private Bitmap _imageBitmap;

    ImageAndroid(Bitmap img){
        _imageBitmap = img;
    }

    /**
     * Devuelve el Bitmap del objeto imageAndroid
     *
     * @return Bitmap del image
     * */
    Bitmap getBitmap() {
        return _imageBitmap;
    }

    @Override
    public int getWidth() {
        return _imageBitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return _imageBitmap.getHeight();
    }
}
