package ca.viaware.rpg.mapeditor;

public class Enemy {
	private EnemyType Type;
	private int Level;

	public Enemy(EnemyType enemy, int level) {
		Type = enemy;
		Level = level;
	}

	public EnemyType getType() {
		return Type;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public void setType(EnemyType type) {
		Type = type;
	}
}
