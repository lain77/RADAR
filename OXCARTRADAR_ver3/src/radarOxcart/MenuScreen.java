package radarOxcart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuScreen extends JPanel {
    public MenuScreen(Runnable onPlay) {
    
        setLayout(new GridLayout(3, 1, 20, 10));

//        Background back = new Background("/img/sr71.jpg");
//        back.setBackground(Color.BLACK);
        setBackground(Color.BLACK);
        
        JButton play = new JButton("Play");
        JButton settings = new JButton("Settings");
        JButton exit = new JButton("Exit");

        // Transformar em texto clicável
        JButton[] buttons = {play, settings, exit};

        for (JButton button : buttons) {
            button.setHorizontalAlignment(SwingConstants.LEFT); 
            button.setBorderPainted(false);     
            button.setContentAreaFilled(false); 
            button.setFocusPainted(false);     
            button.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
            button.setForeground(Color.YELLOW);  
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/res/Orbitron-VariableFont_wght.ttf")).deriveFont(36f);
                button.setFont(font);
            } catch (Exception e) {
                e.printStackTrace();
                button.setFont(new Font("Arial", Font.BOLD, 36)); // fallback
            }
        }

        play.addActionListener(e -> onPlay.run());
        exit.addActionListener(e -> System.exit(0));

        add(play);
        add(settings);
        add(exit);
    }
}

