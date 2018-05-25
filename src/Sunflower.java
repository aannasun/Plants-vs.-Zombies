import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sunflower{
	
	private int health = 30;
	private int notTimer = 0;
	private int xCol, yRow;
	private boolean toLeft = true;
	private BufferedImage img, left, right;
	{
		try 
		{
			img = ImageIO.read(new File("sunflower.png"));
			left = ImageIO.read(new File("sunflower_left.png"));
			right = ImageIO.read(new File("sunflower_right.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Sunflower(int x, int y) {
		img = left;
		xCol = x;
		yRow = y;
	}
	

	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);
//		notTimer++;
//		if(notTimer % 20 == 0) {
//			Sun s = new Sun(xCol + 50, yRow);
//			s.draw(g);
//		}
	}
	
	public void move() {
		if(toLeft) {
			img = left;
			toLeft = false;
		}
		else {
			img = right;
			toLeft = true;
		}
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
