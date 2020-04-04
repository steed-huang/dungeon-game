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
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (layout[i][j] == null) System.out.print("   ");
                else System.out.printf("%02d ", layout[i][j].getLayoutType());
            }
            System.out.println();
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -\n");
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
        layout[spawn_row][spawn_col] = new NormalRoom(0);
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(base_layout[i][j] != 0 && base_layout[i][j] != 2) {
                    boolean up = false, down = false, left = false, right = false; // checking connected rooms
                    if (i-1 >= 0 && base_layout[i-1][j] != 0) up = true;
                    if (i+1 < SIZE && base_layout[i+1][j] != 0) down = true;
                    if (j-1 >= 0 && base_layout[i][j-1] != 0) left = true;
                    if (j+1 < SIZE && base_layout[i][j+1] != 0) right = true;
                    randomLayout(i, j, up, down, left, right);
                }
            }
        }
    }

    private void randomLayout(int row, int col, boolean up, boolean down, boolean left, boolean right){
        int rng;

        if (up && down && left && right){ // all 4: cube 50%, hole 25%, cross 25%
            rng = RandomGenerator.getRandom(0, 3);
            if (rng <= 1) layout[row][col] = new NormalRoom(0); // cube
            else if (rng == 2) layout[row][col] = new NormalRoom(1); // hole
            else if (rng == 3) layout[row][col] = new NormalRoom(2); // cross
        }

        // t
        else if (up && !down && left && right) layout[row][col] = new NormalRoom(9); // t_up
        else if (!up && down && left && right) layout[row][col] = new NormalRoom(10); // t_down
        else if (up && down && left) layout[row][col] = new NormalRoom(11); // t_left
        else if (up && down && right) layout[row][col] = new NormalRoom(12); // t_right

        // bend
        else if (up && !down && left) layout[row][col] = new NormalRoom(5); // left_up
        else if (!up && down && left) layout[row][col] = new NormalRoom(7);// left_down
        else if (up && !down && right) layout[row][col] = new NormalRoom(6); // right_up
        else if (!up && down && right)layout[row][col] = new NormalRoom(8);// right_down


        // rec
        else if (up && down) layout[row][col] = new NormalRoom(3);// rec_ver
        else if (!up && !down && left && right) layout[row][col] = new NormalRoom(4); // rec_hor

        // only one: cube 50%, hole 50%
        else if (up || down || left || right){
            rng = RandomGenerator.getRandom(0, 1);
            if (rng == 0) layout[row][col] = new NormalRoom(0); // cube
            else if (rng == 1) layout[row][col] = new NormalRoom(1); // hole
        }
    }
}