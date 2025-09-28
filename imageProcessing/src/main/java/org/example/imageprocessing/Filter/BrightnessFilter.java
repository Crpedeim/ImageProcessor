package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BrightnessFilter implements ImageFilter {

    private final int brightness;

    /**
     * Constructor to set the brightness adjustment value.
     * @param brightness A positive value to brighten, a negative value to darken.
     */
    public BrightnessFilter(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage brightenedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Add the brightness value to each color component
                r += brightness;
                g += brightness;
                b += brightness;

                // Clamp the values to ensure they remain in the 0-255 range
                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                int newRgb = new Color(r, g, b).getRGB();
                brightenedImage.setRGB(x, y, newRgb);
            }
        }
        return brightenedImage;
    }
}
