import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Lawn {

	private ArrayList<Peashooter> plants= new ArrayList<Peashooter>();
	private final int sqSide = 80;
	//	private Block[][] grid = new Block[5][9];
	private BufferedImage img;

	{
		try {
			img = ImageIO.read(new File("frontyard.png"));
		}	
		catch(IOException e) {

		}
		System.out.println(img);
	}

	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, null);
		for(Peashooter p: plants) {
			p.draw(g);
		}
	}

	public void add(String s, int x, int y) {
		if(s.equals("peashooter")) {
			plants.add(new Peashooter(x, y));
		}
	} 
	
	public void addStuff() {
		String s = JOptionPane.showInputDialog("What plant do you want");
		add(s, 0, 0);
	}
}
