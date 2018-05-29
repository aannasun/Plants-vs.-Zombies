import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bokchoi extends Plants{
	
	
	private int xCol, yRow;
	private BufferedImage left, right, img1, img2;
	{
		try 
		{
			right = ImageIO.read(new File("bokchoi1.png"));
			left = ImageIO.read(new File("bokchoi2.png"));
			img1 = ImageIO.read(new File("bokfight1.png"));
			img2 = ImageIO.read(new File("bokfight2.png"));
		}

		catch(IOException e) {

		}
	}
	
	public Bokchoi(int x, int y) {
		xCol = x;
		yRow = y;
		health = 20;
	}

	public void draw(Graphics g) {
		if (health>0) {
			if (onZombies==true) {
				if (shoot==true)
					g.drawImage(img1, xCol, yRow, null);
				if (shoot==false)
					g.drawImage(img2, xCol, yRow, null);
			}
			else {
				if (left_==true)
					g.drawImage(left, xCol, yRow, null);
				if (left_==false)
					g.drawImage(right, xCol, yRow, null);
			}
		}
	}

	@Override
	public Projectile getProjectile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String name() {
		return "bokchoi";
	}
}
