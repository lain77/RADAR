package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaPlano extends JPanel {

	private volatile boolean paused = false;
	
    private Player player;
    private List<Enemy> inimigos = new ArrayList<>();
    private Thread gameThread;

    public TelaPlano(Player player) {
        this.player = player;
        setFocusable(true);

        // Exemplo de inimigos
        inimigos.add(new Enemy(430, 310));
        inimigos.add(new Enemy(500, 400));

        setupKeyBindings();
    }

    public void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(() -> {
                while (true) {
                    if (!paused) {
                        player.updatePosition();
                        repaint();
                    }
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            gameThread.start();
        }
    }

    private void setupKeyBindings() {
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

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "yawLeft");
        getActionMap().put("yawLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.yawLeft(0.01);
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "yawRight");
        getActionMap().put("yawRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.yawRight(0.01);
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "toggleRadar");
        getActionMap().put("toggleRadar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setRadarOn(!player.isRadarOn());
            }
        });
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // player
        g.setColor(Color.GREEN);
        g.fillOval((int) player.x - 10, (int) player.y - 10, 10, 10);

        // grid fininha
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(100, 100, 100, 100));
        int spacing = 50;
        for (int x = 0; x < getWidth(); x += spacing) g2d.drawLine(x, 0, x, getHeight());
        for (int y = 0; y < getHeight(); y += spacing) g2d.drawLine(0, y, getWidth(), y);

        // eixos principais
        g2d.setColor(Color.GRAY);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // inimigos detectados
        for (Enemy enemy : inimigos) {
            double dx = enemy.x - player.x;
            double dy = enemy.y - player.y;
            double distancia = Math.sqrt(dx * dx + dy * dy);

            if (distancia <= 30) { // radar detecta 30 px
                g.setColor(Color.RED);
                g.fillOval((int) enemy.x, (int) enemy.y, 6, 6);
            }
        }
        
        for(Enemy enemy : inimigos) {
        	enemy.IA(player);
        	g.drawString("Detectado", 600, 50);
        	repaint();
        }
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
}
