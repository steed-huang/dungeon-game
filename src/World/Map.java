package World;

import Main.RandomGenerator;
import World.Rooms.NormalRoom;

public class Map {
    private static final int SIZE = 8; // width and height
    private static int[][] base_layout = new int[SIZE][SIZE]; // locations of specific rooms
    private static Room[][] layout = new Room[SIZE][SIZE]; // specifies room layout type

    // places a boss room in top two rows and spawn in bottom two
    // generates 2 randomly placed shop room and n normal rooms
    // connects the boss, spawn, and 3 randomly generated rooms
    // determines room layout depending on how many other rooms a room leads to
    // 0 - nothing, 1 - normal, 2 - spawn, 3 - shop, 4 - boss

    // generates map
    public void generateMap() {
        placeSpawn_Boss();
        placeRandomRooms(3);
        connectRooms();
        specifyLayouts();
    }

    // output base layout into console
    public void printBaseLayout() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                System.out.print(base_layout[i][j] + " ");
            }
            System.out.println();
        }
    }

    // output layout into console
    public void printLayout(){
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void placeSpawn_Boss(){
        base_layout[RandomGenerator.getRandom(SIZE-2, SIZE-1)][RandomGenerator.getRandom(0, SIZE-1)] = 2; // spawn
        base_layout[RandomGenerator.getRandom(0, 1)][RandomGenerator.getRandom(0, SIZE-1)] = 4; // boss
    }

    private void placeRandomRooms(int n){
        int x,y;
        int num = n;
        while (num >= -1){
            x = RandomGenerator.getRandom(0, SIZE-1);
            y = RandomGenerator.getRandom(0, SIZE-1);
            if (base_layout[x][y] == 0){
                if(num <= 0) base_layout[x][y] = 3;
                else base_layout[x][y] = 1;
                num--;
            }
        }
    }

    private void connectRooms(){}

    private void specifyLayouts(){}
}
