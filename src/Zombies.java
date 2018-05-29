import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Zombies {

	private int health = 30;
	private static int damage = 20;
	private int xCol, yRow;
	private boolean cold = false;
	private BufferedImage img, broken, blue;
	private int twoSeconds = 0;
	{
		try 
		{
			img = ImageIO.read(new File("zombie.png"));
			blue = ImageIO.read(new File("blue_zombie.png"));
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
			g.drawImage(img, xCol, yRow, null);
		}
		if (cold){
//			if(twoSeconds < 3) {
//				g.drawImage(broken, xCol yRow, null);
//			}
			g.drawImage(blue, xCol, yRow, null);
		}
	}
	
//	public void stopMoving() {
//		xCol = xCol;
//	}
//	
	public void move() {
		if(cold) {
			xCol -= 1;
		}
		else xCol-=5;
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
	
	public void slow() {
		cold = true;
	}

	
	
}
