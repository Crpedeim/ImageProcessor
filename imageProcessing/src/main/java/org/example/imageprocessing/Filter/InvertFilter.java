package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class InvertFilter implements ImageFilter {

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int newR = 255 - r;
                int newG = 255 - g;
                int newB = 255 - b;

                int newRgb = new Color(newR, newG, newB).getRGB();
                invertedImage.setRGB(x, y, newRgb);
            }
        }
        return invertedImage;
    }
}

