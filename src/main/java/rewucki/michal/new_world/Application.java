package rewucki.michal.new_world;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import rewucki.michal.new_world.events.StopEvent;
import rewucki.michal.new_world.hotkey.HotkeySender;
import rewucki.michal.new_world.music.MusicBot;
import rewucki.michal.new_world.screen.ScreenReader;

import java.awt.*;
import java.util.List;

@Slf4j
public class Application {
    private final EventBus eventBus = new EventBus();
    private final ScreenReader screenReader;
    private final List<Module> modules = Lists.newArrayList();

    private boolean stop = false;

    public Application() throws AWTException {
        eventBus.register(this);

        Robot robot = new Robot();
        screenReader = new ScreenReader(eventBus, robot);
//        modules.add(new HotkeyListener(eventBus));
        modules.add(new HotkeySender(eventBus, robot));
        modules.add(new MusicBot(eventBus));
    }

    public void start() throws InterruptedException {
        log.info("Loading modules");
        modules.forEach(Module::setup);
        log.info("Modules loaded");

        while(!stop) {
            Thread.sleep(20);
            screenReader.read();
        }

        log.info("Tearing down modules");
        modules.forEach(Module::teardown);
    }

    @Subscribe
    public void onStopEvent(StopEvent stopEvent) {
        stop = true;
    }
}
