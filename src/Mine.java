import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mine extends Plants{
	
	private int xCol, yRow;
	//private int time = 0;
	private BufferedImage img, top;
	{
		try 
		{
			img = ImageIO.read(new File("mine.png"));
			top = ImageIO.read(new File("minetop.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Mine(int x, int y) {
		xCol = x;
		yRow = y;
		health = 20;
	}

	public void draw(Graphics g) {
		if (time < 10)
			g.drawImage(top, xCol, yRow, null);
		else
			g.drawImage(img, xCol, yRow, null);
		
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

	@Override
	public Projectile getProjectile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String name() {
		return "mine";
	}
}
