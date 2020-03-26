package World;

import java.io.*;

public abstract class Room {
    // Room types
    public static final int NUM_ROOMS = 9;

    public static int SQUARE = 0;
    public static int VER_REC = 1;
    public static int HOR_REC = 2;
    public static int T_SHAPED = 3;
    public static int HOLE = 4;
    public static int LEFT_UP = 5;
    public static int RIGHT_UP = 6;
    public static int LEFT_DOWN = 7;
    public static int RIGHT_DOWN = 8;

    private static boolean[] isLoaded = new boolean[NUM_ROOMS];
    protected int[][] grid; // instance room layout

    Room(){}

    // loads specific room layouts from text file
    public static void loadRoom(int room_type){
        if (isLoaded[room_type] == false);
    };

    public abstract void generateEnemies();
    public abstract void draw();
}