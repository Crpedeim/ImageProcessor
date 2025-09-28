package org.example.imageprocessing.IO;

import java.awt.image.BufferedImage;

public interface ImageReadInf {

   public  <T> BufferedImage read(T source);
   public void saveImage(BufferedImage image);


}
