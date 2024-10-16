package com.DDT.pushBox;

import java.util.Random;

public class Box extends Item{
    private int x;
    private int y;

    public Box(int n, int m, int man_x, int man_y) {
        super(n, m);

        //随机生成箱子的坐标
        Random random = new Random();
        int random_x = man_x, random_y = man_y;

        //判断是否与人物重合
        while(random_x == man_x && random_y == man_y) {
            random_x = random.nextInt(1, n - 1);
            random_y = random.nextInt(1, m - 1);
        }
        this.x = random_x;
        this.y = random_y;
    }
}
