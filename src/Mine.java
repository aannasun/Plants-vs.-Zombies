import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mine extends Plants{
	
	private int xCol, yRow;
	private BufferedImage img;
	{
		try 
		{
			img = ImageIO.read(new File("mine.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Mine(int x, int y) {
		xCol = x;
		yRow = y;
	}

	public void draw(Graphics g) {
		if (health>0)
			g.drawImage(img, xCol, yRow, null);
	}
}
