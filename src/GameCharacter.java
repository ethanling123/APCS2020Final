import java.lang.reflect.Constructor;

public class GameCharacter extends Actor implements DamagableInterface{
	
	private float health;
	private float overlapDmg;
	Class<Projectile> projectileType;

	public GameCharacter(int x, int y, int r, float h, float d) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
		
		health = h;
		overlapDmg = d;
	}

	@Override
	public boolean hurtActor(Actor CausingActor, float DamageQuantity) {
		
		health -= DamageQuantity;
		if(health <= 0.f)
		{
			return true;
		} else {
			return false;
		}
	}
	
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
	
	public void killedActor(Actor actorThatDied)
	{
	}
}
