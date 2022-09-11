package rewucki.michal.new_world.music;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import rewucki.michal.new_world.Module;
import rewucki.michal.new_world.events.ScreenshotEvent;
import rewucki.michal.new_world.events.SendKeyEvent;
import rewucki.michal.new_world.image.BufferedImageConverter;
import rewucki.michal.new_world.image.TemplateMatcher;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import static rewucki.michal.new_world.music.MusicGraphics.*;
import static rewucki.michal.new_world.music.MusicGraphics.MOUSE;

@Slf4j
@RequiredArgsConstructor
public class MusicBot implements Module {
    private static final List<MusicGraphics> NOTES = Lists.newArrayList(A, S, D, W, SPACE, MOUSE);
    private final EventBus eventBus;
    private final Rectangle NOTE_TO_PLAY_RECTANGLE = new Rectangle(566, 764, 76, 200);

    @Override
    public void setup() {
        eventBus.register(this);
    }

    @Override
    public void teardown() {
        eventBus.unregister(this);
    }

    @Subscribe
    public void onScreenshotEvent(ScreenshotEvent screenshotEvent) throws IOException {
        BufferedImage captureNoteToPlay = screenshotEvent.getScreenshot().getSubimage(NOTE_TO_PLAY_RECTANGLE.x, NOTE_TO_PLAY_RECTANGLE.y, NOTE_TO_PLAY_RECTANGLE.width, NOTE_TO_PLAY_RECTANGLE.height);
        Mat mat = BufferedImageConverter.toMatImage(captureNoteToPlay);

        for (MusicGraphics note : NOTES) {
            if (TemplateMatcher.findMatch(mat, note.getImage(), 0.65).isPresent()) {
                System.out.println("Found note: " + note.name() + " " + System.currentTimeMillis());
                switch (note) {
                    case D:
                        eventBus.post(SendKeyEvent.keyboardEvents(KeyEvent.VK_D));
                        break;
                    case W:
                        eventBus.post(SendKeyEvent.keyboardEvents(KeyEvent.VK_W));
                        break;
                    case A:
                        eventBus.post(SendKeyEvent.keyboardEvents(KeyEvent.VK_A));
                        break;
                    case S:
                        eventBus.post(SendKeyEvent.keyboardEvents(KeyEvent.VK_S));
                        break;
                    case MOUSE:
                        eventBus.post(SendKeyEvent.mouseEvents(MouseEvent.BUTTON1_DOWN_MASK, MouseEvent.BUTTON3_DOWN_MASK));
                        break;
                    case SPACE:
                        eventBus.post(SendKeyEvent.keyboardEvents(KeyEvent.VK_SPACE));
                        break;
                }
                ImageIO.write(captureNoteToPlay, "png", new File("D:/" + System.currentTimeMillis()+".png"));
                break;
            }
        }
    }

}
