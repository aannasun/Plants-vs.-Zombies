import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bokchoi extends Plants{
	
	private int zx;
	private boolean atZombie = false;
	private BufferedImage img, fight2;
	{ 
		try {
			img = ImageIO.read((getClass().getResource("bokchoi1.png")));
			fight2 = ImageIO.read((getClass().getResource("bokfight2.png")));
//			img = ImageIO.read(new File("bokchoi1.png"));
//			fight2 = ImageIO.read(new File("bokfight2.png"));
		}
		catch(IOException e) {
			
		}
	}
	
	public Bokchoi(int x, int y) {
		setX(x);
		setY(y);
		setHealth(100);
	}
	
	public void draw(Graphics g) {
		if(getHealth() > 0) {
			if(!atZombie) {
					g.drawImage(img, getX(), getY(), null);
				}
			else {
				g.drawImage(fight2, getX(), getY(), null);
			}
		}
	}
	
	//moves the bokchoi
	public void move() {
		if(atZombie) {
			atZombie = false;
		}
		else {
			atZombie = true;
		}
	}
	
	//returns false because there is no projectile
	public boolean isProjectile() {
		return false;
	}
	
	//returns true because there is "motion"
	public boolean isProjMotion() {
		return true;
	}
	
	//punches
	public void shoot() {
		if(zx >= getX()-73 && zx <= getX()+73) {
			atZombie = true;
		}
	}
	
	//sets zombie coordinate
	public void zombCoord(int x, int y) {
		zx = x;
	}

	//returns null because no projectile
	public Projectile getProjectile() {
		return null;
	}
	
	//returns which column it is in
	public int getCol() {
		return (int) ((getX()-145)/75);
	}
	
	//returns which row it is in
	public int getRow() {
		return (int)((getY()-80)/90);
	}
	
	//name
	public String name() {
		return "bokchoi";
	}
	
	//returns is the projectile is at the zombie
	public boolean projectileAtZombie(Zombies z) {
		return atZombie;
	}
}