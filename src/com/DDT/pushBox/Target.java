package com.DDT.pushBox;

import com.DDT.pushBox.bean.Item;

import java.util.Random;

public class Target extends Item {

    public Target(int n, int m, int player_x, int player_y) {
        super(n, m);

        //随机生成箱子的坐标
        Random random = new Random();
        int random_x = player_x, random_y = player_y;

        //判断是否与人物重合
        while(random_x == player_x && random_y == player_y) {
            random_x = random.nextInt(1, n - 1);
            random_y = random.nextInt(1, m - 1);
        }
        setX(random_x);
        setY(random_y);
    }
}
