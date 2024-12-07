import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class GameMode extends JFrame {
    private JButton playerVSComputerButton;
    private JButton playerVSPlayerButton;
    private JPanel ModePanel;

    GameMode() {
        // Set up the frame properties
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);  // Center the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Set the content pane to the ModePanel
        this.setContentPane(ModePanel);


        // Initialize the buttons and add listeners
        playerVSComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Action for player vs computer
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(ModePanel);
                mainFrame.dispose();

                SwingUtilities.invokeLater(() -> playPlayerVsComputer());
            }
        });

        playerVSPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(ModePanel);
                mainFrame.dispose();

                SwingUtilities.invokeLater(() -> playMultiplayer());
            }
        });

        // Pack the frame to fit the content
        this.pack();
    }

    public static void playPlayerVsComputer() {
        // Replace Scanner-based input with GUI actions
        SwingUtilities.invokeLater(() -> {
            PlayOption playerVsComp = new PlayOption();
            playerVsComp.setVisible(true);
        });
    }

    public static void playMultiplayer() {
        SwingUtilities.invokeLater(() -> {
            PlayerVsPlayer PVP = new PlayerVsPlayer();
            PVP.setVisible(true);
        });
    }



}