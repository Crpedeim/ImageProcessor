package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class EdgeDetectionFilter implements ImageFilter {

    // Sobel kernels
    private static final int[][] SOBEL_X = {
            { -1, 0, 1 },
            { -2, 0, 2 },
            { -1, 0, 1 }
    };

    private static final int[][] SOBEL_Y = {
            { -1, -2, -1 },
            {  0,  0,  0 },
            {  1,  2,  1 }
    };

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage edgeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int gx = 0;
                int gy = 0;

                // Apply Sobel kernels
                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int rgb = originalImage.getRGB(x + kx, y + ky);
                        int gray = toGray(rgb);

                        gx += SOBEL_X[ky + 1][kx + 1] * gray;
                        gy += SOBEL_Y[ky + 1][kx + 1] * gray;
                    }
                }

                // Gradient magnitude
                int magnitude = (int)Math.min(255, Math.sqrt(gx * gx + gy * gy));

                int newRgb = new Color(magnitude, magnitude, magnitude).getRGB();
                edgeImage.setRGB(x, y, newRgb);
            }
        }

        return edgeImage;
    }

    private int toGray(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
    }
}

