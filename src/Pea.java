import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pea extends Projectile{
	private int x;
	private int y;
	private boolean stop;
	private boolean newPea;
	private boolean hit;
	BufferedImage pea; 
	{
		try 
		{
			pea = ImageIO.read(new File("pea.png"));
		
			
		}

		catch(IOException e) {

		}
	}
	
	
	public Pea(int x, int y) {
		this.x = x;
		this.y = y;
		newPea = false;
		stop = false;
	}
	
	public void draw(Graphics g) {
		if(!stop) {
			g.drawImage(pea, x, y, null);
		}
		
		
	}
	
	public boolean newPea() {
		return newPea;
	}

	public void changeX() {
		// TODO Auto-generated method stub
		if(x<830) {
			x++;
		}
		else {
			stop = true;
			newPea = true;
			
		}
	}
	
	public boolean atZombie(Zombies z) {
		if(z.getX() == x) {
			if(z.getY() <= y && (z.getY()+100 > y)){
//				stop = true;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Point getCoord() {
		
		return new Point(x,y);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		stop = true;
	}
	
	public void go() {
		stop = false;
	}

}
