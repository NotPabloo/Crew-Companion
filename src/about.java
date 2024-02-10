/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file contains the code for the about section of the application. 
 */

// Importing src folder 
package src;

// Importing the packages
import javax.swing.*;
import java.awt.*;

public class about extends JDialog {

    public about(JFrame parentFrame) {
        super(parentFrame, "About", true); // Setting the title of the box
        setSize(400, 200); // Setting the size 

        // Center the dialog on the parent frame
        setLocationRelativeTo(parentFrame);

        setResizable(false); // Dont allow the box to be resized

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Set layout to GridBagLayout for precise control
        setLayout(new GridBagLayout());

        // Load the profile picture
        ImageIcon pfp = new ImageIcon("media/unnamed.jpg");

        // Create a label for the profile picture
        JLabel pfpLabel = new JLabel();
        pfpLabel.setIcon(new ImageIcon(pfp.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));

        // Create a panel for the text content on the right side
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));

        // Add text content to the panel (example text, you can customize this)
        JLabel titleLabel = new JLabel("Crew Companion made by Pablo");
        JLabel descriptionLabel = new JLabel("Follow me on IG @notpabloo__");

        textPanel.add(titleLabel);
        textPanel.add(descriptionLabel);

        // Set constraints for the profile picture label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 10); // Adjust left margin

        // Add the profile picture label to the dialog
        add(pfpLabel, gbc);

        // Set constraints for the text panel
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;

        // Add the text panel to the dialog
        add(textPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage in a main method
        JFrame parentFrame = new JFrame();
        new about(parentFrame);
    }
}
