
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;


public class SecondScreen extends Screen{

    private static final long serialVersionUID = 1L;

    // ==FIELDS==
    private static final float COOLDOWN_MAX = (float) 0.5;
    private static final float IFRAMECOUNT = 3;
    private static final float SPAWNCD = 1;
    private static int highScore;
    private int health = 100;

    private double iFramesStart, startTime, cdStart, spawnStart, seconds, frames;

    private boolean spawn, iFrames, cd, pressedUp, pressedDown, pressedLeft, pressedRight, pressedShoot;

    private URL lemonImg;
    private Player player;
    private Enemy lil;
    private ArrayList<Enemy> generalEnemies;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> proj;

    private DrawingSurface surface;

    public SecondScreen(DrawingSurface surface) {
        super(800, 600);
        startTime = System.nanoTime();
        lemonImg = Main.class.getResource("/assets/lemon.png");
        player = new Player(Main.DRAWING_WIDTH / 2, Main.DRAWING_HEIGHT / 2, 25, health, null);
        lil = new Enemy(0, 0, 25, 10, 5, 2, null, "lil enemy");
        lil.addYVelocity(3);
        Projectile lemon = new Projectile(0, 0, 10, lemonImg, 0, 0, 5, false, player);
        player.setProjectile(lemon);
        generalEnemies = new ArrayList<Enemy>();
        enemies = new ArrayList<Enemy>();
        proj = new ArrayList<Projectile>();
        generalEnemies.add(lil);
        this.surface = surface;

    }

    public void draw() {
        frames++;
        surface.pushStyle();
        surface.rect((int) (super.displayWidth * 7.5 / 9), 0, (super.displayWidth), super.displayHeight);
        surface.background(170);
        surface.stroke(100);
        surface.rect((int) (super.displayWidth * 7.5 / 9), 0, (super.displayWidth), super.displayHeight);
        surface.fill(1);
        surface.textSize(18);
        surface.text("Score: " + player.getPoints(), (int) (super.displayWidth * 7.7 / 9), super.displayHeight / 9);
        surface.text("High Score: " + highScore, (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 2 / 9);
        surface.text("Health: " + player.getHealth(), (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 5 / 9);
        surface.text("Frames: " + frames, (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 6 / 9);

        surface.popStyle();
        act();
        collide();

        this.player.draw(surface);
        enemies.forEach((a) -> a.draw(surface));
        proj.forEach((a) -> a.draw(surface));

        // Change stuff
        int moveSpeed = 3;
        if (surface.isPressed(KeyEvent.VK_W)) {
            if (player.getYVelocity() >= moveSpeed * -3) {
                player.addYVelocity(-moveSpeed);
            }
        } else {
            if (player.getYVelocity() < 0) {
                player.addYVelocity(+moveSpeed);
            }
        }

        if (surface.isPressed(KeyEvent.VK_A)) {
            if (player.getXVelocity() >= moveSpeed * -3) {
                player.addXVelocity(-moveSpeed);
            }
        } else {
            if (player.getXVelocity() < 0) {
                player.addXVelocity(+moveSpeed);
            }
        }

        if (surface.isPressed(KeyEvent.VK_S)) {
            if (player.getYVelocity() <= moveSpeed * 3) {
                player.addYVelocity(moveSpeed);
            }
        } else {
            if (player.getYVelocity() > 0) {
                player.addYVelocity(-moveSpeed);
            }
        }

        if (surface.isPressed(KeyEvent.VK_D)) {
            if (player.getXVelocity() <= moveSpeed * 3) {
                player.addXVelocity(moveSpeed);
            }
        } else {
            if (player.getXVelocity() > 0) {
                player.addXVelocity(-moveSpeed);
            }
        }
        if (surface.mousePressed) {
            if (!cd) {
                cd = true;
                this.cdStart = seconds;
                float xDir = surface.mouseX - player.getxCord();
                float yDir = surface.mouseY - player.getyCord();
                float directionMagnitude = (float) Math.sqrt((xDir * xDir) + (yDir * yDir));

                xDir /= directionMagnitude;
                yDir /= directionMagnitude;

                player.shootProjectile(proj, player, xDir, yDir);
            }
        }

        if (surface.isPressed(KeyEvent.VK_SPACE)) {
            surface.switchScreen(ScreenSwitcher.SCREEN1);
        }
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
        if (proj.equals(null)) {
            for (Projectile e : proj) {
                if (e.equals(null)) {
                }
                if (e.getyCord() - e.getRadius() > window.getHeight()) {
                    proj.remove(e);
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
        for (Projectile e : proj) {
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
        proj.forEach((a) -> a.act());
    }

    public void run(JFrame window) {
        while (true) {
            tick(window);
            surface.redraw();
        }
    }


}