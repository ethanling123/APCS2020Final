

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class SecondScreen extends Screen {

    private static final long serialVersionUID = 1L;

    // ==FIELDS==
    private static final float COOLDOWN_MAX = 0.5f;
    private static final float IFRAMECOUNT = 3;
    private static final float SPAWNCD = 1;
    private static int highScore;
    private int health = 15;

    private double iFramesStart, startTime, cdStart, spawnStart, seconds, frames;

    private boolean spawn, iFrames, cd, pressedUp, pressedDown, pressedLeft, pressedRight, pressedShoot;

    private String lemonImg;
    private Player player;
    private Enemy lil;

    private ArrayList<Actor> allActors;
    private ArrayList<Enemy> generalEnemies;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> proj;

    private DrawingSurface surface;

    public SecondScreen(DrawingSurface surface) {
        super(800, 600);
        startTime = System.nanoTime();
        lemonImg = "assets/lemon.png";
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

        System.out.println(enemies.size());
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
        spawn();

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
                //cd = true;
                //this.cdStart = seconds;
                float xDir = surface.mouseX - player.getxCord();
                float yDir = surface.mouseY - player.getyCord();
                float directionMagnitude = (float) Math.sqrt((xDir * xDir) + (yDir * yDir));

                xDir /= directionMagnitude;
                yDir /= directionMagnitude;

                System.out.println(xDir + ", " + yDir);

                if(frames > 10)
                    player.shootProjectile(proj, player, xDir, yDir);
            }
        }

        if (surface.isPressed(KeyEvent.VK_SPACE)) {
            surface.switchScreen(ScreenSwitcher.SCREEN1);
        }
    }

    private void spawn() {
        if ((frames % 20 == 0)) {
                enemies.add(lil.makeEnemy((int) (Math.random() * this.displayWidth), 0));
                enemies.get(enemies.size() - 1).addYVelocity(3);
        }
    }


    private void collide() {
        for (int e = 0; e < enemies.size(); e ++) {
            if (enemies.get(e).isIntersecting(player)) {
                if (!iFrames) {
                    this.iFramesStart = seconds;
                    iFrames = true;
                    player.setPoints(player.getPoints() - 1);
                    if (enemies.get(e).damageActor(player)) {
                        restartScreen("Killed by: " + enemies.get(e).getName());
                    }
                }
            }
        }
        for (int p = 0; p < proj.size(); p ++) {
            if (proj.get(p).fromPlayer()) {
                for (int e = 0; e < enemies.size(); e ++) {
                    if (proj.get(p).isIntersecting(enemies.get(e))) {
                        if (proj.get(p).damageActor(enemies.get(e))) {
                            player.killedActor();
                            enemies.remove(enemies.get(e));
                        }
                    }
                }
            } else if (proj.get(p).isIntersecting(player)) {
                if (!iFrames) {
                    iFramesStart = seconds;
                    iFrames = true;
                    player.setPoints(player.getPoints() - 1);
                    if (proj.get(p).damageActor(player))
                        restartScreen("Killed by: an enemy bullet");
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

        for(int x = 0; x < enemies.size(); x++) {
            if (player.getyCord() < enemies.get(x).getxCord())
                    player.setPoints(player.getPoints() - 1);
        }
    }

    /*
    public void run(JFrame window) {
        while (true) {
            tick(window);
            surface.redraw();
        }
    }
    */


    /*
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
    */


}