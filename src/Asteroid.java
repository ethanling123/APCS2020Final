import java.net.URL;
import javax.swing.ImageIcon;

/**
 * An asteroid that travels across the map and deals impact damage to whatever it collides with
 * 
 * Authors: Ethan Ling, Hangyul Yun
 * Period: 6
 * Date: 5/03/2020
 * MonkeyDefence Capstone Project
 * Class: Projectile
 */

public class Asteroid extends Projectile implements DamagableInterface {

	// == FIELD ==
    public float health;

    // == CONSTRUCTOR ==
    
    public Asteroid(int x, int y, int r, int xv, int yv, float dmg, float health) {
		super(x, y, r, Asteroid.class.getResource("/assets/norm.png"), xv, yv, dmg, true, null);
		
		this.health = health;
	}

	@Override
	public boolean hurtActor(Actor causingActor, float damageQuantity) {
		health -= damageQuantity;
		if(health < 0.f)
		{
			//TODO: Destroy actor
		}
		return false;
	}
    
	
	
	/*@Ethan: I commented out most of the methods below since the actor superclass and the DamagableInterface method handles much of what
	 * I think the asteroids should behave like.*/
	
	
    /*
    //Abstract chase method
    public abstract void chase(Player target);

    //Get and Set for Health
    public double getHealth() {
        return health;
    }

    public void loseHealth(int lostHealth) {
        this.health = health - lostHealth;
    }

    //Moves the enemy
    public void update() {
        moveByAmount((int) xMovement, (int) yMovement);
    }
    */
}