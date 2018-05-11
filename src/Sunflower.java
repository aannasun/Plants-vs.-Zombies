import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sunflower extends Plants{
	
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
		g.drawImage(img, xCol, yRow, null);
	}
}
