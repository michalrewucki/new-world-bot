package rewucki.michal.new_world.music;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import rewucki.michal.new_world.image.BufferedImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import static org.opencv.imgcodecs.Imgcodecs.imread;

@AllArgsConstructor
@Getter
@Slf4j
public enum MusicGraphics {
    W("music/W.png"),
    S("music/S.png"),
    A("music/A.png"),
    D("music/D.png"),
    MOUSE("music/Mouse.png"),
    SPACE("music/Space.png");

    private Mat image;
    @SneakyThrows
    MusicGraphics(String filePath) {
        System.out.println("Loading music graphics: " + filePath);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(filePath);
        BufferedImage image = ImageIO.read(url);
        this.image = BufferedImageConverter.toMatImage(image);
    }
}
