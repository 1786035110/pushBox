package com.DDT.pushBox.bean;

import java.util.Random;

public class Box extends Item {

    public Box(int n, int m, int player_x, int player_y, int target_x, int target_y) {
        super(n, m);

        //随机生成终点的坐标
        Random random = new Random();
        int random_x = random.nextInt(1, n - 1);
        int random_y = random.nextInt(1, m - 1);

        //判断是否与人物重合
        while(random_x == player_x && random_y == player_y
                || random_x == target_x && random_y == target_y
                || !check_correctly(player_x, player_y, random_x, random_y, target_x, target_y, n, m)) {
            random_x = random.nextInt(1, n - 1);
            random_y = random.nextInt(1, m - 1);
        }
        setX(random_x);
        setY(random_y);

    }

    //检查箱子是否能到达终点
    public boolean check_correctly(int player_x, int player_y, int box_x, int box_y, int target_x, int target_y, int n, int m) {
        if(box_y == target_y && box_x > Math.min(player_x, target_x) && box_x < Math.max(player_x, target_x) ||
           box_x == target_x && box_y > Math.min(player_y, target_y) && box_y < Math.max(player_y, target_y)) {
            System.out.println(1);
            return true;
        } else return box_x > 1 && box_x < n - 2 && box_y > 1 && box_y < m - 2;
    }
}
