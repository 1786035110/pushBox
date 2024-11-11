package com.DDT.pushBox.bean;

import java.util.Random;

public abstract class Item {
    private int x;
    private int y;
    private String type;

    public Item(int n, int m) {
        //随机生成坐标
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

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }
}

