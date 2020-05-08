import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * A Class to run our game
 *
 * @author Ethan Ling, Hangyul Yun, John Whitney
 * @version 05/03/2020
 */
public class Runner extends JPanel implements ActionListener, KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	// ==FIELDS==
    private final Font font = new Font("ComicSans", Font.PLAIN, 20);
    private int health = 100;
    private int score = 0;
    private int highScore;
    private boolean pressedUp;
    private boolean pressedDown;
    private boolean pressedLeft;
    private boolean pressedRight;

    private ImageIcon playerImg;
    private ImageIcon enemyImg;
    private ImageIcon lemonImg;
    private PlayerCharacter player;
    private ArrayList<Asteroid> enemies;
    
    public Runner() {
    	playerImg = new ImageIcon(Main.class.getResource("/assets/icon.png"));
    	enemyImg =  new ImageIcon(Main.class.getResource("/assets/lil.png"));
    	lemonImg = new ImageIcon(Main.class.getResource("/assets/lemon.png"));
    	player = new PlayerCharacter(Main.DRAWING_WIDTH/2, Main.DRAWING_HEIGHT/2, 2, playerImg, health, null);
    	Projectile lemon=new Projectile(0, 0, 10, lemonImg, 0, 0, 5, false, player);
    	player.setProjectile(lemon);
    	enemies=new ArrayList<Asteroid>();
    	enemies.add(new Asteroid(50,50,10,enemyImg,0,0,10,20));
    }
    
    //Paints everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.fillRect((int) (getWidth() * 7.5 / 9), 0, (getWidth()), getHeight());

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Score: " + score, (int) (getWidth() * 7.7 / 9), getHeight() / 9);
        g.drawString("High Score: " + highScore, (int) (getWidth() * 7.7 / 9), getHeight() * 2 / 9);
        g.drawString("Health: " + health, (int) (getWidth() * 7.7 / 9), getHeight() * 5 / 9);

        this.doVelocity();
        player.act();
        System.out.println("do it");
        player.draw(this, g);
        enemies.forEach((a)->a.draw(this,g));
    }
	
	private void doVelocity() {
		int moveSpeed=1;
		if(pressedUp) {
			if(player.getYVelocity()>=moveSpeed*-3) {
				player.addYVelocity(-moveSpeed);
			}
			
		}
		if(pressedLeft) {
			if(player.getXVelocity()>=moveSpeed*-3) {
				player.addXVelocity(-moveSpeed);
			}
			
		}
		if(pressedDown) {
			if(player.getYVelocity()<=moveSpeed*3) {
				player.addYVelocity(moveSpeed);
			}
			
		}
		if(pressedRight) {
			if(player.getXVelocity()<=moveSpeed*3) {
				player.addXVelocity(moveSpeed);
			}
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg) {
		if (arg.getKeyCode()==KeyEvent.VK_W) {
			pressedUp=true;
			System.out.println("Up");
		}
		else if (arg.getKeyCode()==KeyEvent.VK_A) {
			pressedLeft=true;
			System.out.println("Left");
		}
		else if (arg.getKeyCode()==KeyEvent.VK_S) {
			pressedDown=true;
			System.out.println("Down");
		}
		else if (arg.getKeyCode()==KeyEvent.VK_D) {
			pressedRight=true;
			System.out.println("Right");
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg) {
		if (arg.getKeyCode()==KeyEvent.VK_W) {
			pressedUp=false;
		}
		else if (arg.getKeyCode()==KeyEvent.VK_A) {
			pressedLeft=false;
		}
		else if (arg.getKeyCode()==KeyEvent.VK_S) {
			pressedDown=false;
		}
		else if (arg.getKeyCode()==KeyEvent.VK_D) {
			pressedRight=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
