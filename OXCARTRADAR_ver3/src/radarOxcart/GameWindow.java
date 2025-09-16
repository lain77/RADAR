package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {

	private volatile boolean paused = false;
    private CardLayout cardLayout = new CardLayout();
    private JPanel container = new JPanel(cardLayout);
    private Player player = new Player(400, 300, 0.1, 0, false, 0);

    public GameWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        setSize(2500, 1540);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1️⃣ Start Screen
        StartScreen startScreen = new StartScreen(() -> cardLayout.show(container, "Menu"));

        // 2️⃣ Menu Screen
        MenuScreen menuScreen = new MenuScreen(() -> cardLayout.show(container, "MissionSelect"));

        // 3️⃣ Mission Select Screen
        MissionSelectScreen missionScreen = new MissionSelectScreen(
        	    () -> {
        	    	TelaRadar radar = new TelaRadar(player, cardLayout, container);        	        
        	    	radar.setupKeyBindings();

        	        TelaPlano plano = new TelaPlano(player);

        	        container.add(radar, "GameplayRadar");
        	        container.add(plano, "GameplayPlano");

        	        cardLayout.show(container, "GameplayRadar");

        	        radar.startGame();
        	        plano.startGame();

        	        radar.setFocusable(true);
        	        radar.requestFocusInWindow();
        	        radar.addKeyListener(new KeyAdapter() {
        	            @Override
        	            public void keyPressed(KeyEvent e) {
        	                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        	                    cardLayout.show(container, "GameplayPlano");
        	                    plano.requestFocusInWindow();
        	                }
        	            }
        	        });

        	        plano.setFocusable(true);
        	        plano.addKeyListener(new KeyAdapter() {
        	            @Override
        	            public void keyPressed(KeyEvent e) {
        	                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        	                    cardLayout.show(container, "GameplayRadar");
        	                    radar.requestFocusInWindow();
        	                }
        	                if (e.getKeyCode() == KeyEvent.VK_W) {
        	                    player.accelerate(0.001);
        	                } else if (e.getKeyCode() == KeyEvent.VK_S) {
        	                    player.decelerate(0.001);
        	                }
        	                if (e.getKeyCode() == KeyEvent.VK_R) {
        	                    player.setRadarOn(!player.isRadarOn());
        	                }
        	                if (e.getKeyCode() == KeyEvent.VK_A) player.yawLeft(0.01);
        	                if (e.getKeyCode() == KeyEvent.VK_D) player.yawRight(0.01);
        	                
        	            }

        	            @Override public void keyReleased(KeyEvent e) { }
        	            @Override public void keyTyped(KeyEvent e) { }
        	        });
        	    },

        	    // ✅ Segundo lambda para o botão "Voltar"
        	    () -> cardLayout.show(container, "Menu")
        	);
        
        // Adiciona telas ao container
        container.add(startScreen, "Start");
        container.add(menuScreen, "Menu");
        container.add(missionScreen, "MissionSelect");

        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Label.background", Color.BLACK);
        
        add(container);
        setVisible(true);

        
        
        // Começa na Start Screen
        cardLayout.show(container, "Start");
    }

    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new GameWindow();
    }
}
