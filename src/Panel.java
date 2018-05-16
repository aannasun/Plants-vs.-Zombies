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

	static int LEN = 720+100;//724;
	static int WIDTH = 500;//499;
	private Lawn lawn = new Lawn();
	
	Timer timer = new Timer(125,null);
	Timer timer2 = new Timer(500, null);
	Timer timer3 = new Timer(10000, null);
	Timer peaTimer = new Timer(100, null);
	Timer movingPea = new Timer(1, null);
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
//		sp.setPreferredSize(new Dimension(LEN, WIDTH));
		frame.pack();
		frame.setVisible(true);
		sp.startGame();
	}
	
	public Panel() {
		this.setPreferredSize(new Dimension(this.LEN,this.WIDTH));
//		this.setBorder(BorderFactory.createLineBorder(Color.red));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
//				System.out.println("You just entered!! "+arg0);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
//				System.out.println("You just exited!! "+arg0);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// write your clicking code here!!
				System.out.println("You just clicked: "+arg0);
				
				if(arg0.getX() <= 720) {
					lawn.justClicked(arg0.getX(), arg0.getY());
					lawn.addStuff();	
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
		timer.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();

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
				lawn.addZombie();
				lawn.moveZombies();
				lawn.makeSunsFromSunflowers();
//				lawn.checkZombies();
				lawn.moveSuns();
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
		
		timer.start();
		timer2.start();
		timer3.start();
		peaTimer.start();
//		lawn.add("peashooter", 0, 0);
	}
	protected void tick() {
//		System.out.println("Timer went off!")
		lawn.shoot();
		repaint();
		timer2.stop();
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
//		super.paintComponent(g);
		lawn.draw(g);
	}
}
