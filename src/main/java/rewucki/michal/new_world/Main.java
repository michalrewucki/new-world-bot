package rewucki.michal.new_world;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.sun.jna.platform.win32.GDI32Util;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import rewucki.michal.new_world.image.BufferedImageConverter;
import rewucki.michal.new_world.image.TemplateMatcher;
import rewucki.michal.new_world.image.TextReader;
import rewucki.michal.new_world.music.MusicGraphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static rewucki.michal.new_world.music.MusicGraphics.*;

@Slf4j
public class Main {
    static {
        OpenCV.loadShared();
    }

    public static void main(String[] args) throws AWTException, IOException, TesseractException, URISyntaxException, InterruptedException {
        Application application = new Application();
        application.start();

//        q
//        User32 user32 = User32.INSTANCE;
//        user32.GetActiveWindow();
//        HWND hWnd = user32.FindWindow(null, "Sticky Notes"); // Sets focus to my opened 'Downloads' folder
//        user32.ShowWindow(hWnd, User32.SW_SHOW);
//        user32.SetForegroundWindow(hWnd);
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//        ExecutorService executorService1 = Executors.newCachedThreadPool();
//        Thread thread = new Thread(new HotkeyListener());
//        thread.start();

//
//        Robot robot = new Robot();
//
//        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        BufferedImage capture = robot.createScreenCapture(screenRect);

//        User32 user32 = User32.INSTANCE;
//        HWND hWnd = user32.FindWindow(null, "New World"); // Sets focus to my opened 'Downloads' folder

//        ImageIO.write(capture, "bmp", new File("D:\\output.bmp"));
//        BufferedImage src = ImageIO.read(new File("D:\\src.png"));
//        BufferedImage target = ImageIO.read(new File("D:\\target.png"));
//
//        WinDef.HWND hwnd = User32.INSTANCE.GetDesktopWindow();
//        BufferedImage src = GDI32Util.getScreenshot(hwnd);
//
//        Mat original = BufferedImageConverter.toMatImage(src);


//        Mat grey = new Mat();
//        Imgproc.cvtColor(original, grey, Imgproc.COLOR_RGB2GRAY, 0);
//
//        ImageIO.write(BufferedImageConverter.toBufferedImage(grey), "png", new File("D:/grey.png"));
//
//
//        Mat binary = new Mat();
//        Imgproc.threshold(grey,binary, 0,255, Imgproc.THRESH_BINARY_INV+Imgproc.THRESH_OTSU);
//
//        ImageIO.write(BufferedImageConverter.toBufferedImage(binary), "png", new File("D:/binary.png"));
//
//        //Creating destination matrix
//        Mat dst = new Mat(binary.rows(), binary.cols(), binary.type());
//        //Preparing the kernel matrix object
//        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2));
//        //Applying erosion on the Image
//        Imgproc.erode(binary, dst, kernel);
//
//        ImageIO.write(BufferedImageConverter.toBufferedImage(dst), "png", new File("D:/erode.png"));
//
//        Mat blur = new Mat();
//        Imgproc.GaussianBlur(dst, blur, new Size(3, 3), 0);
//        ImageIO.write(BufferedImageConverter.toBufferedImage(dst), "png", new File("D:/blur.png"));

//
//        String text = TextReader.readText(BufferedImageConverter.toBufferedImage(small));
//        System.out.println(text);
//        try (IplImage sourceIplImage = BufferedImageConverter.toIplImage(src);
//             IplImage targetIplImage = BufferedImageConverter.toIplImage(target);
//             IplImage result = cvCreateImage(cvSize(sourceIplImage.width()-targetIplImage.width()+1, sourceIplImage.height()-targetIplImage.height()+1), IPL_DEPTH_32F, 1)) {
//            cvZero(result);
//
//            cvMatchTemplate(sourceIplImage, targetIplImage, result, CV_TM_CCORR_NORMED);
//            double[] min_val = new double[2];
//            double[] max_val = new double[2];
//            DoublePointer min_valPointer = new DoublePointer(min_val);
//            DoublePointer max_valPointer = new DoublePointer(max_val);
//
//            CvPoint minLoc = new CvPoint();
//            CvPoint maxLoc = new CvPoint();
//
//            //Get the Max or Min Correlation Value
//            cvMinMaxLoc(result, min_valPointer, max_valPointer, minLoc, maxLoc, null);
//
//            System.out.println(Arrays.toString(min_val));
//            System.out.println(Arrays.toString(max_val));
//
//            CvPoint point = new CvPoint();
//            point.x(maxLoc.x()+targetIplImage.width());
//            point.y(maxLoc.y()+targetIplImage.height());
//
//            cvRectangle(sourceIplImage, maxLoc, point, CvScalar.WHITE, 2, 8, 0);//Draw a Rectangle for Matched Region
//
//            cvShowImage("Lena Image", sourceIplImage);
//            cvWaitKey(0);
//            cvReleaseImage(sourceIplImage);
//            cvReleaseImage(targetIplImage);
//            cvReleaseImage(result);
//        }

    }
}
