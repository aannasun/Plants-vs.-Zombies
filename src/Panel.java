import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Panel extends JPanel {

	static int LEN = 950;//720+100;//724;
	static int WIDTH = 550; //500;//499;
	private Lawn lawn = new Lawn();
	
	Timer timer = new Timer(125,null); //lawn.shoot, peaTimer.stop OKAY WAIT THIS DOESNT DO ANYTHING I THINK
	Timer timer2 = new Timer(250, null); //lawn.addZombie(); lawn.moveZombies(); lawn.makeSunsFromSunflowers();
	Timer timer3 = new Timer(10000, null); //lawn.dropSun();
	Timer peaTimer = new Timer(10, null); //make movingPea timer
	Timer movingPea = new Timer(5, null); //lawn.shoot(), peaTimer.stop()
	Timer sunTimer = new Timer(50, null); //lawn.moveSuns()
	Timer zWaves = new Timer(10000, null); //make zombie waves
	Timer movePlants = new Timer(500, null); //move the plants
	Timer lawnMowers = new Timer(5, null); //for the progress of the lawnmowers
	//
	Timer potatoTime = new Timer(500, null); //for the mines
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("PvZ!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel sp = new Panel();
		frame.add(sp);
		frame.pack();
		frame.setVisible(true);
		sp.startGame();
	}
	
	public Panel() {
		this.setPreferredSize(new Dimension(this.LEN,this.WIDTH));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				if((arg0.getX() <= 850-25) && arg0.getY() <= 550-25) {
					lawn.justClicked(arg0.getX(), arg0.getY());
					if(lawn.toRemove()) {
						lawn.removeStuff();
						lawn.setRemove();
					}
					else {
						lawn.addStuff();	
					}
				}
				else if(arg0.getY() >= 480 && arg0.getY() < 480+54) {
					lawn.setRemove();
				}
				else {
					lawn.card(arg0.getY());
				}
				repaint(); 
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
			
		});
	}
	
	private void startGame() {
		lawnMowers.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lawn.moveLawnmowers();
			}
			
		});
		zWaves.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lawn.addZombie();
				repaint();
			}
			
		});
		movePlants.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lawn.plantsMove();
				lawn.plantsShoot();
				repaint();
			}
			
		});
		peaTimer.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick2();

			}
			
		});
		timer2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lawn.moveZombies();
				lawn.checkLawnmowers();
				lawn.makeSunsFromSunflowers();
				repaint();
			}
		});
		timer3.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lawn.dropSun();
				repaint();
			}
			
		});
		
		sunTimer.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lawn.moveSuns();
				repaint();
			}
			
		});

		potatoTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawn.potato();
				repaint();
			}
		});
		
		lawnMowers.start();
		zWaves.start();
		timer2.start();
		timer3.start();
		peaTimer.start();
		sunTimer.start();
		movePlants.start();
		potatoTime.start();
	}

	protected void tick() {
		lawn.shoot();
		repaint();
		peaTimer.stop();
	}
	
	protected void tick2() {
		movingPea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}
		});
		movingPea.start();
	}
	
	public void paintComponent(Graphics g) {
		lawn.draw(g);
	}
}