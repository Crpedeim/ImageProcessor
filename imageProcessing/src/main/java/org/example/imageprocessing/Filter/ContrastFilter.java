package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ContrastFilter implements ImageFilter {

    private final double contrastFactor;

    /**
     * Constructor to set the contrast adjustment factor.
     * @param factor A value > 1.0 increases contrast, 0.0-1.0 decreases it.
     */
    public ContrastFilter(double factor) {
        this.contrastFactor = factor;
    }

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage contrastedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Apply the contrast formula to each color channel
                double newR = contrastFactor * (r - 128) + 128;
                double newG = contrastFactor * (g - 128) + 128;
                double newB = contrastFactor * (b - 128) + 128;

                // Clamp the values to ensure they remain in the 0-255 range
                r = Math.max(0, Math.min(255, (int) newR));
                g = Math.max(0, Math.min(255, (int) newG));
                b = Math.max(0, Math.min(255, (int) newB));

                int newRgb = new Color(r, g, b).getRGB();
                contrastedImage.setRGB(x, y, newRgb);
            }
        }
        return contrastedImage;
    }
}
