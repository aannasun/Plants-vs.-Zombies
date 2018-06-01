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
//			img = ImageIO.read(new File("sunflower.png"));
//			left = ImageIO.read(new File("sunflower_left.png"));
//			right = ImageIO.read(new File("sunflower_right.png"));
			img = ImageIO.read((getClass().getResource("sunflower.png")));
			left = ImageIO.read((getClass().getResource("sunflower_left.png")));
			right = ImageIO.read((getClass().getResource("sunflower_right.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Sunflower(int x, int y) {
		img = left;
		xCol = x;
		yRow = y;
	}
	

	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);
	}
	
	//moves the sunflower
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
	
	//returns x
	public int getX() {
		return xCol;
	}
	
	//returns y
	public int getY() {
		return yRow;
	}
	
	//returns a new sun (generates sun)
	public Sun newSun() {
		return new Sun(xCol + 50, yRow);
	}
	
	//increments notTimer, which is for when a sun is produced
	public void addTime() {
		notTimer++;
	}
	
	//returns notTimer
	public int getTime() {
		return notTimer;
	}
	
	//returns name
	public String name() {
		return "sunflower";
	}
	
	//decreases health
	public void decreaseHealth(int i) {
		health -= i;
	}
	
	//returns health
	public int getHealth() {
		return health;
	}
}