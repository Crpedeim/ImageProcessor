package org.example.imageprocessing.IO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageSaver {

    /**
     * Saves a BufferedImage to a file.
     *
     * @param image The BufferedImage to save.
     * @param outputPath The full path for the output file (e.g., "C:/images/output.png").
     * @return true if the image was saved successfully, false otherwise.
     */
    public boolean saveImageToFile(BufferedImage image, String outputPath) {
        if (image == null || outputPath == null || outputPath.trim().isEmpty()) {
            System.err.println("Invalid input provided.");
            return false;
        }

        try {
            // Extract the format from the file extension
            String formatName = outputPath.substring(outputPath.lastIndexOf('.') + 1);

            // Create a File object
            File outputFile = new File(outputPath);

            // Ensure the parent directory exists
            outputFile.getParentFile().mkdirs();

            // Write the image to the file
            boolean success = ImageIO.write(image, formatName, outputFile);

            if (success) {
                System.out.println("Image saved successfully to: " + outputPath);
            } else {
                System.err.println("Failed to save image. No appropriate writer found for format: " + formatName);
            }
            return success;

        } catch (IOException e) {
            System.err.println("An error occurred while saving the image: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
