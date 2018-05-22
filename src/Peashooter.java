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
	private Pea p;
	private BufferedImage left, right, img1, img2;
	{
		try 
		{
			left = ImageIO.read(new File("peashooter1.png"));
			right = ImageIO.read(new File("peashooter2.png"));
			img1 = ImageIO.read(new File("peashot1.png"));
			img2 = ImageIO.read(new File("peashot2.png"));
		}

		catch(IOException e) {

		}
	}

	public Peashooter(int x, int y) {
		//		super(x, y);
		//img = left;
		xCol = x;
		yRow = y;
		health = 30;
		p = new Pea(xCol+80, yRow+20);
	}

	public void draw(Graphics g) {
		if (health>0) {
			if (shoot==true)
				g.drawImage(img1, xCol, yRow, null);
			if (shoot==false)
				g.drawImage(img2, xCol, yRow, null);
			if(p.newPea()) {
				newPea();
			}
			p.draw(g);
		}
	}

	public int getRow() {
		return (int)((yRow-80)/90);
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
