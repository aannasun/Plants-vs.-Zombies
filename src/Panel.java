import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Panel extends JPanel {

	static int LEN = 724;
	static int WIDTH = 499;
	private Lawn lawn = new Lawn();
	
	Timer timer = new Timer(125,null);
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
		timer.start();
//		lawn.add("peashooter", 0, 0);
		lawn.addStuff();
		repaint();
	}
	protected void tick() {
//		System.out.println("Timer went off!");
	}

	
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		lawn.draw(g);
	}
}