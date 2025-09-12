package radarOxcart;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;

public class StartScreen extends JPanel {

    public StartScreen(Runnable onStart) {

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Texto principal
        JLabel title = new JLabel("OXCART RADAR SIMULATOR", SwingConstants.CENTER);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/res/Orbitron-VariableFont_wght.ttf")).deriveFont(36f);
            title.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
            title.setFont(new Font("Arial", Font.BOLD, 36)); // fallback
        }
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.CENTER);

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
