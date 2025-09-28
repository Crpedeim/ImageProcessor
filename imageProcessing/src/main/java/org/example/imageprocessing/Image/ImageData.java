package org.example.imageprocessing.Image;

import java.awt.image.BufferedImage;

public class ImageData {

    public BufferedImage getImage() {
        return image;
    }

    public int getJ() {
        return j;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private BufferedImage image;

    public ImageData(BufferedImage image,int i ,int j , int x , int y) {
        this.image = image;
        this.i = i;
        this.j = j;
        this.height = y;
        this.width = x;
    }

    private int i;
    private int j;
    private int height;
    private int width;

}
