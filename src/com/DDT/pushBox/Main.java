package com.DDT.pushBox;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Background background = new Background(10, 10);
        Control control = new Control(background);
        control.run();
    }
}


