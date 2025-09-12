package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;
    private float opacity = 0.3f; // Opacidade de 0.0 (invisível) a 1.0 (totalmente visível)

    public BackgroundPanel(String imagePath, float opacity) {
        this.opacity = opacity;

        try {
            backgroundImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridLayout(3, 1, 20, 10)); // Seu layout
        setOpaque(false); // Importante para transparência funcionar direito
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
           // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
