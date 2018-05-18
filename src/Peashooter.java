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
	private BufferedImage img, pea1, pea3;
	{
		try 
		{
			img = ImageIO.read(new File("peashooter.png"));
		}

		catch(IOException e) {

		}
	}

	public Peashooter(int x, int y) {
		xCol = x;
		yRow = y;
		p = new Pea(xCol+100, yRow-30, 0);
	}

	public void draw(Graphics g) {
		if (health>0) {
			g.drawImage(img, xCol, yRow, null);
			if(p.newPea()) {
				newPea();
			}
			p.draw(g);
		}
	}

	public String name() {
		return "peashooter";
	}

	public void newPea() {
		p = new Pea(xCol+80, yRow+20, 0);
	}

	public Pea getPea() {
		return p;
	}
	
//	public void shoot() {
//
//		p.changeX();
//
//	}
}
