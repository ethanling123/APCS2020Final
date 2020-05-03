
public interface DamagableInterface {
	/**
	 * Damage the actor
	 * @param CausingActor the actor that caused the damage
	 * @param DamageQuantity the amount the actor is damaged
	 * @return whether or not the actor has died
	 */
	public boolean hurtActor(Actor CausingActor, float DamageQuantity);
}
