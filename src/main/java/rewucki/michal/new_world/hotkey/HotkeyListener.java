package rewucki.michal.new_world.hotkey;

import com.google.common.eventbus.EventBus;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rewucki.michal.new_world.Module;
import rewucki.michal.new_world.events.StopEvent;

@RequiredArgsConstructor
@Slf4j
public class HotkeyListener implements Module {
    private WinUser.HHOOK hhk;
    private final EventBus eventBus;
    private final User32 lib = User32.INSTANCE;

    @Override
    public void setup() {
        eventBus.register(this);

        WinDef.HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        WinUser.LowLevelKeyboardProc keyboardHook = (nCode, wParam, info) -> {
            if (nCode >= 0) {
                switch(wParam.intValue()) {
                    case WinUser.WM_KEYUP:
                    case WinUser.WM_KEYDOWN:
                    case WinUser.WM_SYSKEYUP:
                    case WinUser.WM_SYSKEYDOWN:
                        if (info.vkCode == 81) {
                            eventBus.post(new StopEvent());
                        }
                }
            }

            Pointer ptr = info.getPointer();
            long peer = Pointer.nativeValue(ptr);
            return lib.CallNextHookEx(hhk, nCode, wParam, new WinDef.LPARAM(peer));
        };
        hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
        log.info("Keyboard hook installed, type 'q' to quit");
    }

    @Override
    public void teardown() {
        eventBus.unregister(this);
        lib.UnhookWindowsHookEx(hhk);
    }
}
