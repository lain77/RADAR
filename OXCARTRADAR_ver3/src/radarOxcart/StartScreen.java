package radarOxcart;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class StartScreen extends JPanel {
	
    public StartScreen(Runnable onStart) {
    	
    	JFrame frame = new JFrame();
    	
        frame.setTitle("Image Background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("src/img/sr7.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null) {
            Image scaledImg = img.getScaledInstance(2500, 1540, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImg));
            label.setOpaque(true); // Make sure it's not transparent
            label.setBackground(Color.BLACK); // Optional
            setLayout(new BorderLayout());
            add(label, BorderLayout.CENTER);
            frame.pack();
        }

        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center on screen
        setVisible(true);
        
        // Texto principal
        JLabel title = new JLabel("OXCART RADAR SIMULATOR", SwingConstants.CENTER);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/res/DARKLINOS.ttf")).deriveFont(36f);
            title.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
            title.setFont(new Font("Arial", Font.BOLD, 36)); // fallback
        }
        title.setForeground(Color.BLACK);
        title.setBackground(Color.BLACK);
        add(title, BorderLayout.NORTH);

        // Instrução
        JLabel label = new JLabel("Pressione ENTER para começar", SwingConstants.CENTER);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/res/Orbitron-VariableFont_wght.ttf")).deriveFont(36f);
            label.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
            label.setFont(new Font("Arial", Font.BOLD, 36)); // fallback
        }
        label.setForeground(Color.GREEN);
        add(label, BorderLayout.SOUTH);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onStart.run(); // vai para a próxima tela
                }
            }
        });
    }
}
