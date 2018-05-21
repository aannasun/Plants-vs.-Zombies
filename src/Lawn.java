import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Lawn {

	private int x, y; 
	private int timer = -1;
	private ArrayList<Plants> plants= new ArrayList<Plants>();
	private ArrayList<ArrayList<Zombies>> zombies= new ArrayList<ArrayList<Zombies>>();
	private ArrayList<Sun> suns = new ArrayList<Sun>();
	private ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
	private String s;
	private int points = 400;
	private BufferedImage lawn, frontyard, sun, sunflower_card, peashooter_card, walnut_card, cabbage_card, mine_card;
	
	public Lawn() {
		{
			try {
				lawn = ImageIO.read(new File("lawn.png"));
				frontyard = ImageIO.read(new File("frontyard.png"));
				sun = ImageIO.read(new File("sun.png"));
				sunflower_card = ImageIO.read(new File("sunflower_card.png"));
				peashooter_card = ImageIO.read(new File("peashooter_card.png"));
				walnut_card = ImageIO.read(new File("walnut_card.png"));
				cabbage_card = ImageIO.read(new File("cabbage_card.png"));
				mine_card = ImageIO.read(new File("mine_card.png"));
			}	
			catch(IOException e) {

			}
		}

		for(int i = 0; i < 5; i++) {
			zombies.add(new ArrayList<Zombies>());
		}
	}

	public void draw(Graphics g) {
		g.drawImage(lawn, 0, 0, null);
		g.drawImage(sunflower_card, 850, 0, null);
		g.drawImage(peashooter_card, 850, 60, null);
		g.drawImage(walnut_card, 850, 120, null);
		g.drawImage(cabbage_card, 850, 180, null);
		g.drawImage(mine_card, 850, 240, null);
		for(Plants p: plants) {
			p.draw(g);
		}
		for(ArrayList<Zombies> row: zombies) {
			for(Zombies z: row) {
				z.draw(g);
			}
		}
		for(Sun s: suns) {
			s.draw(g);
		}
		for(Sunflower sunflower: sunflowers) {
			sunflower.draw(g);
		}
		g.drawString("" + points, 880, 500-50);
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
	} 

	public void addStuff() {
		add(s, this.x, this.y);
		s = "";
	}

	public void justClicked(int x, int y) {
		int a = x-150;
		int b = y-80;
		this.x = (a/75)*75+150;
		this.y = (b/90)*90+80;
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
	}

	public void addZombie() {
		timer++;
		if(timer < 5) {
			Zombies z = new Zombies();
			(zombies.get(z.getRow())).add(z);
		}
		else if(timer == 9) {
			for(int i = 0; i < 5; i++) {
				(zombies.get(i)).add(new Zombies(i));
			}
		}

		else if(timer < 11) {
			Zombies z = new Zombies();
			(zombies.get(z.getRow())).add(z);
		}
		else if(timer == 11) {
			for(int j = 0; j < 2; j++) {
				for(int i = 0; i < 5; i++) {
					(zombies.get(i)).add(new Zombies(i));
				}
			}
		}

	}

	public void movePlants() {
		for(Plants p: plants) {
			p.move();
		}
		for(Sunflower s: sunflowers) {
			s.move();
		}
	}

	public void moveZombies() {
		
		for(ArrayList<Zombies> row: zombies) {
			for(Zombies z: row) {
				boolean atPlant = false;
				if(plants.size() > 0) {
					for(Plants p: plants) {
						if(z.checkIfAtPlant(p)) {
							atPlant = true;
							p.decreaseHealth(5);
							if(p.getHealth() <= 0) {
								plants.remove(p);
								break;
							}
						}
					}
				}
				if(!atPlant) {
					z.move();
				}
			}
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
		for(Plants plant: plants) {
			plant.shoot();
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < zombies.get(i).size(); j++) {
						if(plant.projectileAtZombie(zombies.get(i).get(j))) {
							zombies.get(i).get(j).decreaseHealth(5);
							if(zombies.get(i).get(j).getHealth() <= 0) {
								zombies.get(i).remove(zombies.get(i).get(j));
							}
						}
					}
				}
				for(ArrayList<Zombies> row: zombies) {
					for(Zombies z : row){
						if(plant.projectileAtZombie(z)){
							plant.getProjectile().stop();
						}
					}
				}
		}
	}
}
