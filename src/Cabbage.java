import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cabbage extends Plants {

	private CabbageP cabbageP;
//	private int health = 30;
	private int zx, zy;
	private BufferedImage img;
	{
		try 
		{
//			img = ImageIO.read(new File("cabbage.png"));
			img = ImageIO.read((getClass().getResource("cabbage.png")));
		}

		catch(IOException e) {

		}
	}

	public Cabbage(int x, int y) {
		setX(x);
		setY(y);
		setHealth(30);
		cabbageP = new CabbageP(getX()+20, getY(), zx, zy);
	}

	public void draw(Graphics g) {
		if (getHealth()>0) {
			g.drawImage(img, getX(), getY(), null);
			
				if(cabbageP.newCabbage()) {
					newCabbage();
				}
				cabbageP.draw(g);
			}
	}

	//creates a new projectile
	private void newCabbage() {
		cabbageP = new CabbageP(getX()+20, getY(), zx, zy);
	}

	//returns name
	public String name() {
		return "cabbage";
	}

	//shoots the projectile
	public void shoot() {
		cabbageP.shoot();
	}

	//returns the projectile
	public Projectile getProjectile() {
		return cabbageP;
	}


	//returns true because there is a projectile
	public boolean isProjectile() {
		return true;
	}

	//returns true because there is projectile motion
	public boolean isProjMotion() {
		return true;	
	}

	//sets the zombie coordinates
	public void zombCoord(int x, int y) {
		zx = x;
		zy = y;
	}

	//returns true if the projectile is at the zombie
	public boolean projectileAtZombie(Zombies z) {
		return cabbageP.atZombie(z);
	}

	//moves
	public void move() {
		
	}
}