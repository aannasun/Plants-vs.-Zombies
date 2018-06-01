import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sun {

	private BufferedImage img;
	private int xCol, yRow, stop;
	private static int lawnHeight = 550, //500, 
			lawnWidth = 720, 
			side = 50;
	
	{
		try 
		{
//			img = ImageIO.read(new File("sun.png"));
			img = ImageIO.read((getClass().getResource("sun.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Sun() {
		xCol = (int)(Math.random()*(lawnWidth-side))+145;
		stop = (int)(Math.random()*lawnHeight);
		yRow = 0;
	}
	
	public Sun(int x, int y) {
		xCol = x;
		yRow = y;
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);
	}
	
	//moves the sun
	public void move() {
		if(yRow <= stop) {
			yRow+=1;
		}
	}
	
	//returns y
	public int getY() {
		return yRow;
	}
	
	//returns true if the sun was clicked
	public boolean checkIfClicked(int x, int y) {
		if(x >= xCol && x < xCol+side) {
			if(y >= yRow && y < yRow+side) {
				return true;
			}
		}
		return false;
	}
}
