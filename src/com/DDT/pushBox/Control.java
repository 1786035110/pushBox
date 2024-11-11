package com.DDT.pushBox;

import com.DDT.pushBox.bean.Box;
import com.DDT.pushBox.bean.Player;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    public static void login() {
        System.out.println("欢迎进入推箱子游戏");
        ResourceBundle bundle = ResourceBundle.getBundle("com.DDT.pushBox.resources.user");
        String username = bundle.getString("username");
        String password = bundle.getString("password");

        do {
            System.out.print("请输入用户名:");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            if (Objects.equals(name, username)){
                break;
            } else {
                System.out.println("用户名不存在");
            }
        } while (true);

        do {
            System.out.print("请输入密码:");
            Scanner sc = new Scanner(System.in);
            String pwd = sc.nextLine();
            if (Objects.equals(pwd, password)){
                break;
            } else {
                System.out.println("密码错误");
            }
        } while (true);
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
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
        map[player.getX()][player.getY()] = player.getType();
        map[box.getX()][box.getY()] = box.getType();
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
