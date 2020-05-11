import javax.swing.ImageIcon;

public class Enemy extends GameCharacter implements DamagableInterface {
	float hitDmg;
	float shotDmg;
	String name;
	
	public Enemy(int x, int y, int r, 
			ImageIcon i, float health, 
			float shotDmg,float hitDmg, 
			Projectile shotType, String name) {
		super(x, y, r, i, health, shotType);
		this.hitDmg=hitDmg;
		this.shotDmg=shotDmg;
		this.name=name;
	}

	public boolean damageActor(PlayerCharacter player) {
		if(player.hurtActor(this, hitDmg)) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public GameCharacter makeGameCharacter(int x, int y) {
		Enemy a=new Enemy(x,y, super.getRadius(),super.getImageIcon(),super.getHealth(),shotDmg,hitDmg,projectileType,"lil");
		return a;
	}

	public Enemy makeEnemy(int x, int y) {
		Enemy a=new Enemy(x,y, super.getRadius(),super.getImageIcon(),super.getHealth(),shotDmg,hitDmg,projectileType,"lil");
		return a;
	}
}
