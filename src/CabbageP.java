import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CabbageP extends Projectile{
	private boolean stop;
	private int x;
	private int y;
	private int zx, zy;
	private double m;
	private boolean newCabbage;
	private BufferedImage img;{
		try {
//			img = ImageIO.read(new File("cabbageP.png"));
			img = ImageIO.read((getClass().getResource("cabbageP.png")));
		}
		catch(IOException e) {

		}
	}

	public CabbageP(int x, int y, int zx, int zy) {
		this.x = x;
		this.y = y;
		this.zx = zx;
		this.zy = zy;
		m = (zx-x)/2.2 + x;
		newCabbage = false;
		stop = false;
	}

	public void draw(Graphics g) {
		if(!stop)
			g.drawImage(img, x, y, null);
	}

	//returns coordinate of projectile
	public Point getCoord() {
		return new Point(x,y);
	}

	//sets stop to true
	public void stop() {
		stop = true;
	}

	//creates new cabbage
	public boolean newCabbage() {
		return newCabbage;
	}

	//shoots projectile
	public void shoot() {
		if(x<830) {
			x++;
			y = zy + (int) (0.0009*(x-m)*(x-m) -70);		
		}
		else {
			stop = true;
			newCabbage = true;		
		}
	}
	
	//returns true is projectile at zombie
	public boolean atZombie(Zombies z) {
		if(z.getX() == x) {
			if(z.getY() <= y && z.getY() <= y+31 && (z.getY()+100 > y) && (z.getY()+100 > y+31)){
				System.out.println("at zombie");
				return true;
			}
		}	
		return false;
	}

	//not used
	public void go() {

	}

	//returns false because cabbage projectile has no freezing effect
	public boolean cold() {
		// TODO Auto-generated method stub
		return false;
	}
}