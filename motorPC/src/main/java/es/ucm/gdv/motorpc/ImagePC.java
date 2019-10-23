package es.ucm.gdv.motorpc;

import es.ucm.gdv.interfaces.Image;

public class ImagePC implements Image{

    public ImagePC(java.awt.Image img){
        _image = img;
    }

    public java.awt.Image getImage(){return _image;}

    @Override
    public int getWidth() {
        return _image.getWidth(null);
    }

    @Override
    public int getHeight() {
        return _image.getHeight(null);
    }

    //Atributos privados
    private java.awt.Image _image;
}
