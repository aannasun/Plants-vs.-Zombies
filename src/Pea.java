import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pea{
	private int x;
	private int y;
	private int color;
	private boolean stop = false;
	private boolean newPea;
	//size = 28
	
	BufferedImage pea; 
	{
		try 
		{
			pea = ImageIO.read(new File("pea.png"));
		}
		catch(IOException e) {
		}
	}
	BufferedImage bpea; 
	{
		try 
		{
			bpea = ImageIO.read(new File("bpea.png"));
		}
		catch(IOException e) {
		}
	}
	
	public Pea() {
		
	}
	
	public Pea(int x, int y, int c) {
		this.x = x;
		this.y = y;
		color = c;
		newPea = false;
	}
	
	public void draw(Graphics g) {
		if(!stop) {
			if (color==0)
				g.drawImage(pea, x, y, null);
			if (color==1)
				g.drawImage(bpea, x, y, null);
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

	public void ifHit(Zombies z) {
		if(z.getX() == x) {
		System.out.println("hi");
			if(z.getRow() == (int)(y/80)) {
				System.out.println("hit");	
			}
		}
	}
	
	public int getRow() {
		return (int)(y/80);
	}
	
}
