package es.ucm.gdv.motorandroid;

import  es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Image;

public class GraphicsAndroid implements Graphics {
    @Override
    public Image newImage(String name) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void drawImage(Image image, int x, int y) {

    }

    @Override
    public void drawImageFromSpritesheet(Image image, int x, int y, int tamTileX, int tamTileY, int imgX, int imgY) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
