package rewucki.michal.new_world.screen;

import com.google.common.eventbus.EventBus;
import lombok.RequiredArgsConstructor;
import rewucki.michal.new_world.events.ScreenshotEvent;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class ScreenReader {
    private final EventBus eventBus;
    private final Robot robot;

    public void read() {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = robot.createScreenCapture(screenRect);
        eventBus.post(new ScreenshotEvent(capture));
    }
}
