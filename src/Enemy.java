public class Enemy extends GameCharacter implements DamagableInterface {
    float hitDmg;
    float shotDmg;
    String name;

    public Enemy(int x, int y, int r,
                 float health,
                 float shotDmg, float hitDmg,
                 Projectile shotType, String name) {
        super(x, y, r, Enemy.class.getResource("/assets/lil.png"), health, shotType);
        this.hitDmg = hitDmg;
        this.shotDmg = shotDmg;
        this.name = name;
    }

    public boolean damageActor(Player player) {
        if (player.hurtActor(this, hitDmg)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public GameCharacter makeGameCharacter(int x, int y) {
        Enemy a = new Enemy(x, y, super.getRadius(), super.getHealth(), shotDmg, hitDmg, projectileType, "lil");
        return a;
    }

    public Enemy makeEnemy(int x, int y) {
        Enemy a = new Enemy(x, y, super.getRadius(), super.getHealth(), shotDmg, hitDmg, projectileType, "lil");
        return a;
    }
}
