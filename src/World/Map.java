package World;

import Main.RandomGenerator;
import World.Rooms.NormalRoom;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static final int SIZE = 15; // width and height
    private static int[][] base_layout = new int[SIZE][SIZE]; // locations of specific rooms
    private static Room[][] layout = new Room[SIZE][SIZE]; // specifies room layout type
    private static int spawn_col = SIZE / 2;
    private static int spawn_row = SIZE / 2;

    // 0 - nothing, 1 - normal, 2 - spawn
    public void generateMap() {
        generateRooms();
        specifyLayout();
    }

    public int getSpawnRow() { return spawn_row; }
    public int getSpawnCol() { return spawn_col; }

    public Room getRoom(int row, int col) { return layout[row][col]; }

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
        // call two paths of dungeon gen
        traverse(spawn_row, spawn_col, 20);
        traverse(spawn_row, spawn_col, 20);
        base_layout[spawn_row][spawn_col] = 2; // set spawn
    }

    // recursive dungeon generation
    private void traverse(int row, int col, int movesLeft){
        // possible moves
        boolean up = row - 1 >= 0;
        boolean down = row + 1 < SIZE;
        boolean left = col - 1 >= 0;
        boolean right = col + 1 < SIZE;

        // add possible to list
        List<String> moves = new ArrayList<>();
        if (up) moves.add("up");
        if (down) moves.add("down");
        if (left) moves.add("left");
        if (right) moves.add("right");

        base_layout[row][col] = 1;
        if (movesLeft <= 0) return; // base case

        int rng = RandomGenerator.getRandom(0, moves.size()-1);
        switch (moves.get(rng)) {
            case "up": traverse(row-1, col,movesLeft-1); return;
            case "down": traverse(row+1, col,movesLeft-1); return;
            case "left": traverse(row, col-1,movesLeft-1); return;
            case "right": traverse(row, col+1,movesLeft-1); return;
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