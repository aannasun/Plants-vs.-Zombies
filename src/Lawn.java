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
	private int timer = -1; //timer used for zombie waves
	private ArrayList<Plants> plants= new ArrayList<Plants>(); //list containing all plants
	private ArrayList<ArrayList<Zombies>> zombies= new ArrayList<ArrayList<Zombies>>(); //list of each row, which is a list of the zombies in that row
	private ArrayList<Sun> suns = new ArrayList<Sun>(); //list of the falling suns
	private ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>(); //list of the sunflowers
	private String s;
	private boolean end = false; //loss
	private boolean toRemove = false; //for removal of plants
	private int points = 500;
	private Lawnmower[] lawnmowers = new Lawnmower[5]; //array of lawnmowers
	private boolean[] areLawnmowers = new boolean[5];
	private boolean[][] plantHere = new boolean[5][9];
	private BufferedImage lawn, frontyard, sun, sunflower_card, peashooter_card, walnut_card, cabbage_card, mine_card, snowpea_card, 
	bokchoi_card,  urbrains, shovel, end_win;

	public Lawn() {
		{
			try {
				//lawn = ImageIO.read(new File("lawn.png"));
				lawn = ImageIO.read((getClass().getResource("lawn.png")));
				frontyard = ImageIO.read((getClass().getResource("frontyard.png")));
				sun = ImageIO.read((getClass().getResource("sun.png")));
				sunflower_card = ImageIO.read((getClass().getResource("sunflower_card.png")));
				peashooter_card = ImageIO.read((getClass().getResource("peashooter_card.png")));
				walnut_card = ImageIO.read((getClass().getResource("walnut_card.png")));
				cabbage_card = ImageIO.read((getClass().getResource("cabbage_card.png")));
				mine_card = ImageIO.read((getClass().getResource("mine_card.png")));
				snowpea_card = ImageIO.read((getClass().getResource("snowpea_card.png")));
				bokchoi_card = ImageIO.read((getClass().getResource("bokchoi_card.png")));
				urbrains = ImageIO.read((getClass().getResource("urbrains.jpg")));
				shovel = ImageIO.read((getClass().getResource("shovel.png")));
				end_win = ImageIO.read((getClass().getResource("win.png")));
				
//				frontyard = ImageIO.read(new File("frontyard.png"));
//				sun = ImageIO.read(new File("sun.png"));
//				sunflower_card = ImageIO.read(new File("sunflower_card.png"));
//				peashooter_card = ImageIO.read(new File("peashooter_card.png"));
//				walnut_card = ImageIO.read(new File("walnut_card.png"));
//				cabbage_card = ImageIO.read(new File("cabbage_card.png"));
//				mine_card = ImageIO.read(new File("mine_card.png"));
//				snowpea_card = ImageIO.read(new File("snowpea_card.png"));
//				bokchoi_card = ImageIO.read(new File("bokchoi_card.png"));
//				urbrains = ImageIO.read(new File("urbrains.jpg"));
//				shovel = ImageIO.read(new File("shovel.png"));
//				end_win = ImageIO.read(new File("win.png"));
			}	
			catch(IOException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i < 5; i++) {
			zombies.add(new ArrayList<Zombies>());
			lawnmowers[i] = new Lawnmower(i);
			areLawnmowers[i] = true;
		}

		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 9; c++) {
				plantHere[r][c] = false;
			}
		}
	}

	public void draw(Graphics g) {
		boolean win = true;
		//if all the zombies have been defeated, will display win screen
		for(int i = 0; i < 5; i++) {
			if(zombies.get(i).size() != 0) {
				win = false;
			}
		}
		//if zombie reaches, will display lose screen
		if(end) {
			g.drawImage(urbrains, 0, 0, 950, 580, null);
		}
		else if(timer > 11 && win) {
			if(win) {
				g.drawImage(end_win, 0, 0, 950, 580, null);
			}
		}
		//draws the the lawn, card, zombies, plants
		else {
			g.drawImage(lawn, 0, 0, null);
			g.drawImage(sunflower_card, 850, 0, null);
			g.drawImage(peashooter_card, 850, 60, null);
			g.drawImage(walnut_card, 850, 120, null);
			g.drawImage(cabbage_card, 850, 180, null);
			g.drawImage(mine_card, 850, 240, null);
			g.drawImage(snowpea_card, 850, 300, null);
			g.drawImage(bokchoi_card, 850, 360, null);
			g.drawImage(shovel, 875, 480, null);
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
			for(Lawnmower lawnmower: lawnmowers) {
				lawnmower.draw(g);
			}

			g.drawString("" + points, 880, 500-50);
		}
	}

	//adds the plant to the lawn
	public void add(String s, int x, int y) {
		if(s==null) return;
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
		else if(s.equals("bokchoi")) {
			if(points >= 150) {
				plants.add(new Bokchoi(x, y));
				points -=150;
			}
		}
	} 

	//calls add method, then sets string back to null 
	public void addStuff() {
		add(s, this.x, this.y);
		s = "";
	}

	//sets appropriate location to add plant, and collects the sun if the sun was clicked
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

	//sets boolean so that next click will remove the plant
	public void setRemove() {
		if(toRemove) {
			toRemove = false;
		}
		else {
			toRemove = true;
		}
	}
	
	//returns toRemove
	public boolean toRemove() {
		return toRemove;
	}

	//removes the clicked plant
	public void removeStuff() {
		for(int i = 0; i < sunflowers.size(); i++) {
			if(sunflowers.get(i).getX() == x && sunflowers.get(i).getY() == y) {
				sunflowers.remove(i);
				break;
			}
		}

		for(int i = 0; i < plants.size(); i++) {
			if(plants.get(i).getX() == x && plants.get(i).getY() == y) {
				plants.remove(i);
				break;
			}
		}
	}

	//sets string to appropriate plant name
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
		else if(y > 360 && y <= 420) {
			s = "bokchoi";
		}
		else {
			s = " ";
		}
	}

	//adds zombies in waves 
	public void addZombie() {
		timer++;
		if(timer < 5) {
			Zombies z = new Zombies();
			(zombies.get(z.getRow())).add(z);
		}
		else if(timer == 7) {
			for(int i = 0; i < 5; i++) {
				(zombies.get(i)).add(new Zombies(i));
			}
		}

		else if(timer == 10) {
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

	//moves the plants 
	public void movePlants() {
		for(Plants p: plants) {
			p.move();
		}
		for(Sunflower s: sunflowers) {
			s.move();
		}
	}

	//moves the zombies
	public void moveZombies() {		
		for(ArrayList<Zombies> row: zombies) {
			for(int i = 0; i < row.size(); i++) {
				boolean atPlant = false;
				if(plants.size() > 0) {
					for(int j = 0; j < plants.size(); j++) {
						if(row.get(i).atMine(plants.get(j))) {
							row.remove(i);
							plants.remove(j);
							break;
						}
						else {
							if(row.get(i).checkIfAtPlant(plants.get(j))) {
								atPlant = true;
								plants.get(j).decreaseHealth(5);
								if(plants.get(j).getHealth() <= 0) {
									plants.remove(j);
									break;
								}
							}
							if(sunflowers.size() > 0) {
								for(int s = 0; s < sunflowers.size(); s++) {
									if(row.get(i).checkIfAtSunflower(sunflowers.get(s))) {
										atPlant = true;
										sunflowers.get(s).decreaseHealth(5);
										if(sunflowers.get(s).getHealth() <= 0) {
											sunflowers.remove(s);
											break;
										}
									}
								}
							}
						}
					}
				}
				if(row.size() > 0) {
					if(!atPlant)  {
						row.get(i).move();
					}
				}
			}
		}
		
	}

	//drops the sun from the sky
	public void dropSun() {
		suns.add(new Sun());
	}

	//moves the suns that are dropped
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

	//sunflowers produce suns
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

	//plants shoot
	public void shoot() {
		for(Plants plant: plants) {
			if((zombies.get(plant.getRow())).size() > 0) {
				plant.zombies = true;
				plant.shoot();
				if(plant.isProjMotion()) {
					for(int z = 0; z < zombies.get(plant.getRow()).size(); z++) {
						plant.zombCoord(zombies.get(plant.getRow()).get(z).getX(), zombies.get(plant.getRow()).get(z).getY());
						if(plant.projectileAtZombie(zombies.get(plant.getRow()).get(z))) {
							zombies.get(plant.getRow()).get(z).decreaseHealth(5);
						}
						if(zombies.get(plant.getRow()).get(z).getHealth() <= 0) {
							zombies.get(plant.getRow()).remove(zombies.get(plant.getRow()).get(z));
						}
					}
				}
				else if(!plant.isProjMotion()){
					for(int i = 0; i < 5; i++) {
						for(int j = 0; j < zombies.get(i).size(); j++) {
							if(plant.projectileAtZombie(zombies.get(i).get(j))) {
								zombies.get(i).get(j).decreaseHealth(5);
								if(plant.isProjectile()) {
									if(plant.getProjectile().cold()) {
										zombies.get(i).get(j).slow();
									}
								}
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
			else {
				plant.zombies = false;
				if(plant.isProjectile()) {
					plant.getProjectile().stop();
				}
			}
		}
	}

	//checks if a zombie has reached a lawnmower, or the house
	public void checkLawnmowers() {
		for(ArrayList<Zombies> row: zombies) {
			for(int i = 0; i < row.size(); i++) {
				int rowNum = zombies.indexOf(row);
				if(row.get(i).getX() <= 145) {
					if(areLawnmowers[rowNum]) {
						lawnmowers[rowNum].go();
						areLawnmowers[rowNum] = false;
						row.remove(i);
						i--;
					}
					else {
						end = true;
					}
				}
			}
		}
	}

	//moves the lawnmower once a zombie has reached it
	public void moveLawnmowers() {
		for(int i = 0; i < 5; i++) {
			if(lawnmowers[i].toGo()) {
				lawnmowers[i].move();
				for(int j = 0; j < zombies.get(i).size(); j++) {
					if(lawnmowers[i].getX() == zombies.get(i).get(j).getX()) {
						zombies.get(i).remove(j);
						j--;
					}
				}
			}
		}
	}

	//sets the plants to shoot position
	public void plantsShoot() {
		for (Plants p: plants)
			if (p.name().equals("peashooter") || p.name().equals("bokchoi")) {
				p.setShootPosition();
			}
	}

	//sets the plants to rest position
	public void plantsMove() {
		for (Plants p: plants) {
			if (p.name().equals("peashooter") || p.name().equals("bokchoi")) {
				p.setRestPosition();
			}
		}
	}
	
	//timer for the mine 
	public void potato() {
		for(Plants p: plants) {
			if(p.name().equals("mine")) {
				p.time++;
			}
		}
	}
}