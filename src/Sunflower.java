import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sunflower{
	
	private int health = 30;
	private int notTimer = 0;
	private int xCol, yRow;
	private BufferedImage img;
	{
		try 
		{
			img = ImageIO.read(new File("sunflower.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Sunflower(int x, int y) {
		xCol = x;
		yRow = y;
	}
	

	public void draw(Graphics g) {
		if (health>0)
			g.drawImage(img, xCol, yRow, null);
	}
	
	public Sun newSun() {
		return new Sun(xCol + 50, yRow);
	}
	
	public void addTime() {
		notTimer++;
	}
	
	public int getTime() {
		return notTimer;
	}
	
	public String name() {
		return "sunflower";
	}
}
