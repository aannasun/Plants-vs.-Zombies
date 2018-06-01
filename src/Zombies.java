import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Zombies {

	private int health = 45;
//	private static int damage = 20;
	private int xCol, yRow;
	private BufferedImage walking_left, walking_right, blue_left, blue_right;
	private boolean left = true;
	private boolean cold = false;
	{
		try 
		{
//			walkingleft = ImageIO.read(new File("z_left.png"));
//			walkingright = ImageIO.read(new File("z_right.png"));
//			blue_left = ImageIO.read(new File("blue_z_left.png"));
//			blue_right = ImageIO.read(new File("blue_z_right.png"));
			walking_left = ImageIO.read((getClass().getResource("z_left.png")));
			walking_right = ImageIO.read((getClass().getResource("z_right.png")));
			blue_left = ImageIO.read((getClass().getResource("blue_z_left.png")));
			blue_right = ImageIO.read((getClass().getResource("blue_z_right.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public Zombies() {
		xCol =  850;
		yRow = (int)(Math.random()*5)*90+80;
	}
	
	public Zombies(int row) {
		xCol = 850;
		yRow = row*90+80;
	}
	
	public void draw(Graphics g) {
		if(health > 0) {
			if(cold) {
				if(left) {
					g.drawImage(blue_left, xCol, yRow, null);
				}
				else {
					g.drawImage(blue_right, xCol, yRow, null);
				}	
			}
			else {
				if(left) {
					g.drawImage(walking_left, xCol, yRow, null);
				}
				else {
					g.drawImage(walking_right, xCol, yRow, null);
				}
			}
		}
	}
	
	//moves the zombie
	public void move() {
		if(cold) {
			xCol-=2;
		}
		else {
			xCol-=5;
		}
		if(left) {
			left = false;
		}
		else {
			left = true;
		}
	}
	
	//checks if the zombie is at a plant
	public boolean checkIfAtPlant(Plants p) {
		if((int)(yRow/100) == (int)(p.getY()/100)) {
			if(p.getX()+70 >= xCol && xCol >= p.getX()) {
				return true;
			}
		}	
		return false;
	}
	
	//checks if the zombie is at a sunflower
	public boolean checkIfAtSunflower(Sunflower s) {
		if((int)(yRow/100) == (int)(s.getY()/100)) {
			if(s.getX()+70 == xCol) {
				return true;
			}
		}	
		return false;
	}
	
	//checks if the zombie is at a mine
	public boolean atMine(Plants p) {
		if(checkIfAtPlant(p) && p.name().equals("mine")) {
			if(p.explode()) {
				return true;
			}
		}
		return false;
	}
	
	//returns x
	public int getX() {
		return xCol;
	}
	
	//returns y
	public int getY() {
		return yRow;
	}
	
	//returns the column
	public int getCol() {
		return (int) ((xCol-145)/75);
	}
	
	//returns the row
	public int getRow() {
		return (int)((yRow-80)/90);
	}
	
	
	//returns the zombie's health
	public int getHealth() {
		return health;
	}
	
	//decreases the health
	public void decreaseHealth(int i) {
		health -= i;
	}
	
	//slows the zombie if hit by snowpea
	public void slow() {
		cold = true;
	}
}
