package com.fortnitta.game.utils;

/**
 * Created by Alan on 2/1/2015.
 */
public class Is {
    public static boolean Even(int x, int y) {
        boolean Even = false;
        if ((x%2 == 1 && y%2 == 0) || (x%2 == 0 && y%2 == 1)) {
            Even = true;
        }
        return Even;
    }
}
