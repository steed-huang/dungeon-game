package World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class LayoutLoader {
    // File Reader
    private static BufferedReader br;

    // Room types
    private static final int NUM_LAYOUT = 9;
    private static Layout[] layouts = new Layout[NUM_LAYOUT];

    private static String[] layout_files = {"square.txt", "ver_rec.txt", "hor_rec.txt", "t_shaped.txt", "hole.txt",
                                            "left_up.txt", "right_up.txt", "left_down.txt", "right_down.txt"};

    private static String[] layout_image_files = {"square.png", "ver_rec.png", "hor_rec.png", "t_shaped.png", "hole.png",
                                            "left_up.png", "right_up.png", "left_down.png", "right_down.png"};

    public static Layout getLayout(int layout_type){
        if (layouts[layout_type] == null) loadLayout(layout_type); // if not already loaded, load it
        return layouts[layout_type];
    }

    // loads specific room layouts from file
    public static void loadLayout(int layout_type){
        try {
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

            layouts[layout_type] = new Layout(grid, sprite); // puts new layout in array
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
