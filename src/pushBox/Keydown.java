package pushBox;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class Keydown {

    // 加载User32库，调用系统API
    public static interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);

        short GetAsyncKeyState(int vKey);
    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            for (int key = 8; key <= 255; key++) {  // 循环检测所有可能的按键
                if (User32.INSTANCE.GetAsyncKeyState(key) == -32767) {  // 检测是否有按键按下
                    char ch = (char) key;
                    System.out.println("You pressed: " + ch);
                    if (ch == 'Q' || ch == 'q') {  // 按下 'Q' 键退出
                        System.out.println("Exiting...");
                        return;
                    }
                }
            }
            Thread.sleep(50);  // 稍作延迟，避免占用过多CPU
        }
    }
}
