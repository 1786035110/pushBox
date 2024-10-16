package com.DDT.pushBox;

public class Background {
    private final String[][] map;
    private final Man man;
    private final Box box;
    private int man_x;
    private int man_y;
    private int box_x;
    private int box_y;
    Keydown keydown = new Keydown();

    public Background(int n, int m) {
        map = new String[n][m];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                 if (i == 0 || j == 0 || i == map.length - 1 || j == map[0].length - 1) {
                        map[i][j] = "#";
                 } else {
                        map[i][j] = " ";
                 }
            }
        }

        man = new Man(n, m);
        map[man.getX()][man.getY()] = "o";
        man_x = man.getX();
        man_y = man.getY();

        box = new Box(n, m, man.getX(), man.getY());
        map[box.getX()][box.getY()] = "x";
        box_x = box.getX();
        box_y = box.getY();
    }

    public void update_move() {
        map[man_x][man_y] = " ";
        map[box_x][box_y] = " ";

        map[man.getX()][man.getY()] = "o";
        map[box.getX()][box.getY()] = "x";

        man_x = man.getX();
        man_y = man.getY();
        box_x = box.getX();
        box_y = box.getY();
    }

    public void display() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public String[][] getMap() {
        return map;
    }

    //检查路径上是否有障碍
    public boolean check_wall(Item item, char dir) {
        return !switch (dir) {
            case 'w' -> map[item.getX() - 1][item.getY()].equals("#");
            case 's' -> map[item.getX() + 1][item.getY()].equals("#");
            case 'a' -> map[item.getX()][item.getY() - 1].equals("#");
            case 'd' -> map[item.getX()][item.getY() + 1].equals("#");
            default -> false;
        };
    }

    //加入按键监听事件，转化为字符判断方向
    public char add_listening_events() throws InterruptedException {
        char key = keydown.returnKey();

        if (key == 'w' || key == 'W') {
            return 'w';
        } else if (key == 's' || key == 'S') {
            return 's';
        } else if (key == 'a' || key == 'A') {
            return 'a';
        } else if (key == 'd' || key == 'D') {
            return 'd';
        }

        try {
            Thread.sleep(50);  // 稍作延迟，避免占用过多CPU
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ' ';
    }

    //人物实现移动
    public void move(char dir) {
        switch (dir) {
            case 'w' : if (check_wall(man, 'w')) man.setX(man.getX() - 1); break;
            case 's' : if (check_wall(man, 's')) man.setX(man.getX() + 1); break;
            case 'a' : if (check_wall(man, 'a')) man.setY(man.getY() - 1); break;
            case 'd' : if (check_wall(man, 'd')) man.setY(man.getY() + 1); break;
            default : break;
        }

        update_move();
        display();
    }

    public void push() {

    }
}