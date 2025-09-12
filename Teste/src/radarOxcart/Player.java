package radarOxcart;

public class Player {
    public double x, y;
    public double velocity; // pixels por frame
    public double direction; // ângulo em radianos
    public boolean radarOn;
    public double yaw;  

    public Player(double x, double y, double velocity, double direction, boolean radarOn, double yaw) {
        this.x = x;
        this.y = y;
        this.velocity = velocity * 0.1;
        this.direction = direction;
        this.radarOn = radarOn;
        this.yaw = yaw;
    }

    public void yawLeft(double angle) {
        direction -= angle; 
    }

    public void yawRight(double angle) {
        direction += angle;
    }
    
    public void updatePosition() {
    	x += velocity * Math.cos(direction);
    	y += velocity * Math.sin(direction);
    }

    public void turnLeft(double angle) {
        direction -= angle;
    }

    public void turnRight(double angle) {
        direction += angle;
    }

    public void accelerate(double amount) {
        setVelocity(velocity += amount);
    }

    public void decelerate(double amount) {
        setVelocity(Math.max(0, velocity - amount));
    }

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}
    
    public boolean isRadarOn() {
    	return radarOn;
    }
    
    public void setRadarOn(boolean radarOn) {
    	this.radarOn = radarOn;
    }
}
