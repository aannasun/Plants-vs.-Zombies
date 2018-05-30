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
	private int timer = -1;//timer used for zombie waves
	private ArrayList<Plants> plants= new ArrayList<Plants>();
	//	private ArrayList<Zombies> z = new ArrayList<Zombies>();
	private ArrayList<ArrayList<Zombies>> zombies= new ArrayList<ArrayList<Zombies>>();
	private ArrayList<Sun> suns = new ArrayList<Sun>();
	private ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
	//	private final int SIDE = 80;
	private String s;
	private boolean end = false;
	//	private int numZ = 2;//5;
	private int points = 500;
//	private ArrayList<Lawnmower> lawnmowers = new ArrayList<Lawnmower>();
	private Lawnmower[] lawnmowers = new Lawnmower[5];
	private boolean[] areLawnmowers = new boolean[5];
	//card height = 60;
	//private Block[][] grid = new Block[5][9];
	private boolean[][] plantHere = new boolean[5][9];
	private BufferedImage lawn, frontyard, sun, sunflower_card, peashooter_card, walnut_card, cabbage_card, mine_card, snowpea_card, bokchoi_card, urbrains;
	/*
	 *  added lawn as an image, used it instead of the frontyard pic, changed dimensions of panel to 950 by 550
	 *  actual lawn starts at x = 145
	 *  changed location of cards and points
	 *  changed addZombies, drawZombies, moveZombies, shoot methods
	 */

	/* 
	 * TO DO:
	 * make it so that you can't put a plant down if there is one already there
	 * the shooting zombies/health decrements are weird
	 * sizes
	 * make it so that if the zombie in front is eating, it stops
	 */
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
				snowpea_card = ImageIO.read(new File("snowpea_card.png"));
				bokchoi_card = ImageIO.read(new File("bokchoi_card.png"));
				urbrains = ImageIO.read(new File("urbrains.png"));
			}	
			catch(IOException e) {

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
		if(end) {
			g.drawImage(urbrains, 0, 0, 950, 580, null);
		}
		else {
			g.drawImage(lawn, 0, 0, null);
		//		g.drawImage(frontyard, 0, 0, null);
		//		g.drawImage(sunflower_card, 720, 0, null);
		//		g.drawImage(peashooter_card, 720, 60, null);
		//		g.drawImage(walnut_card, 720, 120, null);
		//		g.drawImage(cabbage_card, 720, 180, null);
		//		g.drawImage(mine_card, 720, 240, null);
			g.drawImage(sunflower_card, 850, 0, null);
			g.drawImage(peashooter_card, 850, 60, null);
			g.drawImage(walnut_card, 850, 120, null);
			g.drawImage(cabbage_card, 850, 180, null);
			g.drawImage(mine_card, 850, 240, null);
			g.drawImage(snowpea_card, 850, 300, null);
			g.drawImage(bokchoi_card, 850, 360, null);
			for(Plants p: plants) {
				p.draw(g);
			}
		//		for(Zombies zombies: z) {
		//			zombies.draw(g);
		//		}
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

	public void add(String s, int x, int y) {
//		if(!plantHere[(int)((y-80)/90)][(int)((x-145)/80)]) {
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
//			plantHere[(int)((y-80)/90)][(int)((x-145)/80)] = true;
//		}
	} 

	public void addStuff() {
		add(s, this.x, this.y);
		s = "";
	}

	public void justClicked(int x, int y) {
		int a = x-150;
		int b = y-80;
		//		this.x = (x/80)*80;
		//		this.y = (y/100)*100;
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
		else if(y > 300 && y <= 360) {
			s = "snowpea";
		}
		else if(y > 360 && y <= 420) {
			s = "bokchoi";
		}
		else {
			s = "";
		}
	}

	public void addZombie() {
		//		if(numZ > 0) {
		//			z.add(new Zombies());
		//			numZ--;
		//		}
		timer++;
		if(timer < 5) {
			//			System.out.println(timer);
			//			z.add(new Zombies());
			Zombies z = new Zombies();
			(zombies.get(z.getRow())).add(z);

			//			int i = (int)(Math.random()*5)
		}
		else if(timer == 9) {
			for(int i = 0; i < 5; i++) {
				//				z.add(new Zombies(i));
				(zombies.get(i)).add(new Zombies(i));
			}
		}

		else if(timer < 11) {
			//			z.add(new Zombies());
			Zombies z = new Zombies();
			(zombies.get(z.getRow())).add(z);
		}
		else if(timer == 11) {
			for(int j = 0; j < 2; j++) {
				for(int i = 0; i < 5; i++) {
					//					z.add(new Zombies(i));
					(zombies.get(i)).add(new Zombies(i));
				}
			}
		}
		//		z.add(new Zombies());

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
		//		boolean atPlant = false;
		//		for(Zombies zombie: z) {
		//			boolean atPlant = false;
		//			if(plants.size() > 0) {
		//				//				for(int i = 0; i < plants.size(); i++) {
		//				for(Plants p: plants) {
		//					//	System.out.println(p.name());
		//					//	System.out.println(plants.indexOf(p));
		//					//	Plants p = plants.get(i);
		//					if(zombie.checkIfAtPlant(p)) {
		//						//		System.out.println(p.name());
		//						atPlant = true;
		//						p.decreaseHealth(5);
		//						if(p.getHealth() <= 0) {
		//							plants.remove(p);
		//							break;
		//						}
		//					}
		//					//	else {
		//					//		System.out.println("move");
		//					//		zombie.move();
		//					//	}
		//				}
		//			}
		//			//else {
		//			//	zombie.move();
		//			//}
		//
		//			if(!atPlant) {
		//				zombie.move();
		//			}
		//		}

		for(ArrayList<Zombies> row: zombies) {
			for(Zombies z: row) {
				boolean atPlant = false;
				if(plants.size() > 0) {
					for(Plants p: plants) {
						if(z.checkIfAtPlant(p)) {
							if (p.name().equals("mine")) {
								row.remove(z);
								plants.remove(p);
							}
							atPlant = true;
							p.decreaseHealth(5);
							if(p.getHealth() <= 0) {
//								plantHere[p.getRow()][p.getCol()] = false;
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
		
//			int row = plant.getRow();
//			System.out.println(plant.getRow());
//			plant.shoot();
			if((zombies.get(plant.getRow())).size() > 0) {
				//start of new stuff
//				plant.zombies = true;
				//end of new stuff
				plant.shoot();
//				plant.getProjectile().go();
				////			for(Zombies zombie: z) {
				//			for(int i = 0; i < z.size(); i++) {
				//				//	if(plant.getRow() == zombie.getRow()) {
				////				plant.shoot();
				//				//	}
				//				if(plant.projectileAtZombie(z.get(i))) {
				//				//System.out.println(z.indexOf(zombie));
				//					z.get(i).decreaseHealth(5);
				//					if(z.get(i).getHealth() <= 0) {
				//						z.remove(z.get(i));
				//						//break;
				//					}
				//				}
				//			}
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < zombies.get(i).size(); j++) {
						if(plant.isProjMotion()) {
							plant.zombCoord(zombies.get(i).get(j).getX(), zombies.get(i).get(j).getY());
							plant.shoot();
						}
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
			//more added stuff
			else {
//				plant.zombies = false;
				if(plant.isProjectile()) {
					plant.getProjectile().stop();
				}
			}
		}
	}
	
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
	
	public void potato() {
		//System.out.println("hiii");
		for (Plants p: plants) {
			//System.out.println(p.name());
			if (p.name().equals("mine")) {
				//System.out.println("potato");
				p.time++;
			}
		}
		
	}
	
	public void plantsShoot() {
		for (Plants p: plants)
			if (p.name().equals("peashooter"))
				p.setShootPosition();
	}
	
	public void plantsMove() {
		for (Plants p: plants) {
			if (p.name().equals("peashooter")) {
				p.setRestPosition();
			}
		}
	}
}
