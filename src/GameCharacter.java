import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * A game character that deals damage when overlapped, attacks, and can be attacked.
 * @author Hangyul Yun
 *
 */
public class GameCharacter extends Actor implements DamagableInterface{
	
	private float health;
	private float overlapDmg;
	Projectile projectileType;
	
	/**
	 * Constructor for a game character
	 * @param x the x-starting position of the character
	 * @param y the y-starting position of the character
	 * @param r the radius of the character's hitbox.
	 * @param i the image to represent the character on screen
	 * @param health the health of the character
	 * @param dmg the amount of damage dealt by the character
	 * @param shotType the type of projectile the character shoots.
	 */
	public GameCharacter(int x, int y, int r, ImageIcon i, float health, float dmg, Projectile shotType) {
		super(x, y, r, i);
		
		this.health = health;
		overlapDmg = dmg;
		projectileType = shotType;
	}

	@Override
	public boolean hurtActor(Actor causingActor, float damageQuantity) {
		
		health -= damageQuantity;
		if(health <= 0.f)
		{
			if(causingActor instanceof PlayerCharacter)
			{
				PlayerCharacter test = (PlayerCharacter)causingActor;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Shoot projectiles and add them to the screen.
	 * @param screen 
	 */
	public void shootProjectile(ArrayList<Projectile> screen,Actor shooter,int xv,int yv) {
		try
		{
			if(projectileType != null)
			{
				Projectile spawnedProjectile = projectileType.makeProjectile(shooter.getxCord(), 
						shooter.getyCord(),xv, yv, shooter);
				screen.add(spawnedProjectile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the current projectile type.
	 * @param 
	 */
	public void setProjectile(Projectile shotType)
	{
		this.projectileType=shotType;
	}

	public float getHealth() {
		return health;
	}
}
