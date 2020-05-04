/**
 * An interface for any actor that can be damaged. Characters, asteroids, and rockets are examples.
 * @author Hangyul Yun
 *
 */
public interface DamagableInterface {
	/**
	 * Damage the actor
	 * @param CausingActor the actor that caused the damage
	 * @param DamageQuantity the amount the actor is damaged
	 * @return whether or not the actor has died
	 */
	public abstract boolean hurtActor(Actor causingActor, float damageQuantity);
}
