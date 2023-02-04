package starwarscantina.gui;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import starwarscantina.logic.Game;

public class GUI_Card extends JPanel {

    private Game logic = null;
    private String name = null;
    private BufferedImage image = null;
    private BufferedImage bgImage = null;
    private boolean visible = false;
    private boolean pairFound = false;
    private AudioClip sound = null;
    private Graphics2D g2d = null;
    private static int mouseClicks = 0;

    public GUI_Card(String name, BufferedImage image, BufferedImage bgImage) {
        this(name, image, bgImage, null);
    }

    public GUI_Card(String name, BufferedImage image, BufferedImage bgImage, AudioClip sound) {
        this.name = name;
        this.image = image;
        this.bgImage = bgImage;
        this.sound = sound;

        this.setBorder(LineBorder.createBlackLineBorder());
        this.addMouseListener(new SWListener());

        logic = Game.getInstance();
    }

    public void reset() {
        pairFound = false;
        visible = false;
    }

    @Override
    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public AudioClip getSound() {
        return this.sound;
    }

    public boolean isOpen() {
        return visible;
    }

    public void setOpen(boolean visible) {

        if (!isPairFound()) {
            this.visible = visible;
        } else {
            this.visible = true;
        }
    }

    public boolean isPairFound() {
        return pairFound;
    }

    public void setPairFound() {
        pairFound = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g2d = (Graphics2D) g;

            if (visible) {
                g2d.drawImage(image, null, 0, 0);
            } else {
                g2d.drawImage(bgImage, null, 0, 0);
            }
        }
    }

    private boolean showCard() {
        return logic.showCard(this);
    }

    private void doCardOperation() {
        logic.cardOperation(this);
    }

    public void requestRepaint() {
        this.repaint();
    }

    public static int getMouseClicks() {
        return mouseClicks;
    }

    public static void setMouseClicks(int mouseClicks) {
        GUI_Card.mouseClicks = mouseClicks;
    }

    private class SWListener implements MouseListener {

        public SWListener() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (showCard()) {
                mouseClicks++;
                requestRepaint();
                doCardOperation();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
