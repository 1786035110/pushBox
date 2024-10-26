package com.DDT.pushBox;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class Keydown {

    // 加载User32库，调用系统API
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);
        short GetAsyncKeyState(int vKey);
    }

    // 获取按键
    public char returnKey() throws InterruptedException {
        for (int key = 8; key <= 255; key++) {  // 循环检测所有可能的按键
            if (User32.INSTANCE.GetAsyncKeyState(key) == -32767) {  // 检测是否有按键按下
                char ch = (char) key;

                if (ch == '&') ch = 'w';
                if (ch == '(') ch = 's';
                if (ch == '%') ch = 'a';
                if (ch == '\'') ch = 'd';

                //通过打印换行，实现控制台刷新
                System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                return ch;
            }
        }
        try {
            Thread.sleep(100);  // 稍作延迟，避免占用过多CPU
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return returnKey();
    }

}
