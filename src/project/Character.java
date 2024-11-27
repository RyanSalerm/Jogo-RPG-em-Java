package project;

public abstract class Character {
    private final String name;
    private int health;
    private final int attackPower;
    private final int defensePower;
    private final int magicPower;
    private final int magicDefense;

    public Character(String name, int health, int attackPower, int defensePower, int magicPower, int magicDefense) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.magicPower = magicPower;
        this.magicDefense = magicDefense;
    }

    // Métodos getters e setters
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public String getName() { return name; }
    public int getMagicPower() { return magicPower; }
    public int getMagicDefense() { return magicDefense; }
    public int getAttackPower() { return attackPower; }
    public int getDefensePower() { return defensePower; }
    // Métodos abstratos
    public abstract int attack(Character opponent, String attackType);
    public abstract void defend(int attackValue, String attackType);
}
