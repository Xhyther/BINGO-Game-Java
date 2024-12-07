import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JLabel Welcome;
    private JButton startButton;
    private JButton exitButton;
    private JPanel WelcomePanel;

    public Main() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Exiting.....");
                System.exit(0);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(WelcomePanel);
                mainFrame.dispose();  // Dispose of the main form

                // Open the second form
                GameMode gameMode = new GameMode();
                gameMode.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Start Menu");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setContentPane(new Main().WelcomePanel);
        frame.pack();
        frame.setVisible(true);
    }
}