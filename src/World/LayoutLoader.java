package World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class LayoutLoader {
    // File Reader
    private static BufferedReader br;

    // Room types
    private static final int NUM_LAYOUT = 13;
    private static Layout[] layouts = new Layout[NUM_LAYOUT];

    // square: 0, hole: 1, cross: 2, rec_ver: 3, rec_hor: 4,
    // left_up: 5, right_up: 6, left_down: 7, right_down: 8,
    // t_up: 9, t_down: 10, t_left: 11, t_right: 12
    private static String[] layout_files = {"square.txt", "hole.txt", "cross.txt", "rec_ver.txt", "rec_hor.txt",
                                            "left_up.txt", "right_up.txt", "left_down.txt", "right_down.txt",
                                            "t_up.txt", "t_down.txt", "t_left.txt", "t_right.txt"};

    private static String[] layout_image_files = {"square.png", "hole.png", "cross.png", "rec_ver.png", "rec_hor.png",
                                                    "left_up.png", "right_up.png", "left_down.png", "right_down.png",
                                                    "t_up.png", "t_down.png", "t_left.png", "t_right.png"};
    private static BufferedImage door_hor;
    private static BufferedImage door_ver;

    public static Layout getLayout(int layout_type){
        if (layouts[layout_type] == null) loadLayout(layout_type); // if not already loaded, load it
        return layouts[layout_type];
    }

    // loads specific room layouts from file
    public static void loadLayout(int layout_type){
        try {
            if (door_hor == null) door_hor = ImageIO.read(LayoutLoader.class.getResourceAsStream("/Assets/door_hor.png"));
            if (door_ver == null) door_ver =  ImageIO.read(LayoutLoader.class.getResourceAsStream("/Assets/door_ver.png"));

            br = new BufferedReader(new FileReader("src/World/Layouts/"+layout_files[layout_type]));
            int height = Integer.parseInt(br.readLine());
            int width = Integer.parseInt(br.readLine());
            int[][] grid = new int[height][width];

            for (int i = 0; i < height; i++){
                // reading each row and parsing to ints
                String line = br.readLine();
                String[] str = line.split(" ");
                int[] ints = new int[width];
                for (int j = 0; j < width; j++){
                    ints[j] = Integer.parseInt(str[j]);
                }

                grid[i] = ints; // storing to grid
            }
            br.close();

            // get image
            BufferedImage sprite = ImageIO.read(LayoutLoader.class.getResourceAsStream("/Assets/"+layout_image_files[layout_type]));

            layouts[layout_type] = new Layout(grid, sprite, door_hor, door_ver); // puts new layout in array
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
