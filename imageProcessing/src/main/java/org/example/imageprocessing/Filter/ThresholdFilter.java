package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ThresholdFilter implements ImageFilter {

    private final int threshold;

    // You can pass the threshold value (0-255) when creating the filter
    public ThresholdFilter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage thresholdImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Convert to grayscale (luminosity method)
                int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);

                // Apply threshold
                int newColor = (gray >= threshold) ? 255 : 0;

                int newRgb = new Color(newColor, newColor, newColor).getRGB();
                thresholdImage.setRGB(x, y, newRgb);
            }
        }

        return thresholdImage;
    }
}
