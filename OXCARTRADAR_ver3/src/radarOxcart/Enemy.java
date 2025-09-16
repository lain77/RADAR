package radarOxcart;

public class Enemy {
    double x, y;

    Player player;
    
    public Enemy(double x, double y) { 	
    	this.x = x;
        this.y = y;
    }
    
    public void IA(Player player) {
        double dx = player.getX() - this.x;
        double dy = player.getY() - this.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        double detectionRadius = 100; // distância máxima
        double speed = 0.001;           // velocidade do inimigo

        if (distance <= detectionRadius) {
            // Normalização
            this.x += (dx / distance) * speed;
            this.y += (dy / distance) * speed;
            
        }
    }
} 	