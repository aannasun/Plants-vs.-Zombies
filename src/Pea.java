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
	private boolean hit, cold;
	private int color;
	BufferedImage pea, bpea; 
	{
		try 
		{
//			pea = ImageIO.read(new File("pea.png"));
//			bpea = ImageIO.read(new File("bpea.png"));
			pea = ImageIO.read((getClass().getResource("pea.png")));
			bpea = ImageIO.read((getClass().getResource("bpea.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Pea(int x, int y, int c) {
		this.x = x;
		this.y = y;
		color = c;
		newPea = false;
		stop = false;
	}
	
	public void draw(Graphics g) {
		if(!stop) {
			if(color == 0) {
				g.drawImage(pea, x, y, null);
			}
			if(color == 1) {
				g.drawImage(bpea, x, y, null);
			}
		}
	}
	
	public boolean newPea() {
		return newPea;
	}

	public void changeX() {
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
		stop = true;
	}
	
	public void go() {
		stop = false;
	}

	public void slow(Zombies z) {
		cold = true; 
		z.slow();
	}

	public boolean cold() {
		return cold;
	}
}
