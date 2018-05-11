import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Lawn {

	private int x;
	private int y;
	private ArrayList<Peashooter> plants= new ArrayList<Peashooter>();
	private final int sqSide = 80;
	private String s;
	//	private Block[][] grid = new Block[5][9];
	private BufferedImage frontyard, peashooter_card;

	{
		try {
			frontyard = ImageIO.read(new File("frontyard.png"));
			peashooter_card = ImageIO.read(new File("peashooter_card.png"));
		}	
		catch(IOException e) {

		}
//		System.out.println(img);
	}

	public void draw(Graphics g) {
		g.drawImage(frontyard, 0, 0, null);
		g.drawImage(peashooter_card, 720, 0, null);
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
//		String s = JOptionPane.showInputDialog("What plant do you want");
		add(s, this.x, this.y);
		s = "";
	}
	
	public void justClicked(int x, int y) {
		this.x = (x/80)*80;
		this.y = (y/100)*100;
	}
	
	public void peashooterCard() {
		s = "peashooter";
	}
}
