import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Peashooter extends Plants{

	private boolean toLeft = true;
	private Pea p;
	private BufferedImage img, left, right, img1, img2;
	{
		try 
		{
//			left = ImageIO.read(new File("peashooter1.png"));
//			right = ImageIO.read(new File("peashooter2.png"));
//			img1 = ImageIO.read(new File("peashot1.png"));
//			img2 = ImageIO.read(new File("peashot2.png"));
			left = ImageIO.read((getClass().getResource("peashooter1.png")));
			right = ImageIO.read((getClass().getResource("peashooter2.png")));
			img1 = ImageIO.read((getClass().getResource("peashot1.png")));
			img2 = ImageIO.read((getClass().getResource("peashot2.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Peashooter(int x, int y) {
		setX(x);
		setY(y);
		setHealth(30);
		p = new Pea(getX()+80, getY()+20, 0);
	}

	public void draw(Graphics g) {
		if (getHealth()>0) {
			if (zombies==true) {
				if (shoot==true)
					g.drawImage(img1, getX(), getY(), null);
				if (shoot==false)
					g.drawImage(img2, getX(), getY(), null);
			}
			else {
				if (left_==true)
					g.drawImage(left, getX(), getY(), null);
				if (left_==false)
					g.drawImage(right, getX(), getY(), null);
			}
			if(p.newPea()) {
				newPea();
			}
			p.draw(g);
		}
	}

	//moves the peashooter
	public void move() {
		if(toLeft) {
			img = left;
			toLeft = false;
		}
		else {
			img = right;
			toLeft = true;
		}
	}

	//returns name
	public String name() {
		return "peashooter";
	}

	//creates new pea
	public void newPea() {
		p = new Pea(getX()+80, getY()+20, 0);
	}

	//returns if the projectile is at the zombie
	public boolean projectileAtZombie(Zombies z) {
		return p.atZombie(z);
	}

	//shoots the pea
	public void shoot() {	
		p.changeX();	
	}

	//returns the pea
	public Projectile getProjectile() {
		return p;
	}
	
	//returns true because there is a projectile
	public boolean isProjectile() {
		return true;
	}
}
