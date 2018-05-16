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
	private ArrayList<Plants> plants= new ArrayList<Plants>();
	private ArrayList<Zombies> z = new ArrayList<Zombies>();
	private ArrayList<Sun> suns = new ArrayList<Sun>();
	private ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
//	private final int SIDE = 80;
	private String s;
	private int numZ = 5;
	private int points = 50;
	//card height = 60;
	//	private Block[][] grid = new Block[5][9];
	private BufferedImage frontyard, sun, sunflower_card, peashooter_card, walnut_card, cabbage_card, mine_card, snowpea_card;

	{
		try {
			frontyard = ImageIO.read(new File("frontyard.png"));
			sun = ImageIO.read(new File("sun.png"));
			sunflower_card = ImageIO.read(new File("sunflower_card.png"));
			peashooter_card = ImageIO.read(new File("peashooter_card.png"));
			walnut_card = ImageIO.read(new File("walnut_card.png"));
			cabbage_card = ImageIO.read(new File("cabbage_card.png"));
			mine_card = ImageIO.read(new File("mine_card.png"));
			snowpea_card = ImageIO.read(new File("snowpea_card.png"));
		}	
		catch(IOException e) {

		}
	}

	public void draw(Graphics g) {
		g.drawImage(frontyard, 0, 0, null);
		g.drawImage(sunflower_card, 720, 0, null);
		g.drawImage(peashooter_card, 720, 60, null);
		g.drawImage(walnut_card, 720, 120, null);
		g.drawImage(cabbage_card, 720, 180, null);
		g.drawImage(mine_card, 720, 240, null);
		g.drawImage(snowpea_card, 720, 300, null);
		for(Plants p: plants) {
			p.draw(g);
		}
		for(Zombies zombies: z) {
			zombies.draw(g);
		}
		for(Sun s: suns) {
			s.draw(g);
		}
		for(Sunflower sunflower: sunflowers) {
			sunflower.draw(g);
		}
		g.drawString("" + points, 750, 500-50);
	}

	public void add(String s, int x, int y) {
		if(s.equals("sunflower")) {
			if(points >= 50) {
				sunflowers.add(new Sunflower(x, y));
				points -= 50;
			}
		}
		else if(s.equals("peashooter")) {
			if(points >= 100) {
				plants.add(new Peashooter(x, y));
				points -=100;
			}
		}
		else if (s.equals("walnut")) {
			if(points >= 50) {
				plants.add(new Walnut(x, y));
				points -= 50;
			}
		}
		else if (s.equals("cabbage")) {
			if(points >= 100) {
			plants.add(new Cabbage(x, y));
			points -=100;
			}
		}
		else if(s.equals("mine")) {
			if(points >= 25) {
				plants.add(new Mine(x, y));
				points -=25;
			}
		}
		else if(s.equals("snowpea")) {
			if(points >= 175) {
				plants.add(new SnowPea(x, y));
				points -=175;
			}
		}
	} 

	public void addStuff() {
		add(s, this.x, this.y);
		s = "";
	}

	public void justClicked(int x, int y) {
		this.x = (x/80)*80;
		this.y = (y/100)*100;
		for(int i = suns.size()-1; i>-1; i--) {
			if(suns.get(i).checkIfClicked(x, y)) {
				suns.remove(i);
				points +=50;
			}
		}
	}

	public void card(int y) {
		if(y >= 0 && y <= 60) {
			s = "sunflower";
		}
		else if(y > 60 && y <= 120) {
			s = "peashooter";
		}
		else if(y > 120 && y <= 180) {
			s = "walnut";
		}
		else if(y > 180 && y <= 240) {
			s = "cabbage";
		}
		else if(y > 240 && y <= 300) {
			s = "mine";
		}
		else if(y > 300 && y <= 360) {
			s = "snowpea";
		}
	}

	public void addZombie() {
		if(numZ > 0) {
			z.add(new Zombies());
			numZ--;
		}
	}

	public void moveZombies() {
		for(Zombies zombie: z) {
			zombie.move();
		}
	}

	public void dropSun() {
		suns.add(new Sun());
	}

	public void moveSuns() {
		for(Sun s: suns) {
			if(s.getY() <= 500) {
				s.move();
			}
		}
		for(int i = suns.size()-1; i>-1; i--) {
			if(suns.get(i).getY()>500) {
				suns.remove(i);
			}
		}
	}
	
	public void makeSunsFromSunflowers() {
		for(Sunflower sunflower:sunflowers) {
			sunflower.addTime();
			if(sunflower.name().equals("sunflower")) {
				if(sunflower.getTime() % 25 == 0) {
					suns.add(sunflower.newSun());
				}
			}
		}
	}
	
	public void shoot() {
		// TODO Auto-generated method stub
		for(Plants p: plants) {
			p.shoot();
		}
	}
}
