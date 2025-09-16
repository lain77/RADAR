package radarOxcart;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PauseMenu extends JPanel {

    public PauseMenu(Runnable onResume, Runnable onExitToMenu) {
        setLayout(new GridLayout(3, 1, 10, 10));
        setOpaque(false); 

        JButton resume = new JButton("Continuar");
        JButton exit = new JButton("Voltar ao Menu");

        resume.addActionListener(e -> onResume.run());
        exit.addActionListener(e -> onExitToMenu.run());

        add(resume);
        add(exit);

        setVisible(false); 
    }

    
    
}

