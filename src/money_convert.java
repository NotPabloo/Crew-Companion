/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file contains the code that handles the calculation
 * of the data so that it can return how much money a person made
 */

// Importing src folder
package src;

// Importing packages
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class money_convert {
    public static final double RATE_PER_HOUR = 16.50; // The rate per hour is set at $16.50 an hour
    public static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    public static final DecimalFormat hoursFormat = new DecimalFormat("#.##");

    public static String formatCurrency(double amount) {
        return currencyFormat.format(amount);
    }

    public static String formatHours(double hours) {
        return hoursFormat.format(hours);
    }

    public static double calculateTotalAmount(double hoursWorked) {
        double totalAmount = hoursWorked * RATE_PER_HOUR;
        // Return the totalAmount as a double
        return Double.parseDouble(hoursFormat.format(totalAmount));
    }
}
