package com.DDT.pushBox;

import com.DDT.pushBox.bean.Item;


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

        player = new Player(n, m);
        map[player.getX()][player.getY()] = "O";

        target = new Target(n, m, player.getX(), player.getY());
        map[target.getX()][target.getY()] = "T";

        box = new Box(n, m, player.getX(), player.getY(), target.getX(), target.getY());
        map[box.getX()][box.getY()] = "X";
    }

    //打印地图
    public void display() {
        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }

    //检查路径上是否有障碍
    public boolean check_wall(Item item, char dir) {
        switch (dir) {
            case 'w' :
                if(item.getClass().equals(box.getClass())) return map[item.getX() - 1][item.getY()].equals(" ") || map[box.getX() - 1][box.getY()].equals("T");
                    else return map[item.getX() - 1][item.getY()].equals(" ");
            case 's' :
                if(item.getClass().equals(box.getClass())) return map[item.getX() + 1][item.getY()].equals(" ") || map[box.getX() + 1][box.getY()].equals("T");
                    else return map[item.getX() + 1][item.getY()].equals(" ");
            case 'a' :
                if(item.getClass().equals(box.getClass())) return map[item.getX()][item.getY() - 1].equals(" ") || map[box.getX()][box.getY() - 1].equals("T");
                    else return map[item.getX()][item.getY() - 1].equals(" ");
            case 'd' :
                if(item.getClass().equals(box.getClass())) return map[item.getX()][item.getY() + 1].equals(" ") || map[box.getX()][box.getY() + 1].equals("T");
                    else return map[item.getX()][item.getY() + 1].equals(" ");
            default :  return false;
        }
    }

    //检查路径上是否有箱子
    public boolean check_box(Player player,char dir) {
        return switch (dir) {
            case 'w' -> map[player.getX() - 1][player.getY()].equals("X");
            case 's' -> map[player.getX() + 1][player.getY()].equals("X");
            case 'a' -> map[player.getX()][player.getY() - 1].equals("X");
            case 'd' -> map[player.getX()][player.getY() + 1].equals("X");
            default -> false;
        };
    }

    public void is_win() {
        if (map[target.getX()][target.getY()].equals("X")) {
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