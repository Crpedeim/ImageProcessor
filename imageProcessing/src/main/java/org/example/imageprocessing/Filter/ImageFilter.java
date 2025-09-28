package org.example.imageprocessing.Filter;

import java.awt.image.BufferedImage;

public interface ImageFilter {

    BufferedImage filter(BufferedImage image);
}
