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
//			img = ImageIO.read(new File("lawnmower.png"));	
			img = ImageIO.read((getClass().getResource("lawnmower.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
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
	
	//moves the lawnmower
	public void move() {
		if(x < 830-50) {
			x++;
		}
	}
	
	//sets the lawnmower so it is ready to go
	public void go() {
		go = true;
	}
	
	//returns go
	public boolean toGo() {
		return go;
	}
	
	//returns x
	public int getX() {
		return x+71;
	}
}
