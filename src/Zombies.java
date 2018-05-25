import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Zombies {

	private int health = 30;
	private static int damage = 20;
	private int xCol, yRow;
	private BufferedImage img, broken, walkingleft, walkingright;
	private int twoSeconds = 0;
	private boolean left = true;
	{
		try 
		{
			img = ImageIO.read(new File("zombie.png"));
			walkingleft = ImageIO.read(new File("z_left.png"));
			walkingright = ImageIO.read(new File("z_right.png"));
		}

		catch(IOException e) {

		}
	}
	public Zombies() {
//		xCol = (9*80);
		xCol =  850;
//		yRow = (int)(Math.random()*5)*100;
		yRow = (int)(Math.random()*5)*90+80;
	}
	
	public Zombies(int row) {
//		xCol = (9*80);
//		yRow = row*100;
		xCol = 850;
		yRow = row*90+80;
	}
	
	public void draw(Graphics g) {
		if(health > 0) {
			if(left) {
				g.drawImage(walkingleft, xCol, yRow, null);
			}
			else {
				g.drawImage(walkingright, xCol, yRow, null);
			}
//			g.drawImage(img, xCol, yRow, null);
		}
	}
	
//	public void stopMoving() {
//		xCol = xCol;
//	}
//	
	public void move() {
		xCol-=5;
		if(left) {
			left = false;
		}
		else {
			left = true;
		}
	}
	
	public boolean checkIfAtPlant(Plants p) {
//		System.out.println("Z:" + xCol + " " + yRow/100);
//		System.out.println("P:" + p.getX() + " " + p.getY()/100);
		if((int)(yRow/100) == (int)(p.getY()/100)) {
			if(p.getX()+70 == xCol) {
				return true;
			}
		}	
		return false;
	}
	
	public int getX() {
		return xCol;
	}
	
	public int getY() {
		return yRow;
	}
	
	public int getCol() {
//		return (int)(xCol/80);
		return (int) ((xCol-145)/75);
	}
	
	public int getRow() {
//		return (int)(yRow/100);
		return (int)((yRow-80)/90);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decreaseHealth(int i) {
		health -= i;
	}
}
