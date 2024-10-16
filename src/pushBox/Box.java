package pushBox;

import java.util.Random;

public class Box {
    private int x;
    private int y;

    public Box(int n, int m, int man_x, int man_y) {
        //随机生成箱子的坐标
        Random random = new Random();
        int x = man_x, y = man_y;

        //判断是否与人物重合
        while(x == man_x && y == man_y) {
            x = random.nextInt(1, n - 1);
            y = random.nextInt(1, m - 1);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
