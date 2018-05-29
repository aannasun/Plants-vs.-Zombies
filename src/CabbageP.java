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
			img = ImageIO.read(new File("cabbageP.png"));
		}
		catch(IOException e) {

		}
	}

	public CabbageP(int x, int y, int zx, int zy) {
		this.x = x;
		this.y = y;
		this.zx = zx;
		this.zy = zy;
		m = (zx-x)/1.5 + x;
		newCabbage = false;
		stop = false;
	}

	public void draw(Graphics g) {
		if(!stop)
			g.drawImage(img, x, y, null);
	}

	@Override
	public Point getCoord() {
		return new Point(x,y);
	}

	@Override
	public void stop() {
		stop = true;
	}

	@Override
	public void go() {
		stop = false;

	}
	
	public boolean newCabbage() {
		return newCabbage;
	}

	public void shoot() {

		if(x<830) {
			x++;
			y = zy + (int) (0.001*(x-m)*(x-m) );
			
		}
		else {
			stop = true;
			newCabbage = true;
			
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

}
