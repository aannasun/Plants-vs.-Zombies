import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Walnut extends Plants{
	
	private int health = 100;
	private int xCol, yRow;
	private BufferedImage img, half;
	{
		try 
		{
			img = ImageIO.read(new File("walnut.png"));
			half = ImageIO.read(new File("half_walnut.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Walnut(int x, int y) {
		xCol = x;
		yRow = y;
	}

	public void draw(Graphics g) {
		if (health < 50)
			g.drawImage(half, xCol, yRow, null);
		else if (health > 0) {
			g.drawImage(img, xCol, yRow, null);
		}
		
	}

	@Override
	public boolean isProjectile() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void decreaseHealth(int h) {
		health -= h;
	}
	
	public int getY() {
		return yRow;
	}
	public int getX() {
		return xCol;
	}
	
	public int getHealth() {
		return health;
	}
}
