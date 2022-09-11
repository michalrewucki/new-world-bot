package rewucki.michal.new_world.image;

import lombok.experimental.UtilityClass;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@UtilityClass
public class BufferedImageConverter {

    public static Mat toMatImage(BufferedImage bufferedImage) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_GRAYSCALE);
        }
    }

    public static BufferedImage toBufferedImage(Mat mat) {
        BufferedImage bufferedImage = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_BYTE_GRAY);

        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, data);
        return bufferedImage;
    }
}
