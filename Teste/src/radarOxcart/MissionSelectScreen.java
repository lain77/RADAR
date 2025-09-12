package radarOxcart;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MissionSelectScreen extends JPanel {

    public MissionSelectScreen(Runnable onSelectMission) {
    	
    	setBackground(Color.BLACK);
    	
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton mission1 = new JButton("Missão 1");
        
        JButton[] buttons = {mission1};
        
        for (JButton button : buttons) {
            button.setHorizontalAlignment(SwingConstants.CENTER); 
           // button.setBorderPainted(false);     
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
        
        mission1.addActionListener(e -> onSelectMission.run());

        add(mission1);
    }
}
