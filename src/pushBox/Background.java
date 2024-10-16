package pushBox;

public class Background {
    private String[][] map;
    private Man man;
    private Box box;
    private int man_x;
    private int man_y;
    private int box_x;
    private int box_y;

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
}