package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Background extends JPanel {
    private BufferedImage image;

    public Background(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
