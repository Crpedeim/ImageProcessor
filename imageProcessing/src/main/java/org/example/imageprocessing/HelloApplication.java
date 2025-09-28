package org.example.imageprocessing;

import javafx.application.Application;

import javafx.stage.Stage;
import org.example.imageprocessing.Filter.*;
import org.example.imageprocessing.IO.FileImageRead;
import org.example.imageprocessing.IO.ImageReadInf;
import org.example.imageprocessing.IO.ImageSaver;
import org.example.imageprocessing.ImageProcessor.DrawMultipleImagesOnCanvas;
import org.example.imageprocessing.ImageProcessor.ImageProcessor;
import javafx.application.Platform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        DrawMultipleImagesOnCanvas drawMultipleImagesOnCanvas = DrawMultipleImagesOnCanvas.getInstance();
        drawMultipleImagesOnCanvas.initialize(stage);

        ImageReadInf imageIO = new FileImageRead();
        ImageSaver imageSaver = new ImageSaver();
        BufferedImage image = imageIO.read("C:\\Users\\shaur\\Desktop\\imageProcessing\\src\\main\\java\\org\\example\\imageprocessing\\Image\\test2.jpg");
        ImageProcessor processor = new ImageProcessor();
        ImageFilter imageFilter = new SharpenFilter();


        // processing subimages and drawing them
        processor.processImage(image, 10, imageFilter, drawMultipleImagesOnCanvas);

        Platform.setImplicitExit(false);


// saving the image to file


        BufferedImage outputImg = imageFilter.filter(image);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String outputPath = "C:\\Users\\shaur\\Desktop\\imageProcessing\\Images" + File.separator + "outputImage"+formattedDateTime+".png";

        imageSaver.saveImageToFile(outputImg,outputPath);

        // applying filter to image and saving it to file




    }

    public static void main(String[] args) {
        launch();
    }
}
