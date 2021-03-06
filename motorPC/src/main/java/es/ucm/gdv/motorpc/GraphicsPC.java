package es.ucm.gdv.motorpc;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Rect;
import es.ucm.gdv.interfaces.AbstractGraphics;

public class GraphicsPC extends AbstractGraphics {

    //Atributos privados
    private java.awt.Graphics2D _graphics;   //Graphics de java
    private JFrame _frame;                 //Ventana donde pintamos

    void setGraphics(java.awt.Graphics2D g) {
        _graphics = g;
    }

    GraphicsPC(JFrame jf) {
        _frame = jf;
        updateCanvasSize();
    }

    //-----Métodos de la interfaz-----
    @Override
    public Image newImage(String name) {
        java.awt.Image _img = null;
        Image ret = null;

        try {
            _img = javax.imageio.ImageIO.read(new java.io.File("images/" + name));
            ret = new ImagePC(_img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void clear(int color) {
        Color rgb = new Color(color);
        _graphics.setColor(rgb);
        _graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void drawImage(Image image, Rect destino, Rect source, Boolean scale) {
        ImagePC img = (ImagePC) image;
        java.awt.Image awtImage = img.getImage();
        Rect physicsCoordinates = (scale)? coordinatesToCanvas(destino) : destino;

        _graphics.drawImage(awtImage, physicsCoordinates.x, physicsCoordinates.y,
                physicsCoordinates.x + physicsCoordinates.width, physicsCoordinates.y + physicsCoordinates.height,
                source.x, source.y,  source.x + source.width,
                source.y+source.height, null);
    }

    @Override
    public void drawImage(Image image, Rect destino, Rect source, float alpha, Boolean scale) {
        ImagePC img = (ImagePC) image;
        java.awt.Image awtImage = img.getImage();
        Rect physicsCoordinates = (scale)? coordinatesToCanvas(destino) : destino;

        //Vamos a regular el alpha, porque en PC está en escala 0-1
         alpha /= 100f;
         if(alpha< 0 ) alpha = 0;
         else if (alpha > 1) alpha = 1;

        //DST_Over = The destination is composited over the source and the result replaces the destination (Porter-Duff Destination Over Source rule).
        _graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        _graphics.drawImage(awtImage, physicsCoordinates.x, physicsCoordinates.y,
                physicsCoordinates.x+physicsCoordinates.width, physicsCoordinates.y+physicsCoordinates.height,
                source.x, source.y,  source.x+source.width,
                source.y + source.height, null);

        _graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

    }

    @Override
    public void updateCanvasSize() {
        setCanvasSize(new Rect(_frame.getX(), _frame.getY(),
                _frame.getWidth(), _frame.getHeight()));
    }

    @Override
    public int getWidth() {
        return _frame.getWidth();
    }

    @Override
    public int getHeight() {
        return _frame.getHeight();
    }
}
