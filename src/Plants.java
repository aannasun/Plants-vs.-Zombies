import java.awt.Graphics;

abstract class Plants {
	
	private int health;
	private int x, y;
	boolean shoot = true;
	boolean left_ = true;
	boolean zombies = false;
	static boolean onZombies = false;
	int time = 0;
		
	public abstract void draw(Graphics g);

	public abstract void move(); //moves plants
	
	public abstract String name(); //returns name of plant
	
	public abstract Projectile getProjectile(); //returns projectile
		
	//returns health
	public int getHealth() {
		return health;
	}

	//sets value for health
	public void setHealth(int h) {
		health = h;
	}
	
	//shoots 
	public void shoot() {
	}
	
	//returns true if the projectile is at the zombie
	public boolean projectileAtZombie(Zombies z) {
		return false;
	}
	
	//returns true if the plant has a projectile (peashooter would return true, walnut would return false)
	public abstract boolean isProjectile(); 
	
	//decreases health of plant
	public void decreaseHealth(int i) {
		health -= i;
	}
	
	//returns x
	public int getX() {
		return x;
	}
	
	//sets x
	public void setX(int a) {
		x = a;
	}
	//returns y
	public int getY() {
		return y;
	}
	
	//sets y
	public void setY(int b) {
		y = b;
	}
	
	//returns column
	public int getCol() {
		return (int) ((x-145)/75);
	}
	
	//returns row
	public int getRow() {
		return (int)((y-80)/90);
	}

	//sets position
	public void setRestPosition() {
		if (left_ == true)
			left_ = false;
		else if (left_ == false)
			left_ = true;
	}
	
	//sets position
	public void setShootPosition() {
		if (shoot == true)
			shoot = false;
		else if (shoot == false)
			shoot = true;
	}
	
	//returns true if the plant is ready to explode
	public boolean explode() {
		return false;
	}

	//returns true for projectile motion
	public boolean isProjMotion() {
		return false;
	}

	//gives coordinates of zombie
	public void zombCoord(int x2, int y2) {
	}	
}