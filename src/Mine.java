import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mine extends Plants{
	
	private BufferedImage img, top;
	{
		try 
		{
//			img = ImageIO.read(new File("mine.png"));
//			top = ImageIO.read(new File("minetop.png"));
			img = ImageIO.read((getClass().getResource("mine.png")));
			top = ImageIO.read((getClass().getResource("minetop.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Mine(int x, int y) {
		setX(x);
		setY(y);
		setHealth(20);
	}

	public void draw(Graphics g) {
		if (time < 10) {
			g.drawImage(top, getX(), getY(), null);
		}
		else {
			g.drawImage(img, getX(), getY(), null);
		}
	}
	
	//returns plant name
	public String name() {
		return "mine";
	}

	//returns the projectile
	public Projectile getProjectile() {
		return null;
	}

	//returns false because a mine has no projectile
	public boolean isProjectile() {
		return false;
	}
		
	//returns true when the mine is ready to explode
	public boolean explode() {
		if(time < 10) {
			return false;
		}
		return true;
	}

	public void move() {
		
	}
}