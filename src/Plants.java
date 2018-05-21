import java.awt.Graphics;

abstract class Plants {
	
	int health;
	int x, y;
	boolean shoot = true;
	boolean left = true;
	
	public void draw(Graphics g) {
				
	}
	
	public String name() {
		return "";
	}
	
	public void move() {
		
	}
	
	public int getHealth() {
		return health;
	}

	public void shoot() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean projectileAtZombie(Zombies z) {
		return false;
	}
	
	public void decreaseHealth(int i) {
		health -= i;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getCol() {
		return (int) (x/75);
	}
	
	public int getRow() {
		return (int)(y/90);
	}

	public void setRestPosition() {
		if (left == true)
			left = false;
		else if (left == false)
			left = true;
	}
	
	public void setShootPosition() {
		if (shoot == true)
			shoot = false;
		else if (shoot == false)
			shoot = true;
	}
	
	public abstract Projectile getProjectile();
}
