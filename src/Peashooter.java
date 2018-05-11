import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Peashooter extends Plant{

	private static int damage = 20;
//	private static int X_LEN  = 80;
//	private static int Y_WIDTH = 100;
	private int xCol, yRow;
	private BufferedImage img;
	{
		try 
		{
			img = ImageIO.read(new File("peashooter.png"));
		}

		catch(IOException e) {

		}
	}

	public Peashooter(int x, int y) {
		xCol = x;
		yRow = y;
	}

	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);
	}
}
