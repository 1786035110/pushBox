package com.DDT.pushBox;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Background background = new Background(10, 10);
        background.display();
        while (true) {
            background.update_move();
            background.move(background.add_listening_events());

        }

    }
}


