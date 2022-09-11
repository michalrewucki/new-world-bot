package rewucki.michal.new_world.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@Getter
@RequiredArgsConstructor
public class ScreenshotEvent {
    private final BufferedImage screenshot;
}
