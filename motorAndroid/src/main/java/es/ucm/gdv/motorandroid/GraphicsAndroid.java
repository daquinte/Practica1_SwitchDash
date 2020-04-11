package es.ucm.gdv.motorandroid;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.logica.AbstractGraphics;

//Imports de android


public class GraphicsAndroid extends AbstractGraphics implements Graphics {

    //Atributos
    private SurfaceView _surfaceView;       //Ventana para android. Se usa para los guetters
    private AssetManager _assetManager;     //Carga de imagenes
    private Canvas _androidCanvas;                 //Viewport. Aquí se pinta.

    private Paint _paint;

    GraphicsAndroid(SurfaceView surfaceView, AssetManager assetManager) {
        _surfaceView = surfaceView;
        _assetManager = assetManager;
        _paint = new Paint();
    }

    //Canvas = Objeto usado para enviar los comandos de dibujado
    void startFrame(Canvas c) {
        _androidCanvas = c;
    }

    @Override
    public Image newImage(String name) {
        ImageAndroid imageAndroid = null;
        InputStream rutaImage = null;
        try {
            rutaImage = _assetManager.open("images/" + name);
            imageAndroid = new ImageAndroid(BitmapFactory.decodeStream(rutaImage)); //Decode el asset manager
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
        _androidCanvas.drawColor(color);
    }

    // Metodo para debug
    @Override
    public void DrawRect(Rect rectangulo) {
        _paint.setColor(Color.BLUE);
        _androidCanvas.drawRect(rectangulo.x, rectangulo.y,
                rectangulo.x + rectangulo.width, rectangulo.y + rectangulo.height, _paint);

    }

    @Override
    public void drawImage(Image image, Rect destino, Rect source) {
        if (image != null) {
            Rect physicsCoords = coordinatesToCanvas(destino);
            android.graphics.Rect dest = new android.graphics.Rect(physicsCoords.x, physicsCoords.y,
                    physicsCoords.x + physicsCoords.width, physicsCoords.y + physicsCoords.height);

            android.graphics.Rect src = new android.graphics.Rect(source.x, source.y,
                    source.x + source.width, source.y + source.height);
            _androidCanvas.drawBitmap(((ImageAndroid) image).getBitmap(), src, dest, _paint);
        }
    }

    @Override
    public void drawImage(Image image, Rect destino, Rect source, float alpha) {
        _paint.setAlpha((int) alpha);
        drawImage(image, destino, source);
        _paint.reset();
    }

    @Override
    public void updateCanvasSize() {
        setCanvasSize(new Rect(0, 0, _surfaceView.getWidth(), _surfaceView.getHeight()));
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
