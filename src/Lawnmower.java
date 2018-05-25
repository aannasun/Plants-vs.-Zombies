import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Lawnmower {

	private int x, y, row;
	private BufferedImage img;
	private boolean go = false;
	
	{
		try 
		{
			img = ImageIO.read(new File("lawnmower.png"));			
		}

		catch(IOException e) {
			
		}
	}
	
	public Lawnmower(int r) {
		x = 145-50;
		row = r;
		y = row*90+80;
	}
	
	public void draw(Graphics g) {
		if(x < 780) {
			g.drawImage(img, x, y, null);
		}
	}
	
	public void move() {
		if(x < 830-50) {
			x++;
		}
	}
	
	public void go() {
		go = true;
	}
	
	public boolean toGo() {
		return go;
	}
	
	public int getX() {
		return x+71;
	}
}
