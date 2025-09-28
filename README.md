# ImageProcessor

A Java-based image processing application built with JavaFX that provides various image filters and effects with parallel processing capabilities.

## 🚀 Features

### Image Filters
- **Grayscale Conversion** - Convert color images to grayscale
- **Edge Detection** - Sobel edge detection algorithm
- **Gaussian Blur** - Smoothing filter with Gaussian kernel
- **Brightness Adjustment** - Adjust image brightness levels
- **Contrast Enhancement** - Improve image contrast
- **Sepia Tone** - Apply vintage sepia effect
- **Sharpen Filter** - Enhance image sharpness
- **Emboss Effect** - Create embossed appearance
- **Invert Colors** - Invert image colors
- **Threshold** - Binary threshold conversion
- **Adaptive Threshold** - Dynamic threshold based on local areas

### Key Capabilities
- **Parallel Processing** - Multi-threaded image processing for improved performance
- **Sub-image Processing** - Process images in smaller chunks for better memory management
- **Real-time Visualization** - Live preview of processed image segments
- **Multiple Format Support** - Read and save various image formats (JPEG, PNG, etc.)
- **JavaFX GUI** - Modern graphical user interface

## 🛠️ Technology Stack

- **Java 24** - Core programming language
- **JavaFX 21.0.6** - GUI framework
- **Maven** - Build and dependency management
- **AWT BufferedImage** - Image processing backend
- **Multi-threading** - Parallel processing with ExecutorService

## 📋 Prerequisites

- Java 24 or higher
- Maven 3.6 or higher
- JavaFX runtime (included in dependencies)

## 🚀 Getting Started

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd imageProcessing
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

### Alternative Running Methods

**Using Maven Wrapper (Windows):**
```bash
./mvnw.cmd javafx:run
```

**Using Maven Wrapper (Unix/Linux/Mac):**
```bash
./mvnw javafx:run
```

**Direct Java execution:**
```bash
java --module-path <javafx-path> --add-modules javafx.controls,javafx.fxml -cp target/classes org.example.imageprocessing.HelloApplication
```

## 📁 Project Structure

```
src/main/java/org/example/imageprocessing/
├── Filter/                    # Image filter implementations
│   ├── ImageFilter.java       # Filter interface
│   ├── GreyScaleFilter.java   # Grayscale conversion
│   ├── EdgeDetectionFilter.java # Sobel edge detection
│   ├── GaussianBlurFilter.java # Gaussian blur
│   ├── BrightnessFilter.java  # Brightness adjustment
│   ├── ContrastFilter.java    # Contrast enhancement
│   ├── SepiaFilter.java       # Sepia tone effect
│   ├── SharpenFilter.java     # Sharpening filter
│   ├── EmbossFilter.java      # Emboss effect
│   ├── InvertFilter.java      # Color inversion
│   ├── ThresholdFilter.java   # Binary threshold
│   └── AdaptiveThresholdFilter.java # Adaptive threshold
├── Image/
│   ├── ImageData.java         # Image data container
│   └── test.jpg, test2.jpg    # Sample images
├── ImageProcessor/
│   ├── ImageProcessor.java    # Main processing logic
│   └── DrawMultipleImagesOnCanvas.java # GUI canvas management
├── IO/
│   ├── FileImageRead.java     # Image reading interface
│   ├── ImageReadInf.java      # Reading interface definition
│   └── ImageSaver.java        # Image saving functionality
├── HelloApplication.java      # Main JavaFX application
└── Launcher.java             # Application launcher
```

## 🔧 Usage

### Basic Usage

The application processes images by:
1. Loading an image from the specified path
2. Dividing it into sub-images for parallel processing
3. Applying the selected filter to each sub-image
4. Displaying the results in real-time on the JavaFX canvas
5. Saving the final processed image to the output directory

### Customizing Filters

To use a different filter, modify the `HelloApplication.java` file:

```java
// Change this line to use a different filter
ImageFilter imageFilter = new GreyScaleFilter(); // or any other filter
```

### Available Filters

```java
// Basic filters
new GreyScaleFilter()
new InvertFilter()
new SepiaFilter()

// Enhancement filters
new BrightnessFilter()
new ContrastFilter()
new SharpenFilter()

// Blur filters
new GaussianBlurFilter()

// Edge detection
new EdgeDetectionFilter()

// Special effects
new EmbossFilter()

// Threshold filters
new ThresholdFilter()
new AdaptiveThresholdFilter()
```

## ⚙️ Configuration

### Image Paths
Update the image paths in `HelloApplication.java`:
```java
// Input image path
BufferedImage image = imageIO.read("path/to/your/image.jpg");

// Output directory
String outputPath = "path/to/output/directory/outputImage" + formattedDateTime + ".png";
```

### Processing Parameters
Adjust processing parameters in `HelloApplication.java`:
```java
// Number of sub-images (10x10 grid)
processor.processImage(image, 10, imageFilter, drawMultipleImagesOnCanvas);
```

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

## 📦 Dependencies

The project uses the following main dependencies:
- **JavaFX Controls** (21.0.6) - GUI components
- **JavaFX FXML** (21.0.6) - FXML support
- **JavaFX Web** (21.0.6) - Web component support
- **JavaFX Swing** (21.0.6) - Swing integration
- **JavaFX Media** (21.0.6) - Media support
- **ControlsFX** (11.2.1) - Additional UI controls
- **JUnit Jupiter** (5.12.1) - Testing framework

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🐛 Known Issues

- Hard-coded file paths in the main application
- Limited error handling for invalid image formats
- No user interface for filter selection (currently code-based)

## 🔮 Future Enhancements

- [ ] Interactive GUI for filter selection
- [ ] Batch processing capabilities
- [ ] More advanced filters (bilateral, morphological operations)
- [ ] Image format conversion utilities
- [ ] Performance optimization for large images
- [ ] Plugin system for custom filters

## 📞 Support

If you encounter any issues or have questions, please open an issue on the GitHub repository.

---

**Note**: This application requires JavaFX runtime. Make sure you have the appropriate JavaFX modules available in your Java installation or use the provided Maven configuration.

