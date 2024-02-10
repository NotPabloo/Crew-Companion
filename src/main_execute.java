/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file is where the program first starts to execute. The main funciton of this program is to allow 
 * the user to log into the program using a pre determined user name and password. When the program runs for 
 * the first time, a new folder named data is created. 
 * 
 * USERNAME is: 3735
 * PASSWORD IS: p@ssword2024
 */

// Importing src folder 
package src;

// Importing packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class that contains all variables 
public class main_execute implements ActionListener {
    JFrame frame;
    JPanel contentPane;
    JLabel userPrompt;
    JLabel passPrompt;
    JTextField userID;
    JPasswordField passwordText;
    JButton checkButton;
    JLabel resultLabel;

    String infoMessage = "Login failed. Incorrect password.";
    String titleBar = "Error";

    final int USERCODE = 3735;
    final String PASSCODE = "p@ssword2024";

    public main_execute() {
        // Find where the Java files are located
        String currentDirectory = System.getProperty("user.dir");

        // Specify the folder name
        String dataFolderPath = currentDirectory + "/data";

        // Call the folder creation method from the FolderCreator class
        store_names.createDataFolder(dataFolderPath);

        // Setting frame title
        frame = new JFrame("Crew Companion by Pablo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting the app icon
        // Calling method from program_icon.java file
        program_icon.setAppIcon(frame);

        // Set the starting window size
        frame.setSize(800, 600);

        // Setting layout to use GridBagLayout
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        userPrompt = new JLabel("Enter an Employee ID: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(userPrompt, gbc);

        userID = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(userID, gbc);

        passPrompt = new JLabel("Enter Password: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(passPrompt, gbc);

        passwordText = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(passwordText, gbc);

        checkButton = new JButton("Log In");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(checkButton, gbc);

        resultLabel = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPane.add(resultLabel, gbc);

        frame.getContentPane().add(contentPane, BorderLayout.CENTER);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        checkButton.addActionListener(this);
    }

    // Checking if the password is valid
    public void actionPerformed(ActionEvent event) {
        try {
            String enteredPassword = new String(passwordText.getPassword()); // Making a new string for the password
                                                                             // entered

            if (enteredPassword.equals(PASSCODE)) { // Checking if it matches the const declared at the top of the
                                                    // program
                resultLabel.setText("Login successful.");

                // Call the window_main method to open the main screen scene after login
                window_main.mainwindow();

                // Close the login frame
                frame.dispose();
            } else {
                // Show message box telling user incorrect password
                JOptionPane.showMessageDialog(null, infoMessage, titleBar,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            resultLabel.setText("An error occurred. Please try again.");
        }
    }

    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false); // Setting no window decorations
        new main_execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }
}
