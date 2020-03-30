package World;

import Main.RandomGenerator;
import World.Rooms.NormalRoom;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static final int SIZE = 15; // width and height
    private static int[][] base_layout = new int[SIZE][SIZE]; // locations of specific rooms
    private static Room[][] layout = new Room[SIZE][SIZE]; // specifies room layout type


    // 0 - nothing, 1 - normal, 2 - spawn
    public void generateMap() {
        generateRooms();
        specifyLayout();
    }

    // output base layout into console
    public void printBaseLayout() {
        System.out.println("Base Layout: ");
        System.out.println("- - - - - - - - - - - - - - -");
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                System.out.print(base_layout[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("- - - - - - - - - - - - - - -\n");
    }

    // output layout into console
    public void printLayout(){
        System.out.println("Layout: ");
        System.out.println("- - - - - - - - - - - - - - -");
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (layout[i][j] == null) System.out.print("  ");
                else System.out.print(layout[i][j].getLayoutType()+ " ");
            }
            System.out.println();
        }
        System.out.println("- - - - - - - - - - - - - - -\n");
    }

    // generates dungeon structure
    private void generateRooms(){
        // set spawn point in the middle
        int x = SIZE / 2;
        int y = SIZE / 2;

        // call two paths of dungeon gen
        traverse(x, y, 20);
        traverse(x, y, 20);
        base_layout[y][x] = 2; // set spawn
    }

    // recursive dungeon generation
    private void traverse(int x, int y, int movesLeft){
        // possible moves
        boolean up = y-1 >= 0;
        boolean down = y+1 < SIZE;
        boolean left = x-1 >= 0;
        boolean right = x+1 < SIZE;

        // add possible to list
        List<String> moves = new ArrayList<>();
        if (up) moves.add("up");
        if (down) moves.add("down");
        if (left) moves.add("left");
        if (right) moves.add("right");

        base_layout[y][x] = 1;
        if (movesLeft <= 0) return; // base case

        int rng = RandomGenerator.getRandom(0, moves.size()-1);
        switch (moves.get(rng)) {
            case "up": traverse(x,y-1,movesLeft-1); return;
            case "down": traverse(x,y+1,movesLeft-1); return;
            case "left": traverse(x-1,y,movesLeft-1); return;
            case "right": traverse(x+1,y,movesLeft-1); return;
            default: System.out.println("something went wrong");
        }
    }

    private void specifyLayout(){
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(base_layout[i][j] != 0) layout[i][j] = new NormalRoom(0); // temporarily set all rooms to be square normal
            }
        }
    }
}