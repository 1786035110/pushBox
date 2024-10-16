package pushBox;

import java.util.Random;

public class Man {
    private int x;
    private int y;

    public Man(int n, int m) {
        //随机生成人物的坐标
        Random random = new Random();
        x = random.nextInt(1, n - 1);
        y = random.nextInt(1, m - 1);
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
