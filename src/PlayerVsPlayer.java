import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PlayerVsPlayer extends JFrame {

    private JPanel Option;
    private JButton rollCallANumberButton;
    private JButton markANumberButton;
    private JButton bingoButton;
    private JButton exitButton;
    private JButton skitp;
    private Cards[] players;
    private boolean[] calledNumbers;
    private RandomBingo randomBingo;
    private int currentPlayer;
    private int calledNumber;

    public PlayerVsPlayer() {
        // Initialize JFrame properties
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Center the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(Option);

        // Game setup

        initializeGame();

        // Button actions
        rollCallANumberButton.addActionListener(e -> rollNumber());
        markANumberButton.addActionListener(e -> markNumber());
        bingoButton.addActionListener(e -> checkBingo());
        exitButton.addActionListener(e -> System.exit(0));
        skitp.addActionListener( e-> skipTurn());
    }

    private void initializeGame() {
        // Reset game variables
        clearScreen();

        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the number of players:"));
        players = new Cards[numPlayers];
        calledNumbers = new boolean[76];
        randomBingo = new RandomBingo();
        currentPlayer = 0;
        calledNumber = -1;

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Cards();
            players[i].Intialize();
        }
        Option.setBorder(BorderFactory.createTitledBorder("Player " + (1)));

        // Display cards for all players
        for (int i = 0; i < numPlayers; i++) {
            JOptionPane.showMessageDialog(this, "Player " + (i + 1) + "'s Card:\n");
            System.out.println("Player " + (i + 1) + "'s Card:\n");
            players[i].printCard();
        }
    }

    private void rollNumber() {
        if (calledNumber == -1 || calledNumbers[calledNumber]) {
            do {
                calledNumber = randomBingo.getRandom();
            } while (calledNumbers[calledNumber]);
            calledNumbers[calledNumber] = true;
        }

        JOptionPane.showMessageDialog(this, "Number Rolled: " + calledNumber + ". All players can mark this number.");
    }


    private void markNumber() {
        if (calledNumber == -1) {
            JOptionPane.showMessageDialog(this, "No number has been rolled yet! Please roll a number first.");
            return;
        }

        boolean validInput = false;
        int numberToMark = -1;

        while (!validInput) {
            String input = JOptionPane.showInputDialog(this, "Player " + (currentPlayer + 1) + ", enter a number to mark:");

            if (input == null || input.trim().isEmpty()) { // Check if input is empty or canceled
                return; // Exit the method
            }

            try {
                numberToMark = Integer.parseInt(input);

                if (numberToMark < 1 || numberToMark > 75) {
                    JOptionPane.showMessageDialog(this, "Invalid number! Enter a number between 1 and 75.");
                } else if (!calledNumbers[numberToMark]) {
                    JOptionPane.showMessageDialog(this, "Invalid action! Number " + numberToMark + " has not been called.");
                } else {
                    validInput = true; // Input is valid if it passes all checks
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }

        // Mark the number if valid
        if (players[currentPlayer].markNumber(numberToMark)) {
            JOptionPane.showMessageDialog(this, "Number marked on Player " + (currentPlayer + 1) + "'s card!");
        } else {
            JOptionPane.showMessageDialog(this, "Number not found on your card.");
        }

        nextTurn();
    }




    private void checkBingo() {
        if (players[currentPlayer].checkBingo()) {
            JOptionPane.showMessageDialog(this, "Congratulations, Player " + (currentPlayer + 1) + "! You got Bingo!");
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                initializeGame();
            } else {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Bingo yet for Player " + (currentPlayer + 1) + ". Keep playing!");
        }

        nextTurn();
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer + 1) % players.length;
        JOptionPane.showMessageDialog(this, "Now it's Player " + (currentPlayer + 1) + "'s turn!");
        Option.setBorder(BorderFactory.createTitledBorder("Player " + (currentPlayer + 1)));
        clearScreen();
        System.out.println("Player " + (currentPlayer + 1) + "'s Card:\n");
        players[currentPlayer].printCard();
    }
    private void skipTurn() {
        JOptionPane.showMessageDialog(this, "Player " + (currentPlayer + 1) + " has skipped their turn.");
        nextTurn();
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerVsPlayer game = new PlayerVsPlayer();
            game.setVisible(true);
        });
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
    }}


