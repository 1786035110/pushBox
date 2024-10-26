package com.DDT.pushBox;

public class Control {

    Keydown keydown = new Keydown();
    private final Background background;
    private final String[][] map ;
    private final Player player;
    private final Box box;

    public Control(Background background) {
        this.background = background;
        this.map = background.getMap();
        this.player = background.getPlayer();
        this.box = background.getBox();
    }
    
    //开始游戏
    public void run() throws InterruptedException {
        background.display();
        while (true) {
            char dir = addListeningEvents();
            update_map();
            move(dir);
            push(dir);
            background.is_win();
        }
    }

    //加入按键监听事件，转化为字符判断方向
    public char addListeningEvents() throws InterruptedException {
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

    //更新地图
    public void update_map() {
        map[player.getX()][player.getY()] = "O";
        map[box.getX()][box.getY()] = "X";
    }

    //人物实现移动
    public void move(char dir) throws InterruptedException {
        switch (dir) {
            case 'w' :
                if (background.check_wall(player, 'w')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setX(player.getX() - 1);
                }
                break;
            case 's' :
                if (background.check_wall(player, 's')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setX(player.getX() + 1);
                }
                break;
            case 'a' :
                if (background.check_wall(player, 'a')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setY(player.getY() - 1);
                }
                break;
            case 'd' :
                if (background.check_wall(player, 'd')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setY(player.getY() + 1);
                }
                break;
            default : break;
        }

        update_map();
        background.display();
    }

    //推箱子
    public void push(char dir) throws InterruptedException {
        switch (dir) {
            case 'w' :
                if (background.check_wall(box, 'w') && background.check_box(player, 'w')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setX(player.getX() - 1);
                    box.setX(box.getX() - 1);
                }
                break;
            case 's' :
                if (background.check_wall(box, 's') && background.check_box(player, 's')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setX(player.getX() + 1);
                    box.setX(box.getX() + 1);
                }
                break;
            case 'a' :
                if (background.check_wall(box, 'a') && background.check_box(player, 'a')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setY(player.getY() - 1);
                    box.setY(box.getY() - 1);
                }
                break;
            case 'd' :
                if (background.check_wall(box, 'd') && background.check_box(player, 'd')) {
                    map[player.getX()][player.getY()] = " ";
                    map[box.getX()][box.getY()] = " ";
                    player.setY(player.getY() + 1);
                    box.setY(box.getY() + 1);
                }
                break;
            default : break;
        }

        update_map();
        background.display();
    }
}
