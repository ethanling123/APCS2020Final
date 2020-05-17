import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
    private static final float COOLDOWN_MAX = (float) 0.5;
    private static final float IFRAMECOUNT = 3;
    private static final float SPAWNCD = 1;
    private static int highScore;
    private int health = 100;

    private double iFramesStart, startTime, cdStart, spawnStart, seconds;

    private boolean spawn, iFrames, cd, pressedUp, pressedDown, pressedLeft, pressedRight, pressedShoot;

    private URL lemonImg;
    private Player player;
    private Enemy lil;
    private ArrayList<Enemy> generalEnemies;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> screen;

    public Runner() {
        startTime = System.nanoTime();
        lemonImg = Main.class.getResource("/assets/lemon.png");
        player = new Player(Main.DRAWING_WIDTH / 2, Main.DRAWING_HEIGHT / 2, 25, health, null);
        lil = new Enemy(0, 0, 25, 10, 5, 2, null, "lil enemy");
        lil.addYVelocity(3);
        Projectile lemon = new Projectile(0, 0, 10, lemonImg, 0, 0, 5, false, player);
        player.setProjectile(lemon);
        generalEnemies = new ArrayList<Enemy>();
        enemies = new ArrayList<Enemy>();
        screen = new ArrayList<Projectile>();
        screen.add(new Asteroid(50, 50, 10, 0, 5, 10, 5));
        generalEnemies.add(lil);
    }

    //Paints everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.fillRect((int) (getWidth() * 7.5 / 9), 0, (getWidth()), getHeight());

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Score: " + player.getPoints(), (int) (getWidth() * 7.7 / 9), getHeight() / 9);
        g.drawString("High Score: " + highScore, (int) (getWidth() * 7.7 / 9), getHeight() * 2 / 9);
        g.drawString("Health: " + player.getHealth(), (int) (getWidth() * 7.7 / 9), getHeight() * 5 / 9);

        this.doAction();

        act();
        collide();

        player.draw(this, g);
        enemies.forEach((a) -> a.draw(this, g));
        screen.forEach((a) -> a.draw(this, g));
    }

    private void tick(JFrame window) {
        seconds = (System.nanoTime() - startTime) / 1000000000;
        if (seconds - iFramesStart >= IFRAMECOUNT) {
            iFrames = false;
        }
        if (seconds - cdStart >= COOLDOWN_MAX) {
            cd = false;
        }
        if (seconds - spawnStart >= SPAWNCD) {
            spawn = true;
        }
        if (!enemies.equals(null)) {
            for (Enemy e : enemies) {
                if (e.getxCord() - e.getRadius() > window.getWidth()) {
                    enemies.remove(e);
                }
            }
        }
        if (screen.equals(null)) {
            for (Projectile e : screen) {
                if (e.equals(null)) {
                    System.out.println("a fd");
                }
                if (e.getyCord() - e.getRadius() > window.getHeight()) {
                    screen.remove(e);
                }
            }
        }
        lil.setHealth((float) (seconds / 30 + 10));
        if (spawn) {
            spawn = false;
            this.spawnStart = seconds;
            spawn(window);
        }

    }

    private void spawn(JFrame window) {
        if (!(seconds < Math.sqrt(seconds))) {
            for (int f = (int) (Math.sqrt(seconds)); f > 0; f--) {
                enemies.add(lil.makeEnemy((int) (Math.random() * window.getWidth()), 0));
                enemies.get(enemies.size() - 1).addYVelocity(3);
            }
        }
    }

    private void collide() {
        for (Enemy e : enemies) {
            if (e.isIntersecting(player)) {
                if (!iFrames) {
                    this.iFramesStart = seconds;
                    iFrames = true;
                    player.setPoints(player.getPoints() - 1);
                    if (e.damageActor(player)) {
                        restartScreen("Killed by: " + e.getName());
                    }
                }
            }
        }
        for (Projectile e : screen) {
            if (e.fromPlayer()) {
                for (Enemy a : enemies) {
                    if (e.isIntersecting(a)) {
                        if (e.damageActor(a)) {
                            player.killedActor();
                            enemies.remove(a);
                        }
                    }
                }
            } else if (e.isIntersecting(player)) {
                if (!iFrames) {
                    iFramesStart = seconds;
                    iFrames = true;
                    player.setPoints(player.getPoints() - 1);
                    if (e.damageActor(player)) {
                        restartScreen("Killed by: an enemy bullet");
                    }
                }
            }
        }
    }

    private void restartScreen(String string) {
        System.out.println(string);

    }

    private void act() {
        player.act();
        enemies.forEach((a) -> a.act());
        screen.forEach((a) -> a.act());
    }

    private void doAction() {
        int moveSpeed = 1;
        if (pressedUp) {
            if (player.getYVelocity() >= moveSpeed * -3) {
                player.addYVelocity(-moveSpeed);
            }
        } else {
            if (player.getYVelocity() < 0) {
                player.addYVelocity(+moveSpeed);
            }
        }

        if (pressedLeft) {
            if (player.getXVelocity() >= moveSpeed * -3) {
                player.addXVelocity(-moveSpeed);
            }
        } else {
            if (player.getXVelocity() < 0) {
                player.addXVelocity(+moveSpeed);
            }
        }

        if (pressedDown) {
            if (player.getYVelocity() <= moveSpeed * 3) {
                player.addYVelocity(moveSpeed);
            }
        } else {
            if (player.getYVelocity() > 0) {
                player.addYVelocity(-moveSpeed);
            }
        }

        if (pressedRight) {
            if (player.getXVelocity() <= moveSpeed * 3) {
                player.addXVelocity(moveSpeed);
            }
        } else {
            if (player.getXVelocity() > 0) {
                player.addXVelocity(-moveSpeed);
            }
        }
        if (pressedShoot) {
            if (!cd) {
                cd = true;
                this.cdStart = seconds;
                player.shootProjectile(screen, player, 0, -10);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        float xDir = arg0.getX() - player.getxCord();
        float yDir = arg0.getY() - player.getyCord();
        
        float directionMagnitude = (float) Math.sqrt((xDir * xDir) + (yDir * yDir));
        xDir /= directionMagnitude;
        yDir /= directionMagnitude;
        
        player.shootProjectile(screen, player, xDir, yDir);
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
        if (arg.getKeyCode() == KeyEvent.VK_W) {
            pressedUp = true;
        }
        if (arg.getKeyCode() == KeyEvent.VK_A) {
            pressedLeft = true;
        }
        if (arg.getKeyCode() == KeyEvent.VK_S) {
            pressedDown = true;
        }
        if (arg.getKeyCode() == KeyEvent.VK_D) {
            pressedRight = true;
        }
        if (arg.getKeyCode() == KeyEvent.VK_SPACE) {
            pressedShoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg) {
        if (arg.getKeyCode() == KeyEvent.VK_W) {
            pressedUp = false;
        }
        if (arg.getKeyCode() == KeyEvent.VK_A) {
            pressedLeft = false;
        }
        if (arg.getKeyCode() == KeyEvent.VK_S) {
            pressedDown = false;
        }
        if (arg.getKeyCode() == KeyEvent.VK_D) {
            pressedRight = false;
        }
        if (arg.getKeyCode() == KeyEvent.VK_SPACE) {
            pressedShoot = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    public void run(JFrame window) {
        while (true) {
            tick(window);
            repaint();
        }
    }

}
