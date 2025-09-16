package radarOxcart;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageWindow extends JFrame {

    public ImageWindow() {
        setTitle("Image Background");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/img/sr.jpg")); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null) {
            Image scaledImg = img.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
            imageLabel.setLayout(new BorderLayout());

            JLabel title = new JLabel("OXCART RADAR SIMULATOR", SwingConstants.CENTER);
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/res/DARKLINOS.ttf")).deriveFont(36f);
                title.setFont(font);
            } catch (Exception e) {
                e.printStackTrace();
                title.setFont(new Font("Arial", Font.BOLD, 36)); // fallback
            }

            title.setForeground(Color.WHITE);
            title.setOpaque(false);

            imageLabel.add(title, BorderLayout.NORTH);

            add(imageLabel, BorderLayout.CENTER);
            pack();
            setResizable(false);
            setLocationRelativeTo(null); 
            setVisible(true); 
        } else {
            System.err.println("Image failed to load. Check path: src/img/sr.jpg");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageWindow());
    }
}
