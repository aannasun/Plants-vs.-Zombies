import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Walnut extends Plants{
	
	private BufferedImage img, half;
	{
		try 
		{
//			img = ImageIO.read(new File("walnut.png"));
//			half = ImageIO.read(new File("half_walnut.png"));
			img = ImageIO.read((getClass().getResource("walnut.png")));
			half = ImageIO.read((getClass().getResource("half_walnut.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Walnut(int x, int y) {
		setX(x);
		setY(y);
		setHealth(100);
	}

	public void draw(Graphics g) {
		if (getHealth() < 50)
			g.drawImage(half, getX(), getY(), null);
		else if (getHealth() > 0) {
			g.drawImage(img, getX(), getY(), null);
}
	}
	
	//returns name
	public String name() {
		return "walnut";
	}

	//returns no projectile
	public Projectile getProjectile() {
		return null;
	}
	
	//returns false because walnut is not a projectile
	public boolean isProjectile() {
		return false;
	}

	//move
	public void move() {
	
	}
}