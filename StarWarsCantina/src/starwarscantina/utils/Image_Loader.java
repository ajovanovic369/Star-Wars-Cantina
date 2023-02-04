package starwarscantina.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image_Loader extends JPanel {

    private GraphicsConfiguration gc;
    private BufferedImage image = null;
    private int x = 0;
    private int y = 0;
    
    
    public Image_Loader() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
    }
    
    public Image_Loader(BufferedImage image) {
        this.image = image;
    }
    
    public BufferedImage loadImage(String imageName) {
        try {
            BufferedImage im = ImageIO.read(getClass().getResource(imageName));
            BufferedImage copy = gc.createCompatibleImage(im.getWidth(), im.getHeight());

            Graphics2D g2d = copy.createGraphics();

            g2d.drawImage(im, 0, 0, null);
            g2d.dispose();
            return copy;
        } catch (Exception e) {
            System.out.println("Load image error for " + imageName + ":\n" + e);
            return null;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image, null, x, y);
        }
    }
}
