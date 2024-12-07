import javax.swing.*;

public class PlayOption extends JFrame {
    private JPanel Option;
    private JButton rollCallANumberButton;
    private JButton markANumberButton;
    private JButton bingoButton;
    private JButton exitButton;
    private Cards playerCard = new Cards();
    private Cards computerCard = new Cards();
    private boolean[] calledNumbers = new boolean[76];
    private RandomBingo randomBingo = new RandomBingo();

    public PlayOption() {
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Center the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(Option);

        // Initialize cards
        playerCard.Intialize();
        computerCard.Intialize();
        clearScreen();
        // Show initial cards in console
        System.out.println("Your Card:");
        playerCard.printCard();
        System.out.println("Computer's Card:");
        computerCard.printCard();

        // Roll/Call a Number Button
        rollCallANumberButton.addActionListener(e -> rollNumber());

        // Mark a Number Button
        markANumberButton.addActionListener(e -> markNumber());

        // Bingo Button
        bingoButton.addActionListener(e -> checkBingo());

        // Exit Button
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void rollNumber() {
        int calledNumber = randomBingo.getRandom();
        if (!calledNumbers[calledNumber]) {
            calledNumbers[calledNumber] = true;
            JOptionPane.showMessageDialog(null, "Number Called: " + calledNumber);

            // Mark for computer
            computerCard.markNumber(calledNumber);

            // Check if computer got Bingo
            if (computerCard.checkBingo()) {
                JOptionPane.showMessageDialog(this, "The computer got Bingo! You lost.");
                promptPlayAgain();
                return;
            }
            clearScreen();
            // Show updated cards
            System.out.println("Your Card:");
            playerCard.printCard();
            System.out.println("Computer's Card:");
            computerCard.printCard();
        } else {
            JOptionPane.showMessageDialog(this, "Number " + calledNumber + " was already called.");
        }
    }

    private void markNumber() {
        String input = JOptionPane.showInputDialog(this, "Enter a number to mark:");

        if (input == null || input.trim().isEmpty()) { // Check if input is empty or canceled
            return; // Exit the method
        }
        try {
            int numberToMark = Integer.parseInt(input);
            if (numberToMark < 1 || numberToMark > 75) {
                JOptionPane.showMessageDialog(this, "Invalid number! Enter a number between 1 and 75.");
            } else if (calledNumbers[numberToMark]) {
                if (playerCard.markNumber(numberToMark)) {
                    JOptionPane.showMessageDialog(null, "Number marked!");
                } else {
                    JOptionPane.showMessageDialog(null, "Number not found on your card.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid action! Number " + numberToMark + " has not been called.");
            }
            clearScreen();
            // Show updated cards
            System.out.println("Your Card:");
            playerCard.printCard();
            System.out.println("Computer's Card:");
            computerCard.printCard();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void checkBingo() {
        if (playerCard.checkBingo()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You got Bingo!");
            promptPlayAgain();
        } else {
            JOptionPane.showMessageDialog(this, "No Bingo yet! Keep playing.");
        }
    }

    private void promptPlayAgain() {
        int response = JOptionPane.showOptionDialog(this,
                "Would you like to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "Play Again", "Quit" },
                "Play Again");

        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }



    private void resetGame() {
        playerCard = new Cards();
        computerCard = new Cards();
        playerCard.Intialize();
        computerCard.Intialize();
        calledNumbers = new boolean[76];

        clearScreen();
        System.out.println("New Game:");
        System.out.println("Your Card:");
        playerCard.printCard();
        System.out.println("Computer's Card:");
        computerCard.printCard();
    }


    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Error clearning the screen "+ e.getMessage());
        }
    }
}
