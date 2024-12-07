import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomBingo {
    private Set<Integer> rolledNumbers = new HashSet<>(); // To store rolled numbers

    public int getRandom() {
        BINGO bingo = getBingo();
        Random random = new Random();
        int number = 0;

        // Roll a unique number for each letter
        if (bingo == BINGO.B) {
            JOptionPane.showMessageDialog(null, " Letter B");
            number = getUniqueRandom(1, 15);
        } else if (bingo == BINGO.I) {
            JOptionPane.showMessageDialog(null, " Letter I");
            number = getUniqueRandom(16, 30);
        } else if (bingo == BINGO.N) {
            JOptionPane.showMessageDialog(null, " Letter N");
            number = getUniqueRandom(31, 45);
        } else if (bingo == BINGO.G) {
            JOptionPane.showMessageDialog(null, " Letter G");
            number = getUniqueRandom(46, 60);
        } else if (bingo == BINGO.O) {
            JOptionPane.showMessageDialog(null, " Letter O");
            number = getUniqueRandom(61, 75);
        }
        return number;
    }

    public int getRandom(BINGO bingo) {
        Random random = new Random();
        int number = 0;

        // Roll a unique number for each letter
        if (bingo == BINGO.B) {
            number = getUniqueRandom(1, 15);
        } else if (bingo == BINGO.I) {
            number = getUniqueRandom(16, 30);
        } else if (bingo == BINGO.N) {
            number = getUniqueRandom(31, 45);
        } else if (bingo == BINGO.G) {
            number = getUniqueRandom(46, 60);
        } else if (bingo == BINGO.O) {
            number = getUniqueRandom(61, 75);
        }
        return number;
    }

    // Helper method to ensure the number is unique
    private int getUniqueRandom(int lowerBound, int upperBound) {
        Random random = new Random();
        int number;
        do {
            number = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        } while (rolledNumbers.contains(number)); // If the number is already rolled, try again

        rolledNumbers.add(number); // Mark the number as rolled
        return number;
    }

    public static BINGO getBingo() {
        Random rand = new Random();
        BINGO[] bingos = BINGO.values();
        return bingos[rand.nextInt(bingos.length)];
    }
}
