package com.DDT.pushBox;

import com.DDT.pushBox.Box.LargeBox;
import com.DDT.pushBox.Box.MiddleBox;
import com.DDT.pushBox.Box.SmallBox;
import com.DDT.pushBox.Player.Gablin;
import com.DDT.pushBox.Player.Orcish;
import com.DDT.pushBox.bean.Box;
import com.DDT.pushBox.bean.Item;
import com.DDT.pushBox.bean.Player;
import com.DDT.pushBox.bean.Target;

import java.util.Scanner;


public class Background {
    private final String[][] map;
    private final Player player;
    private final Box box;
    private final Target target;

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

        System.out.println("Choose your character: \n1. Gablin \n2. Orcish");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("Choose your box: \n1. Large \n2. Middle \n3. Small");
        int y = sc.nextInt();

        player = chooseCharacter(x, n, m);
        map[player.getX()][player.getY()] = player.getType();

        target = new Target(n, m, player.getX(), player.getY());
        map[target.getX()][target.getY()] = target.getType();

        box = chooseBox(y, n, m);
        map[box.getX()][box.getY()] = box.getType();
    }

    public Player chooseCharacter (int x, int n, int m) {
        switch (x) {
            case 1 -> {
                return new Gablin(n, m);
            }
            case 2 -> {
                return new Orcish(n, m);
            }
        }
        return null;
    }

    public Box chooseBox(int y, int n, int m) {
        switch (y) {
            case 1 -> {
                return new LargeBox(n, m, player.getX(), player.getY(), target.getX(), target.getY());
            }
            case 2 -> {
                return new MiddleBox(n, m, player.getX(), player.getY(), target.getX(), target.getY());
            }
            case 3 -> {
                return new SmallBox(n, m, player.getX(), player.getY(), target.getX(), target.getY());
            }
        }
        return null;
    }

    //打印地图
    public void display() {
        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
        System.out.println("Enter your move (W(up), S(down), A(left), D(right))");
    }

    //检查路径上是否有障碍
    public boolean check_wall(Item item, char dir) {
        switch (dir) {
            case 'w' :
                if(item.getClass().equals(box.getClass())) return map[item.getX() - 1][item.getY()].equals(" ") || map[box.getX() - 1][box.getY()].equals(target.getType());
                    else return map[item.getX() - 1][item.getY()].equals(" ");        case 's' :
                if(item.getClass().equals(box.getClass())) return map[item.getX() + 1][item.getY()].equals(" ") || map[box.getX() + 1][box.getY()].equals(target.getType());
                    else return map[item.getX() + 1][item.getY()].equals(" ");
            case 'a' :
                if(item.getClass().equals(box.getClass())) return map[item.getX()][item.getY() - 1].equals(" ") || map[box.getX()][box.getY() - 1].equals(target.getType());
                    else return map[item.getX()][item.getY() - 1].equals(" ");
            case 'd' :
                if(item.getClass().equals(box.getClass())) return map[item.getX()][item.getY() + 1].equals(" ") || map[box.getX()][box.getY() + 1].equals(target.getType());
                    else return map[item.getX()][item.getY() + 1].equals(" ");
            default :  return false;
        }
    }

    //检查路径上是否有箱子
    public boolean check_box(Player player,char dir) {
        return switch (dir) {
            case 'w' -> map[player.getX() - 1][player.getY()].equals(box.getType());
            case 's' -> map[player.getX() + 1][player.getY()].equals(box.getType());
            case 'a' -> map[player.getX()][player.getY() - 1].equals(box.getType());
            case 'd' -> map[player.getX()][player.getY() + 1].equals(box.getType());
            default -> false;
        };
    }

    public void is_win() {
        if (map[target.getX()][target.getY()].equals(box.getType())) {
            System.out.println("YOU WIN");
            System.exit(0);
        }
    }
    public String[][] getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public Box getBox() {
        return box;
    }


}