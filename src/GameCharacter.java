import java.lang.reflect.Constructor;

import javax.swing.ImageIcon;

/**
 * A game character that deals damage when overlapped, attacks, and can be attacked.
 * @author Hangyul Yun
 *
 */
public class GameCharacter extends Actor implements DamagableInterface{
	
	private float health;
	private float overlapDmg;
	Class<Projectile> projectileType;
	
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
	public GameCharacter(int x, int y, int r, ImageIcon i, float health, float dmg, Class<Projectile> shotType) {
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
	 */
	public void shootProjectile() {
		try
		{
			if(projectileType != null)
			{
				Constructor<Projectile> projConstructor = projectileType.getConstructor();
				Projectile spawnedProjectile = projConstructor.newInstance(new Object[] {});
				//^^ This is a bit sus.
				//TODO: Add projectile to global actor manager
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Register the event of killing another actor. Used for adding points or a death message.
	 * @param actorThatDied the actor that has been killed by this actor.
	 */
	public void killedActor(Actor actorThatDied)
	{
	}
	
	/**
	 * Sets the current projectile type.
	 * @param 
	 */
	public void setProjectile(Class<Projectile> shotType)
	{
		this.projectileType=shotType;
	}
}
