import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Peashooter extends Plants{

	private static int damage = 20;
	//	private static int X_LEN  = 80;
	//	private static int Y_WIDTH = 100;
	private int xCol, yRow;
	private boolean toLeft = true;
	private Pea p;
	private BufferedImage img, left, right;
	{
		try 
		{
			img = ImageIO.read(new File("peashooter.png"));
			left = ImageIO.read(new File("peashooter_left.png"));
			right = ImageIO.read(new File("peashooter_right.png"));
		}

		catch(IOException e) {

		}
	}

	public Peashooter(int x, int y) {
		img = left;
		xCol = x;
		yRow = y;
		health = 30;
		p = new Pea(xCol+80, yRow+20);
	}

	public void draw(Graphics g) {
		g.drawImage(img, xCol, yRow, null);

		if(p.newPea()) {
			newPea();
		}
		p.draw(g);
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
	
	public String name() {
		return "peashooter";
	}

	public void newPea() {
		p = new Pea(xCol+80, yRow+20);
	}

	public boolean projectileAtZombie(Zombies z) {
		return p.atZombie(z);
	}

	public int getX() {
		return xCol;
	}
	
	public int getY() {
		return yRow;
	}
	
	public void shoot() {	
		p.changeX();	
	}

	@Override
	public Projectile getProjectile() {
		// TODO Auto-generated method stub
		return p;
	}
}
