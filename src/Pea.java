import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pea{
	private int x;
	private int y;
	private boolean stop = false;
	private boolean newPea;
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
		if(x<724) {
			x++;
		}
		else {
			stop = true;
			newPea = true;
			
		}
	}

}
