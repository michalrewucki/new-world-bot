package rewucki.michal.new_world.image;

import lombok.experimental.UtilityClass;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {
    private final Tesseract tesseract;

    public TextReader() throws URISyntaxException {
//        BufferedImage src = ImageIO.read(new File("D:\\untitled.png"));
//
//        src = src.getSubimage(src.getWidth() / 2, src.getHeight() / 2, src.getWidth() /2, src.getHeight() / 2);
//
//        Mat mat = BufferedImageConverter.toMatImage(src);
//
//        Imgproc.resize(mat, mat,  new Size(src.getWidth() * 3, src.getHeight() * 3), 3, 3, Imgproc.INTER_CUBIC);
//
//        Imgproc.adaptiveThreshold(mat, mat, 255,
//                Imgproc.ADAPTIVE_THRESH_MEAN_C,
//                Imgproc.THRESH_BINARY_INV, 51, 4);
//        ImageIO.write(BufferedImageConverter.toBufferedImage(mat), "png", new File("D://output.png"));
//
//        TextReader textReader = new TextReader();
//        String text = textReader.readText(src);
//        System.out.println(text);


        tesseract = new Tesseract();
        tesseract.setLanguage("eng");
        tesseract.setOcrEngineMode(1);
        Path dataDirectory = Paths.get(ClassLoader.getSystemResource("tesseract").toURI());
        tesseract.setDatapath(dataDirectory.toString());
    }

    public String readText(BufferedImage bufferedImage) throws TesseractException, URISyntaxException, IOException {
        preprocessImage(bufferedImage);

        File tmpFolder = LoadLibs.extractTessResources("win32-x86-64");
        System.setProperty("java.library.path", tmpFolder.getPath());



        return tesseract.doOCR(bufferedImage);
    }

    private void preprocessImage(BufferedImage bufferedImage) throws IOException {


    }
}
