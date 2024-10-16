package com.DDT.pushBox;

import java.util.Random;

public abstract class Item {
    private int x;
    private int y;

    public Item(int n, int m) {
        //随机生成箱子的坐标
        Random random = new Random();
        x = random.nextInt(1, n - 1);
        y = random.nextInt(1, m - 1);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
