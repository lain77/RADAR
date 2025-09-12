package radarOxcart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaRadar extends JPanel {

	JButton pauseButton = new JButton("Pause");
	
    private double ponteiroAngle = 0; // ângulo do ponteiro em radianos
    private Player player;
    private Thread gameThread;

    public TelaRadar(Player player) {
        this.player = player;
        setFocusable(true);
        requestFocusInWindow();
    }

    public void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(() -> {
                while (true) {
                    ponteiroAngle += Math.toRadians(5);
                    if (ponteiroAngle >= 2 * Math.PI) ponteiroAngle = 0;

                    player.updatePosition(); // atualiza a posição do player
                    repaint();               // redesenha a tela

                    try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
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

        // centro
        g.setColor(Color.RED);
        g.fillOval(448, 298, 4, 4);

        // ponteiro
        g.setColor(Color.RED);
        int ponteiroX = 450 + (int) (100 * Math.cos(ponteiroAngle));
        int ponteiroY = 300 + (int) (100 * Math.sin(ponteiroAngle));
        g.drawLine(450, 300, ponteiroX, ponteiroY);

        // exibir dados do player
        g.setColor(Color.WHITE);
        g.drawString("X: " + (int) player.x, 10, 20);
        g.drawString("Y: " + (int) player.y, 10, 40);
        g.drawString("Velocidade: " + String.format("%.2f", player.velocity * 10000) + " km/h", 10, 60);
        g.drawString("Radar: " + (player.radarOn ? "Ligado" : "Desligado"), 10, 80);
        
        g.drawRect(700, 20, 40, 40);
    }
}
