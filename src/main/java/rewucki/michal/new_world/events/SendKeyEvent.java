package rewucki.michal.new_world.events;

import com.google.common.collect.ImmutableSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class SendKeyEvent {
    private final Set<Integer> mouseEvents;
    private final Set<Integer> keyboardEvents;

    public static SendKeyEvent mouseEvents(Integer... events) {
        return new SendKeyEvent(ImmutableSet.copyOf(events), ImmutableSet.of());
    }

    public static SendKeyEvent keyboardEvents(Integer... events) {
        return new SendKeyEvent(ImmutableSet.of(), ImmutableSet.copyOf(events));
    }
}
