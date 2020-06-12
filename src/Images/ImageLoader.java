package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageLoader {

    // image files names and images
    private static HashMap<String, BufferedImage> images = new HashMap<>();

    public static BufferedImage getImage(String file) {
        if (!images.containsKey(file)){ // if not already loaded, load image
            try {
                images.put(file, ImageIO.read(ImageLoader.class.getResourceAsStream("/Assets/"+file)));
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return images.get(file); // return image
    }
}
