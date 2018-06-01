import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SnowPea extends Plants{
	
	private Pea p;
	private BufferedImage img1;
	{
		try 
		{
//			img1 = ImageIO.read(new File("snowpea.png"));
			img1 = ImageIO.read((getClass().getResource("snowpea.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public SnowPea(int x, int y) {
		setX(x);
		setY(y);
		setHealth(30);
		p = new Pea(getX()+80, getY()+20, 1);
	}

	public void draw(Graphics g) {
		if (getHealth()>0) {
			g.drawImage(img1, getX(), getY(), null);
			if(p.newPea()) {
				newPea();
			}
			p.draw(g);
		}
	}

	//returns name
	public String name() {
		return "snowpea";
	}

	//creates a new pea
	public void newPea() {
		p = new Pea(getX()+80, getY()+20, 1);
	}

	//returns is the projectile is at the zombie, slows the zombie if the snowpea projectile is at it
	public boolean projectileAtZombie(Zombies z) {
		//
		if(p.atZombie(z)) {
			p.slow(z);
		}
		return p.atZombie(z);
	}

	//shoots the projectile
	public void shoot() {	
		p.changeX();	
	}

	//returns the projectile
	public Projectile getProjectile() {
		return p;
	}

	//returns true because there is a projectile
	public boolean isProjectile() {
		return true;
	}

	//move
	public void move() {
		
	}
}