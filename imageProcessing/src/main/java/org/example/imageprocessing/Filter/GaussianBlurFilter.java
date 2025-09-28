package org.example.imageprocessing.Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GaussianBlurFilter implements ImageFilter {

    // 3x3 Gaussian kernel (σ ≈ 1)
    private static final double[][] GAUSSIAN_KERNEL = {
            { 1, 2, 1 },
            { 2, 4, 2 },
            { 1, 2, 1 }
    };

    private static final double KERNEL_SUM = 16.0; // sum of all kernel elements

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage blurredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double r = 0, g = 0, b = 0;

                // Apply kernel
                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int rgb = originalImage.getRGB(x + kx, y + ky);
                        int rr = (rgb >> 16) & 0xFF;
                        int gg = (rgb >> 8) & 0xFF;
                        int bb = rgb & 0xFF;

                        double weight = GAUSSIAN_KERNEL[ky + 1][kx + 1];
                        r += rr * weight;
                        g += gg * weight;
                        b += bb * weight;
                    }
                }

                int newR = (int)Math.min(255, Math.max(0, r / KERNEL_SUM));
                int newG = (int)Math.min(255, Math.max(0, g / KERNEL_SUM));
                int newB = (int)Math.min(255, Math.max(0, b / KERNEL_SUM));

                int newRgb = new Color(newR, newG, newB).getRGB();
                blurredImage.setRGB(x, y, newRgb);
            }
        }

        return blurredImage;
    }
}

