package project;

import java.util.Random;

public class RandomUtils {
    public RandomUtils() {};
    private static final Random RANDOM = new Random();

    /**
     * Gera um número aleatório no intervalo definido.
     *
     * @param min Valor mínimo do intervalo (inclusivo).
     * @param max Valor máximo do intervalo (inclusivo).
     * @return Número inteiro aleatório entre min e max.
     */
    public static int randomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    /**
     * Escolhe aleatoriamente entre dois valores.
     *
     * @param option1 Primeira opção.
     * @param option2 Segunda opção.
     */

    // Método para decidir qual poder aplicar (AttackPower, DefensePower, MagicPower, MagicDefense)
    public static int decisaoComputador() {
        int choice = RANDOM.nextInt(4);  // Obtém uma escolha aleatória entre 0 e 3
        // Para garantir que sempre tenha um valor válido
        return switch (choice) {
            case 0 -> -20;  // AttackPower = -20
            case 1 -> 20;   // DefensePower = +20
            case 2 -> -40;  // MagicPower = -40
            case 3 -> 40;   // MagicDefense = +40
            default ->
                    throw new IllegalStateException("Unexpected value: " + choice);  // Para garantir que sempre tenha um valor válido
        };
    }
}
