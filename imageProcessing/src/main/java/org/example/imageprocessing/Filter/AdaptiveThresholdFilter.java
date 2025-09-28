package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class AdaptiveThresholdFilter implements ImageFilter {

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage thresholdImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 1. Compute average brightness of this subimage
        long sum = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
                sum += gray;
            }
        }
        int avgThreshold = (int)(sum / (width * height));

        // 2. Apply adaptive threshold
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);

                int newColor = (gray >= avgThreshold) ? 255 : 0;
                int newRgb = new Color(newColor, newColor, newColor).getRGB();
                thresholdImage.setRGB(x, y, newRgb);
            }
        }

        return thresholdImage;
    }
}

