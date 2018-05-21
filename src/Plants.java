import java.awt.Graphics;

abstract class Plants {
	
	int health = 30;
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
}
