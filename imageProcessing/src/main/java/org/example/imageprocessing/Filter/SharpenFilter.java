package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SharpenFilter implements ImageFilter {

    private static final int[][] KERNEL = {
            {  0, -1,  0 },
            { -1,  5, -1 },
            {  0, -1,  0 }
    };

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage sharpenedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int r = 0, g = 0, b = 0;

                // Apply kernel
                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int rgb = originalImage.getRGB(x + kx, y + ky);
                        int rr = (rgb >> 16) & 0xFF;
                        int gg = (rgb >> 8) & 0xFF;
                        int bb = rgb & 0xFF;

                        int factor = KERNEL[ky + 1][kx + 1];
                        r += rr * factor;
                        g += gg * factor;
                        b += bb * factor;
                    }
                }

                // clamp values to [0, 255]
                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                int newRgb = new Color(r, g, b).getRGB();
                sharpenedImage.setRGB(x, y, newRgb);
            }
        }

        return sharpenedImage;
    }
}
