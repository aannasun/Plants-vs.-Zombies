import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sun {

	private BufferedImage img;
	private int xCol, yRow;
	private static int lawnHeight = 500, lawnWidth = 720, side = 50;
	
	{
		try 
		{
			img = ImageIO.read(new File("sun.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Sun() {
		xCol = (int)(Math.random()*lawnWidth);
		yRow = (int)(Math.random()*lawnHeight);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);
	}
	
	public void move() {
		yRow+=10;
	}
	
	public int getY() {
		return yRow;
	}
	
	public boolean checkIfClicked(int x, int y) {
		if(x >= xCol && x < xCol+side) {
			if(y >= yRow && y < yRow+side) {
				return true;
			}
		}
		return false;
	}
}

