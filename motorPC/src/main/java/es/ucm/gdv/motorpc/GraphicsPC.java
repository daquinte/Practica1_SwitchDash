package es.ucm.gdv.motorpc;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import es.ucm.gdv.interfaces.AbstractGraphics;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;
import es.ucm.gdv.interfaces.Pair;
import es.ucm.gdv.interfaces.Rect;

public class GraphicsPC extends AbstractGraphics {

    public void setGraphics(java.awt.Graphics2D g) {
        _graphics = g;
    }

    public GraphicsPC(JFrame jf) {

        _frame = jf;
        setCanvasSize();
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
    public void drawImage(Image image, int x, int y) {
        //Siempre recibimos una imagen de PC, hacemos un casting hacia abajo
        ImagePC img = (ImagePC) image;
        java.awt.Image awtImage = img.getImage();
        _graphics.drawImage(awtImage, x, y, null);
    }

    @Override
    public void drawImageFromSpritesheet(Image image, Rect destino, Rect spriteFromSpriteSheet) {
        ImagePC img = (ImagePC) image;
        java.awt.Image awtImage = img.getImage();
        Rect physicsCoordinates = coordenadasACanvas(destino.x, destino.y,
                destino.width, destino.height);
        _graphics.drawImage(awtImage, physicsCoordinates.x, physicsCoordinates.y,
                physicsCoordinates.width, physicsCoordinates.height,
                spriteFromSpriteSheet.x, spriteFromSpriteSheet.y, spriteFromSpriteSheet.width,
                spriteFromSpriteSheet.height, null);
    }

    @Override
    public void drawFromSpriteSheetWithAlpha(Image image, Rect destino, Rect spriteFromSpriteSheet, int alpha) {

    }

    @Override
    public void setCanvasSize() {
        _Canvas = Escalamelo();
    }

    @Override
    public Rect getCanvas() {
        return _Canvas;
    }

    private Rect coordenadasACanvas(int x, int y, int width, int height) {
        int _height = height * getCanvas().height / baseSizeHeight;
        int _width = _height * width / height;
        int _y = (_height * y / baseSizeHeight) + getCanvas().y;
        int _x = (_width * x / baseSizeWidth) + getCanvas().x;
        _width += _x;
        _height += _y;
        return new Rect(_x, _y, _width, _height);
    }

    @Override
    public int getWidth() {
        return _frame.getWidth();
    }

    @Override
    public int getHeight() {
        return _frame.getHeight();
    }

    //Atributos privados
    java.awt.Graphics2D _graphics;   //Graphics de java
    JFrame _frame;                 //Ventana donde pintamos

    Rect _Canvas;
}
