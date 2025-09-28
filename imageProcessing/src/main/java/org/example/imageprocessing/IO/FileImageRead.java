package org.example.imageprocessing.IO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileImageRead implements ImageReadInf{

    @Override
    public <T> BufferedImage read(T source){
        try{
            String  src = source.toString();

            File imgFile = new File(src);

          return ImageIO.read(imgFile);

        }
        catch (Exception e){
            System.err.println("Could not read file");
            return null;
        }
    }

    @Override
    public void saveImage(BufferedImage src){
        //todo
    }


}
