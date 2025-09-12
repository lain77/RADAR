package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tela extends JPanel {

    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    private Player player;

    public Tela(Player player) {
    	this.player = player;
        TelaRadar radar = new TelaRadar(player);
        TelaPlano plano = new TelaPlano(player);

        container.add(radar, "Radar");
        radar.requestFocusInWindow();
        container.add(plano, "Plano");

        setLayout(new BorderLayout());
        add(container, BorderLayout.CENTER);

        // Foco para capturar teclas
        setFocusable(true);
        requestFocusInWindow();

        // KeyListener
        addKeyListener(new KeyListener() {
            boolean mostrandoRadar = true;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) { // aperta ESPAÇO
                    if (mostrandoRadar) {
                        cardLayout.show(container, "Plano");
                    } else {
                        cardLayout.show(container, "Radar");
                    }
                    mostrandoRadar = !mostrandoRadar;
                }
        		if(e.getKeyCode() == KeyEvent.VK_W) {
        			player.accelerate(0.001);
        		} else if(e.getKeyCode() == KeyEvent.VK_S){
        			player.decelerate(0.001);
        		}
        		if (e.getKeyCode() == KeyEvent.VK_R) { 
        		    player.setRadarOn(!player.isRadarOn());
        		}
        		if (e.getKeyCode() == KeyEvent.VK_A) player.yawLeft(0.01);
        		if (e.getKeyCode() == KeyEvent.VK_D) player.yawRight(0.01);
            }
            
            @Override
            public void keyReleased(KeyEvent e) { }

            @Override
            public void keyTyped(KeyEvent e) { }
        });

        // Cria a janela
        JFrame frame = new JFrame("Radar + Plano com Tecla");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Player player = new Player(400, 300, 1, 0, false, 0);
        new Tela(player);
    }
}
