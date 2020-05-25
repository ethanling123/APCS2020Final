

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class SecondScreen extends Screen {

    private static final long serialVersionUID = 1L;

    // ==FIELDS==
    private static final float COOLDOWN_MAX = 0.5f;
    private static final float IFRAMECOUNT = 3;
    private static final float SPAWNCD = 1;
    private int health = 100;

    private double iFramesStart, startTime, cdStart, spawnStart, seconds, frames;

    private boolean spawn, iFrames, cd, pressedUp, pressedDown, pressedLeft, pressedRight, pressedShoot;

    private String lemonImg;
    private Player player;
    private Enemy lil;

    private ArrayList<Actor> allActors;
    private ArrayList<Enemy> generalEnemies;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectileList;

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
        projectileList = new ArrayList<Projectile>();
        generalEnemies.add(lil);
        this.surface = surface;
    }

    public void draw() {
            frames++;
            surface.pushStyle();
            surface.background(170);
            surface.fill(1);
            surface.textSize(18);
            surface.text("Score: " + player.getPoints(), (int) (super.displayWidth * 7.7 / 9), super.displayHeight / 9);
            surface.text("Health: " + player.getHealth(), (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 4 / 9);
            surface.text("Frames: " + frames, (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 5 / 9);
            surface.text("Enemies: " + enemies.size(), (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 6 / 9);
            surface.text("Projectiles: " + projectileList.size(), (int) (super.displayWidth * 7.7 / 9), super.displayHeight * 7 / 9);
            surface.popStyle();

            act();
            collide();
            spawn();

            this.player.draw(surface);
            enemies.forEach((a) -> a.draw(surface));
            projectileList.forEach((a) -> a.draw(surface));

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

                    if (frames > 10)
                        player.shootProjectile(projectileList, player, 10 * xDir, 10 * yDir);
                }
            }

            if (surface.isPressed(KeyEvent.VK_SPACE)) {
                surface.switchScreen(ScreenSwitcher.SCREEN1);
            }
        if(player.getHealth() <= 0) {
            surface.pushStyle();
            surface.rect(0, 0, super.displayWidth, super.displayHeight);
            surface.textSize(30);
            surface.fill(0);
            surface.text("Game Over \n Press esc to exit", super.displayWidth/2 - 50, super.displayHeight/2 - 10);
            surface.popStyle();
        }
    }

    private void spawn() {
        if ((frames % 50 == 0)) {
            enemies.add(lil.makeEnemy((int) (Math.random() * this.displayWidth), 0));
            enemies.get(enemies.size() - 1).addYVelocity(3);
        }
    }


    private void collide() {
        for (int e = 0; e < enemies.size(); e++) {
            if (enemies.get(e).isIntersecting(player)) {
                if (!iFrames) {
                    this.iFramesStart = seconds;
                    iFrames = true;
                    if (enemies.get(e).damageActor(player)) {
                        restartScreen("Killed by: " + enemies.get(e).getName());
                    }
                }
            }
            if (enemies.get(e).getyCord() > super.displayHeight) {
                enemies.remove(e);
                player.setPoints(player.getPoints() - 10);
            }
        }
        for (int p = 0; p < projectileList.size(); p++) {
            if (projectileList.get(p).fromPlayer()) {
                for (int e = 0; e < enemies.size(); e++) {
                    if (projectileList.get(p).isIntersecting(enemies.get(e))) {
                        if (projectileList.get(p).damageActor(enemies.get(e))) {
                            player.killedActor();
                            enemies.remove(e);
                            projectileList.remove(p);
                            player.setPoints(player.getPoints() + 20);
                        }

                    }
                }


            } else if (projectileList.get(p).isIntersecting(player)) {
                if (!iFrames) {
                    iFramesStart = seconds;
                    iFrames = true;
                    player.setPoints(player.getPoints() - 1);
                    if (projectileList.get(p).damageActor(player))
                        restartScreen("Killed by: an enemy bullet");
                }
            } if (projectileList.get(p).getxCord() > super.displayWidth || projectileList.get(p).getxCord() < 0 ||
                    projectileList.get(p).getyCord() > super.displayHeight || projectileList.get(p).getyCord() < 0) {
                projectileList.remove(p);
            }
        }
    }

    private void restartScreen(String string) {
        System.out.println(string);
    }

    private void act() {
        player.act();
        enemies.forEach((a) -> a.act());
        projectileList.forEach((a) -> a.act());
        if(player.getxCord() > super.displayWidth || player.getxCord() < 0 ||
                player.getyCord() > super.displayHeight || player.getyCord() < 0)
            player.setHealth(player.getHealth() -1);
    }
}