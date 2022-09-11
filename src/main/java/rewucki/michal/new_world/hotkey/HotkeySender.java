package rewucki.michal.new_world.hotkey;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import rewucki.michal.new_world.Module;
import rewucki.michal.new_world.events.SendKeyEvent;

import java.awt.*;
import java.util.Random;

@RequiredArgsConstructor
public class HotkeySender implements Module {
    private final EventBus eventBus;
    private final Robot robot;
    private final Random random = new Random();


    @Override
    public void setup() {
        eventBus.register(this);
    }

    @Subscribe
    public void onHotkeyEvent(SendKeyEvent sendKeyEvent) {
        if (!sendKeyEvent.getMouseEvents().isEmpty()) {
            for (Integer mouseEvent : sendKeyEvent.getMouseEvents()) {
                robot.mousePress(mouseEvent);
                robot.delay(Math.abs(random.nextInt(5) + 5));
            }
            robot.delay(Math.abs(random.nextInt(5) + 5));
            for (Integer mouseEvent : sendKeyEvent.getMouseEvents()) {
                robot.mouseRelease(mouseEvent);
                robot.delay(Math.abs(random.nextInt(2)));
            }
        }

        if (!sendKeyEvent.getKeyboardEvents().isEmpty()) {
            System.out.println("Key pressed: " + System.currentTimeMillis());
            for (Integer keyboardEvent : sendKeyEvent.getKeyboardEvents()) {
                robot.keyPress(keyboardEvent);
                robot.delay(Math.abs(random.nextInt(5) + 2));
            }
            for (Integer mouseEvent : sendKeyEvent.getKeyboardEvents()) {
                robot.keyRelease(mouseEvent);
            }
            System.out.println("Key released: " + System.currentTimeMillis());
        }
        robot.delay(80);
    }

    @Override
    public void teardown() {
        eventBus.unregister(this);
    }
}
