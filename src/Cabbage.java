import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cabbage extends Plants {

	private int xCol, yRow;
	private CabbageP cabbageP;
	private int health = 30;
	private int zx, zy;
	private BufferedImage img;
	{
		try 
		{
			img = ImageIO.read(new File("cabbage.png"));
		}

		catch(IOException e) {

		}
	}

	public Cabbage(int x, int y) {
		xCol = x;
		yRow = y;
		cabbageP = new CabbageP(xCol+80, yRow-200, zx, zy);
	}

	public void draw(Graphics g) {
		if (health>0) {
			g.drawImage(img, xCol, yRow, null);
			
				if(cabbageP.newCabbage()) {
					newCabbage();
				}
				cabbageP.draw(g);
			}
	}

	private void newCabbage() {
		cabbageP = new CabbageP(xCol+80, yRow-200, zx, zy);
	}

	public String name() {
		return "cabbage";
	}

	public void shoot() {
		cabbageP.shoot();
	}

	@Override
	public Projectile getProjectile() {
		return cabbageP;
	}


	@Override
	public boolean isProjectile() {
		return true;
	}

	public boolean isProjMotion() {
		return true;
		
	}

	public void zombCoord(int x, int y) {
		zx = x;
		zy = y;
	}

	public boolean projectileAtZombie(Zombies z) {
		return cabbageP.atZombie(z);
	}
	
	public int getRow() {
		return (int)((yRow-80)/90);
	}
}

