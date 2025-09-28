package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class EmbossFilter implements ImageFilter {

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage embossedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // The emboss convolution kernel
        float[] kernel = {
                -2, -1, 0,
                -1,  1, 1,
                0,  1, 2
        };

        // We iterate from 1 to width-1 and 1 to height-1 to avoid the edges
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {

                float sumR = 0, sumG = 0, sumB = 0;
                int kernelIndex = 0;

                // Apply the convolution kernel to the 3x3 neighborhood
                for (int row = -1; row <= 1; row++) {
                    for (int col = -1; col <= 1; col++) {
                        int rgb = originalImage.getRGB(x + col, y + row);
                        float k = kernel[kernelIndex++];

                        sumR += ((rgb >> 16) & 0xFF) * k;
                        sumG += ((rgb >> 8) & 0xFF) * k;
                        sumB += (rgb & 0xFF) * k;
                    }
                }

                // Add 128 to shift the result into the visible gray range
                int r = (int) sumR + 128;
                int g = (int) sumG + 128;
                int b = (int) sumB + 128;

                // Clamp the values
                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                // For a classic emboss effect, convert to grayscale
                int gray = (r + g + b) / 3;

                int newRgb = new Color(gray, gray, gray).getRGB();
                embossedImage.setRGB(x, y, newRgb);
            }
        }
        return embossedImage;
    }
}
