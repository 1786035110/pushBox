package com.DDT.pushBox.Box;

import com.DDT.pushBox.bean.Box;

public class SmallBox extends Box {
    public SmallBox(int n, int m, int player_x, int player_y, int target_x, int target_y) {
        super(n, m, player_x, player_y, target_x, target_y);
        this.setType("S");
    }
}
