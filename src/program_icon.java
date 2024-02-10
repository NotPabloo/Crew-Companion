/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file contains the code to set an icon for the application. 
 */

// Importing src folder 
package src;

//Importing packages 
import javax.swing.*;

// Setting the path where the png is
public class program_icon {
    public static final ImageIcon appIcon = new ImageIcon(program_icon.class.getResource("/media/app_icon.png"));

    public static void setAppIcon(JFrame frame) {
        frame.setIconImage(appIcon.getImage()); // Setting the app icon
    }
}
