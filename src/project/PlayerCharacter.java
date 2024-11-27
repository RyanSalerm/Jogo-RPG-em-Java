package project;

import java.util.Random;

public class PlayerCharacter extends Character {

    public PlayerCharacter(String name, int health, int attackPower, int defensePower, int magicPower, int magicDefense) {
        super(name, health, attackPower, defensePower, magicPower, magicDefense);
    }

    @Override
    public int attack(Character opponent, String attackType) {
        Random random = new Random();
        int attackValue;
        if (attackType.equalsIgnoreCase("magic")) {
            attackValue = random.nextInt(getMagicPower());
            System.out.println(getName() + " uses a magical attack with power: " + attackValue);
        } else {
            attackValue = random.nextInt(getAttackPower());
            System.out.println(getName() + " uses a common attack with power: " + attackValue);
        }
        opponent.defend(attackValue, attackType);
        return attackValue;
    }

    @Override
    public void defend(int attackValue, String attackType) {
        int defenseValue;
        if ("magic".equalsIgnoreCase(attackType)) {
            defenseValue = new Random().nextInt(getMagicDefense());
        } else {
            defenseValue = new Random().nextInt(getDefensePower());
        }

        System.out.println(getName() + " defends with power: " + defenseValue);
        if (attackValue > defenseValue) {
            int damage = attackValue - defenseValue;
            setHealth(getHealth() - damage);
            System.out.println(getName() + " takes " + damage + " damage!");
        } else {
            System.out.println(getName() + " successfully defended the attack!");
        }
    }
}
