package es.ucm.gdv.motorandroid;

import  es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;

//Imports de android
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


import java.io.IOException;
import java.io.InputStream;


public class GraphicsAndroid implements Graphics {

    //Atributos
    private SurfaceView _surfaceView;       //Ventana para android. Se usa para los guetters
    private AssetManager _assetManager;     //Carga de imagenes
    private Canvas _canvas;                 //Viewport. Aquí se pinta.


    GraphicsAndroid(SurfaceView surfaceView, AssetManager assetManager) {
        _surfaceView = surfaceView;
        _assetManager = assetManager;
    }

    public void startFrame(Canvas c){
        _canvas = c;
    }


    @Override
    public Image newImage(String name){
        ImageAndroid imageAndroid = null;
        InputStream rutaImage = null;
        try {
            rutaImage = _assetManager.open(name);
            Bitmap sprite = BitmapFactory.decodeStream(rutaImage); //Decode el asset manager
            imageAndroid = new ImageAndroid(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //finally hace que se ejecute siempre, para cerrar el fichero independientemente del error
        finally {
            if (rutaImage != null) {
                try {
                    rutaImage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageAndroid;
    }

    @Override
    public void clear(int color) {
        _canvas.drawColor(color);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        //Poner que si no es null, se pinta en canvas
        if (image != null) {
            ImageAndroid androidImg = (ImageAndroid) image;
            Bitmap bm = androidImg.getBitmap();
            _canvas.drawBitmap(bm, x, y, null);
        }
    }

    @Override
    public void drawImageScaled(Image image, es.ucm.gdv.interfaces.Rect destino, Rect spriteFromSpriteSheet) {

    }

    @Override
    public void drawImageScaledWithAlpha(Image image, Rect destino, Rect spriteFromSpriteSheet, float alpha) {

    }



    @Override
    public void setCanvasSize() {

    }

    @Override
    public Rect getCanvas() {
        return null;
    }

    @Override
    public int getWidth() {
        return _surfaceView.getWidth();
    }

    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }
}
