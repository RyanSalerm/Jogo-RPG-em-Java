package project;

import java.util.Random;

public class AICharacter extends Character {

    public AICharacter(String name, int health, int attackPower, int defensePower, int magicPower, int magicDefense) {
        super(name, health, attackPower, defensePower, magicPower, magicDefense);
    }

    @Override
    public int attack(Character opponent, String attackType) {
        Random random = new Random();
        int attackValue;
        if ("magic".equalsIgnoreCase(attackType)) {
            attackValue = random.nextInt(getMagicPower());
            System.out.println(getName() + " (AI) uses a magical attack with power: " + attackValue);
        } else {
            attackType = "common";
            attackValue = random.nextInt(getAttackPower());
            System.out.println(getName() + " (AI) uses a common attack with power: " + attackValue);
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

        System.out.println(getName() + " (AI) defends with power: " + defenseValue);
        if (attackValue > defenseValue) {
            int damage = attackValue - defenseValue;
            setHealth(getHealth() - damage);
            System.out.println(getName() + " (AI) takes " + damage + " damage!");
        } else {
            System.out.println(getName() + " (AI) successfully defended the attack!");
        }
    }
}
