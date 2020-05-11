import javax.swing.ImageIcon;

/**
 * A projectile class that moves and deals damage when overlapping another actor. Starts with an initial velocity.
 * @author Hangyul Yun, John Whitney
 * @version 05/03/2020
 */
public class Projectile extends Actor{

	private float damage;
	private boolean shouldDieOnHit;
	private Actor summoner;
	
	/**
	 * Constructor for a projectile
	 * @param x the starting x-location of the projectile
	 * @param y the starting y-location of the projectile
	 * @param r the radius of the projectile's hitbox.
	 * @param i the image to represent the projectile on screen
	 * @param xv the x-velocity of the projectile
	 * @param yv the y-velocity of the projectile
	 * @param dmg the amount of damage dealt by this projectile
	 * @param bShouldDieOnHit whether or not the projectile deletes itself from the game after colliding with another actor
	 * @param caster the character that created this projectile (null if the projectile spawned by itself into the game)
	 */
	public Projectile(int x, int y, int r, ImageIcon i, int xv, int yv, float dmg, boolean bShouldDieOnHit, Actor caster) {
		super(x, y, r, i, xv, yv);
		
		damage = dmg;
		summoner = caster;
		shouldDieOnHit = bShouldDieOnHit;
	}
	
	@Override
	public boolean isIntersecting(Actor other) {
		if(super.isIntersecting(other))
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean damageActor(Actor hurtActor)
	{
		if(hurtActor instanceof DamagableInterface)
		{
			if(hurtActor!=summoner) {
				boolean killedActor = ((DamagableInterface)hurtActor).hurtActor(this, damage);
				
				if(killedActor)
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public ClassLoader getClassLoader() {
		return this.getClassLoader();
	}

	/**
	 * Constructs a projectile
	 * @param x the starting x-location of the projectile
	 * @param y the starting y-location of the projectile
	 * @param r the radius of the projectile's hitbox.
	 * @param i the image to represent the projectile on screen
	 * @param xv the x-velocity of the projectile
	 * @param yv the y-velocity of the projectile
	 * @param dmg the amount of damage dealt by this projectile
	 * @param bShouldDieOnHit whether or not the projectile deletes itself from the game after colliding with another actor
	 * @param caster the character that created this projectile (null if the projectile spawned by itself into the game)
	 */
	public Projectile makeProjectile(int x, int y, int xv, int yv, Actor caster) {
		return new Projectile (x, y, super.getRadius(), super.getImageIcon(), xv, yv, damage, shouldDieOnHit, caster);
	}
	
	/**
	 * Returns this projectile's damage
	 * @return damage
	 */
	public float getDmg() {
		return damage;
	}

	public boolean fromPlayer() {
		if(summoner instanceof PlayerCharacter) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Actor getSummoner() {
		return summoner;
	}
}
