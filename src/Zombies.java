import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Zombies {

	private static int damage = 20;
	private int xCol, yRow;
	private BufferedImage img;
	{
		try 
		{
			img = ImageIO.read(new File("zombie.png"));
		}

		catch(IOException e) {

		}
	}
	public Zombies() {
		xCol = (9*80);
		yRow = (int)(Math.random()*4*100);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);
	}
	
	public void move() {
		xCol-=5;
	}
}
