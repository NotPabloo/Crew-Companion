/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file contains the code that handles the formatting and exporting of the table data into 
 * a txt file.
 */

// Importing src folder 
package src;

// Importing packages 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class convert {

    public static void convertToTxt(String folderPath, Object[][] data) {
        File file = new File(folderPath, "exported_data.txt"); // Making file variable

        try (FileWriter writer = new FileWriter(file)) {
            // Column headers
            writer.write("Name\tHours Worked\tTotal Amount\n");
            writer.write("--------------------------------\n");

            for (Object[] row : data) {
                for (Object cell : row) {
                    writer.write(cell.toString() + "\t");
                }
                writer.write("\n");
                writer.write("--------------------------------\n");
            }
            // Message pane to alert user if the task was successful
            JOptionPane.showMessageDialog(null, "Data exported to TXT successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            // Message pane to alert user if the task was not successful
            JOptionPane.showMessageDialog(null, "Error exporting data to TXT: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
