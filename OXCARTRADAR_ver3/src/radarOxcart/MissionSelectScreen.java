package radarOxcart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MissionSelectScreen extends JPanel {

	public MissionSelectScreen(Runnable onSelectMission, Runnable onBack) {
    	
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // 🔹 Centro com os botões principais
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBackground(Color.BLACK);

        JButton mission1 = new JButton("Missão 1");

        JButton[] buttons = {mission1};

        for (JButton button : buttons) {
            button.setHorizontalAlignment(SwingConstants.CENTER);
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
                button.setFont(new Font("Arial", Font.BOLD, 36));
            }
            centerPanel.add(button);
        }

        mission1.addActionListener(e -> onSelectMission.run());

        //Painel inferior com botão Voltar alinhado à direita
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.BLACK);

        JButton voltar = new JButton("Voltar");
        voltar.setContentAreaFilled(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        voltar.setForeground(Color.YELLOW);
        
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/res/Orbitron-VariableFont_wght.ttf")).deriveFont(24f);
            voltar.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
            voltar.setFont(new Font("Arial", Font.BOLD, 24));
        }

        voltar.addActionListener(e -> onBack.run());

        bottomPanel.add(voltar);

        //Adiciona os dois painéis no layout principal
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

