package rewucki.michal.new_world.image;

import lombok.experimental.UtilityClass;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.util.Optional;

@UtilityClass
public class TemplateMatcher {

    public static Optional<Rectangle> findMatch(Mat inputImage, Mat templateImage, double matchQuality) {
        int resultCols = inputImage.cols() - templateImage.cols() + 1;
        int resultRows = inputImage.rows() - templateImage.rows() + 1;
        Mat result = new Mat(resultRows, resultCols, CvType.CV_8UC1);

        Imgproc.matchTemplate(inputImage, templateImage, result, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

        if (minMaxLocResult.maxVal >= matchQuality) {
            Rectangle rectangle = new Rectangle((int) minMaxLocResult.maxLoc.x,
                    (int) minMaxLocResult.minLoc.y,
                    templateImage.width(),
                    templateImage.height());
            return Optional.of(rectangle);
        }
        return Optional.empty();
    }
}
