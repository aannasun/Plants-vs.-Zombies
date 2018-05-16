import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Walnut extends Plants{
	
	private int health = 100;
	private int xCol, yRow;
	private BufferedImage img;
	{
		try 
		{
			img = ImageIO.read(new File("walnut.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Walnut(int x, int y) {
		xCol = x;
		yRow = y;
	}

	public void draw(Graphics g) {
		if (health>0)
			g.drawImage(img, xCol, yRow, null);
	}
}
