
public class Projectile extends Actor{

	private float damage;
	private boolean shouldDieOnHit;
	private GameCharacter summoner;
	
	public Projectile(int x, int y, int r, float d, GameCharacter caster) {
		super(x, y, r);
		this.damage = d;
		summoner = caster;
	}
	
	@Override
	public boolean isIntersecting(Actor other) {
		if(super.isIntersecting(other))
		{
			damageActor(other);
			
			if(shouldDieOnHit)
			{
				//TODO: somehow delete self (remove self from global actor manager?)
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void damageActor(Actor hurtActor)
	{
		if(hurtActor instanceof DamagableInterface)
		{
			boolean killedActor = ((DamagableInterface)hurtActor).hurtActor(this, damage);
			
			if(killedActor && summoner != null)
			{
				summoner.killedActor(hurtActor);
			}
		}
	}
}
