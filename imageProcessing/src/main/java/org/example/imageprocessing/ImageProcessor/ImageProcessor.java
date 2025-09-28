package org.example.imageprocessing.ImageProcessor;

import org.example.imageprocessing.Filter.ImageFilter;
import org.example.imageprocessing.Image.ImageData;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageProcessor {

    private ExecutorService executorService;
//    private DrawMultipleImagesOnCanvas drawfn;

   public ImageProcessor(){
        executorService = Executors.newFixedThreadPool(100);

//        drawfn = DrawMultipleImagesOnCanvas.getInstance();
    }

    public void processImage(BufferedImage image, int num, ImageFilter Filter, DrawMultipleImagesOnCanvas drawfn){
        int numHorizontalImages = image.getWidth() / num;
        int numVerticalImages = image.getHeight()/num;

        List<Future<ImageData>> futures = new ArrayList<>();

        for(int i = 0 ; i < numHorizontalImages ; i++){

            for(int j = 0 ; j < numVerticalImages ; j++){

                BufferedImage subImage = image.getSubimage(i*num,j*num,num,num);
                int finalI = i;
                int finalJ = j;

                Future<ImageData> bufferedImageFuture = executorService.submit(new Callable<ImageData>() {
                    @Override
                    public ImageData call(){
                        BufferedImage result = Filter.filter(subImage);
                        ImageData Img = new ImageData(result, finalI *num, finalJ *num,num,num);
                        // we were resolving the futures sequentially thats why The image was drawing sequentially
                        // So we have to wait for n-1 future to gte resolved and added to the queue before adding nth future
                        // here we are pushing the image as soon as it is processed
                        drawfn.addImageToQueue(Img);
                        return Img;
                    }
                });

                futures.add(bufferedImageFuture);
            }
        }


        for(Future<ImageData> future : futures){
            try{
                future.get();
            }
           catch(Exception ex){

                System.err.println("Cannot push image data to queue");
                ex.printStackTrace();
           }
        }
    }


}
