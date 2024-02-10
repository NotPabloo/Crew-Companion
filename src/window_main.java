/*
 * Programmer: Peter Plastina
 * Date Created: 01/15/24
 * Date Modified: 01/21/24
 * Version 1.0
 * 
 * This file contains everything for the main GUI. This includes the top menu bar, buttons, and grid.
 * The GUI is made using the GBC GridBagLayout. Many functions in the file call on other java files in
 * the src folder to avoid clutter.
 */

// Importing src folder 
package src;

// Importing packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Class that contains all variables
public class window_main {
    public static DefaultTableModel tableModel;
    public static JTable table;
    public static boolean editMode = false; // Flag to indicate edit mode
    public static JFrame sceneFrame;

    // Class that contains the main window and the menu bar
    public static void mainwindow() {
        sceneFrame = new JFrame("Crew Companion by Pablo"); // Setting the window title
        sceneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sceneFrame.setSize(800, 600); // Setting window size

        // Setting the app icon
        // Calling method from program_icon.java file
        program_icon.setAppIcon(sceneFrame);

        // Setting layout to use GridBagLayout
        JPanel scenePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Label Text
        JLabel wageInfoLabel = new JLabel("<html><font size=2>*Wage is calculated at $16.50 per hour</font></html>");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST; // Set it to the left side
        gbc.insets = new Insets(10, 10, 10, 10); // Add some top padding
        scenePanel.add(wageInfoLabel, gbc); // Add the text

        // Creating MenuBar
        JMenuBar menubar = new JMenuBar();
        sceneFrame.setJMenuBar(menubar);

        // Adding options to the menu bar
        JMenu file = new JMenu("File"); // This is what shows up at the top
        menubar.add(file);
        JMenuItem exit = new JMenuItem("Exit"); // This is what appears on click
        file.add(exit);

        JMenu cell_editor = new JMenu("Edit");
        menubar.add(cell_editor);
        JMenuItem edit = new JMenuItem("Edit Mode");
        cell_editor.add(edit);

        JMenu convert = new JMenu("Export");
        menubar.add(convert);

        JMenu exportSubMenu = new JMenu("Export Options");
        convert.add(exportSubMenu);
        JMenuItem exportToTXT = new JMenuItem("Export to TXT"); // Submenu item. When mouse hovers multiple options
                                                                // appears
        exportSubMenu.add(exportToTXT);

        JMenu account = new JMenu("Account");
        menubar.add(account);
        JMenuItem userAccount = new JMenuItem("Log Out");
        account.add(userAccount);

        JMenu help = new JMenu("Help");
        menubar.add(help);
        JMenuItem manual = new JMenuItem("Documentation");
        help.add(manual);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        // When the exit button is pressed insdie the file menu the program shuts down
        class ExitListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        exit.addActionListener(new ExitListener()); // Adding the action listener

        // When the about button is pressed a new window appears with information about
        // the program
        // Calls the about.java file
        class AboutListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                new about(sceneFrame);
            }
        }
        about.addActionListener(new AboutListener());

        // When the log out button is pressed the main window closes and the login
        // screen appears
        class LogOutListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                sceneFrame.dispose();
                new main_execute();
            }
        }
        userAccount.addActionListener(new LogOutListener());

        // When the edit button is clicked the cells in the table become editable
        // The title of the program window changes to reflect the mode
        class EditListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                editMode = !editMode; // Toggle edit mode
                setCellEditors(); // Set cell editors accordingly
                updateFrameTitle(); // Update frame title to indicate edit mode
            }
        }
        edit.addActionListener(new EditListener());

        // When the documentation button is clicked, a pdf file will be opened
        // in either the users web browser or dedicated pdf viewer application
        class ManualListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String documentationPath = "docs/Crew_Companion_Manual.pdf"; // Replace with your actual file path

                File documentationFile = new File(documentationPath);

                try {
                    Desktop.getDesktop().open(documentationFile);
                } catch (Exception ex) {
                    System.err.println("Error opening documentation: " + ex.getMessage());
                    JOptionPane.showMessageDialog(sceneFrame, "Error opening documentation\n            File not found",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        manual.addActionListener(new ManualListener());

        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints controlPanelGbc = new GridBagConstraints();
        controlPanelGbc.insets = new Insets(5, 5, 5, 5);

        // Placing the add & delete user buttons
        JTextField nameField = new JTextField(15);
        JTextField hoursField = new JTextField(15);

        // Code that sets the info inuputted into the chart
        // Also checks to make sure data is inputted into the box before adding
        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addUser(nameField.getText(), hoursField.getText())) {
                    JOptionPane.showMessageDialog(sceneFrame, "Employee created successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    nameField.setText("");
                    hoursField.setText("");
                } else {
                    JOptionPane.showMessageDialog(sceneFrame, "Failed to create employee. Please check the input.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Code that deletes a row of data from the table
        // Also checks to make sure a row is selected before deleted
        JButton deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(sceneFrame, "Are you sure you want to delete this employee?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (deleteUser()) {
                        JOptionPane.showMessageDialog(sceneFrame, "Employee deleted successfully!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(sceneFrame,
                                "Failed to delete employee. Please select a valid row.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Placing the buttons on the left side
        controlPanelGbc.gridx = 0;
        controlPanelGbc.gridy = 0;
        controlPanelGbc.anchor = GridBagConstraints.WEST;
        controlPanel.add(new JLabel("Name:"), controlPanelGbc);

        controlPanelGbc.gridy = 1;
        controlPanel.add(new JLabel("Hours Worked:"), controlPanelGbc);

        controlPanelGbc.gridx = 1;
        controlPanelGbc.gridy = 0;
        controlPanel.add(nameField, controlPanelGbc);

        controlPanelGbc.gridy = 1;
        controlPanel.add(hoursField, controlPanelGbc);

        controlPanelGbc.gridx = 0;
        controlPanelGbc.gridy = 2;
        controlPanelGbc.gridwidth = 2;
        controlPanelGbc.anchor = GridBagConstraints.WEST;
        controlPanel.add(addButton, controlPanelGbc);

        controlPanelGbc.gridy = 2;
        controlPanelGbc.gridx = 1;
        controlPanel.add(deleteButton, controlPanelGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        scenePanel.add(controlPanel, gbc);

        // Creating table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Hours Worked");
        tableModel.addColumn("Total Amount");

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editMode;
            }
        };

        // Making the table not editable by default
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        scenePanel.add(scrollPane, gbc);

        sceneFrame.getContentPane().add(scenePanel, BorderLayout.CENTER);
        sceneFrame.setLocationRelativeTo(null);
        sceneFrame.setVisible(true);

        // When the export to txt button is clicked the class in the convert.java file
        // is called
        exportToTXT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToTXT();
            }
        });
    }

    // If the edit button is pressed the title changes
    // If it is pressed again the name changes back to the original
    public static void updateFrameTitle() {
        if (editMode) {
            sceneFrame.setTitle("Crew Companion by Pablo - Edit Mode");
        } else {
            sceneFrame.setTitle("Crew Companion by Pablo");
        }
    }

    // Adds the user to the table
    public static boolean addUser(String name, String hours) {
        try {
            double hoursWorked = Double.parseDouble(hours);
            double totalAmount = money_convert.calculateTotalAmount(hoursWorked);

            String formattedHours = money_convert.formatHours(hoursWorked);
            String formattedAmount = money_convert.formatCurrency(totalAmount);

            tableModel.addRow(new Object[] { name, formattedHours, formattedAmount });
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Deletes the user from the table
    public static boolean deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            return true;
        }
        return false;
    }

    // Get data from the table array and exports it to the data folder
    public static void exportToTXT() {
        String filePath = "data";
        Object[][] tableData = getTableData();

        convert.convertToTxt(filePath, tableData);
    }

    // Gets the data from the table and adds it to an array
    public static Object[][] getTableData() {
        int rowCount = tableModel.getRowCount();
        int colCount = tableModel.getColumnCount();
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = tableModel.getValueAt(i, j);
            }
        }

        return data;
    }

    // Sets cell editors for the table based on the current edit mode.
    // If edit mode is true, it sets a default text editor for all cells.
    // If edit mode is false, it removes cell editors, making cells non-editable.
    public static void setCellEditors() {
        if (editMode) {
            table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        } else {
            table.setDefaultEditor(Object.class, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainwindow();
            }
        });
    }
}
