/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file contains the code to make a folder 
 * in the program directory for the exported txt file. 
 */

// Importing src folder 
package src;

// Importing packages
import java.io.File;

public class store_names {

    public static void createDataFolder(String folderPath) {
        File dataFolder = new File(folderPath); 

        if (!dataFolder.exists()) {
            if (dataFolder.mkdirs()) {
                System.out.println("Data folder created successfully!");
            } else {
                System.err.println("Failed to create data folder!");
            }
        }
    }
}

