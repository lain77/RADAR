package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaRadar extends JPanel {

	private volatile boolean paused = false;
	
    private int xb = 1850;
    private int yb = 20;
    private int wb = 40;
    private int hb = 40;

    private JButton pauseButton = new JButton("Pause");

    private double ponteiroAngle = 0;
    private Player player;
    private Thread gameThread;

    private PauseMenu pauseMenu;

    public TelaRadar(Player player, CardLayout cardLayout, JPanel container) {
        this.player = player;
        setFocusable(true);
        requestFocusInWindow();
        setLayout(null); // necessário para sobreposição do pauseMenu

        // ⬇️ Cria o menu de pausa depois de declarar o campo
        pauseMenu = new PauseMenu(
        	    () -> {
        	        pauseMenu.setVisible(false);
        	        resume(); // ⬅️ Retoma a thread quando clica "Continuar"
        	    },
        	    () -> {
        	        pauseMenu.setVisible(false);
        	        pause(); // ⬅️ Garante que a thread fique pausada ao voltar pro menu
        	        cardLayout.show(container, "Menu");
        	    }
        	);
    }

    public void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(() -> {
                while (true) {
                    if (!paused) {
                        ponteiroAngle += Math.toRadians(5);
                        if (ponteiroAngle >= 2 * Math.PI) ponteiroAngle = 0;

                        player.updatePosition();
                        repaint();
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            gameThread.start();
        }
    }

    public void setupKeyBindings() {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "acelerar");
        getActionMap().put("acelerar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.accelerate(0.001);
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "desacelerar");
        getActionMap().put("desacelerar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.decelerate(0.001);
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "toggleRadar");
        getActionMap().put("toggleRadar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setRadarOn(!player.isRadarOn());
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "pauseToggle");
        getActionMap().put("pauseToggle", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean visible = !pauseMenu.isVisible();
                pauseMenu.setVisible(visible);
                if (visible) {
                    pause();   // Pausa o jogo quando menu abre
                } else {
                    resume();  // Retoma quando menu fecha com ESC
                }
            }
        });
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }
    
    public boolean isPaused() {
    	return paused;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GREEN);
        g.drawOval(350, 200, 200, 200);
        g.fillOval(350, 200, 200, 200);

        g.setColor(Color.WHITE);
        for (int i = 0; i < 360; i += 30) {
            double angle = Math.toRadians(i);
            int x1 = 450 + (int) (90 * Math.cos(angle));
            int y1 = 300 + (int) (90 * Math.sin(angle));
            int x2 = 450 + (int) (100 * Math.cos(angle));
            int y2 = 300 + (int) (100 * Math.sin(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        g.setColor(Color.RED);
        g.fillOval(448, 298, 4, 4);

        g.setColor(Color.RED);
        int ponteiroX = 450 + (int) (100 * Math.cos(ponteiroAngle));
        int ponteiroY = 300 + (int) (100 * Math.sin(ponteiroAngle));
        g.drawLine(450, 300, ponteiroX, ponteiroY);

        g.setColor(Color.WHITE);
        g.drawString("X: " + (int) player.x, 10, 20);
        g.drawString("Y: " + (int) player.y, 10, 40);
        g.drawString("Velocidade: " + String.format("%.2f", player.velocity * 10000) + " km/h", 10, 60);
        g.drawString("Radar: " + (player.radarOn ? "Ligado" : "Desligado"), 10, 80);

        g.drawRect(xb, yb, wb, hb);
        g.fillRect(xb, yb, wb, hb);
    }
}
