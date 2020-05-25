import java.net.URL;
import java.util.ArrayList;

/**
 * A game character that deals damage when overlapped, attacks, and can be attacked.
 *
 * @author Hangyul Yun
 */
public class GameCharacter extends Actor implements DamagableInterface {

    private float health;
    Projectile projectileType;

    /**
     * Constructor for a game character
     *
     * @param x        the x-starting position of the character
     * @param y        the y-starting position of the character
     * @param r        the radius of the character's hitbox.
     * @param i        the image to represent the character on screen
     * @param health   the health of the character
     * @param shotType the type of projectile the character shoots.
     */
    public GameCharacter(int x, int y, int r, String i, float health, Projectile shotType) {
        super(x, y, r, i);

        this.health = health;
        projectileType = shotType;
    }

    @Override
    public boolean hurtActor(Actor causingActor, float damageQuantity) {

        health -= damageQuantity;
        if (health <= 0.f) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Shoot projectiles and add them to the screen.
     *
     * @param screen
     */
    public void shootProjectile(ArrayList<Projectile> screen, Actor shooter, float xv, float yv) {
        try {
            if (projectileType != null) {
                screen.add(projectileType.makeProjectile(shooter.getxCord(), shooter.getyCord(), xv, yv, shooter));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the current projectile type.
     *
     * @param
     */
    public void setProjectile(Projectile shotType) {
        this.projectileType = shotType;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float hp) {
        health = hp;
    }

    /**
     * Constructor for a game character
     *
     * @param x        the x-starting position of the character
     * @param y        the y-starting position of the character
     * @param r        the radius of the character's hitbox.
     * @param i        the image to represent the character on screen
     * @param health   the health of the character
     * @param shotType the type of projectile the character shoots.
     */
//    public GameCharacter makeGameCharacter(int x, int y) {
//        return new GameCharacter(x, y, super.getRadius(), health, projectileType);
//    }
}
