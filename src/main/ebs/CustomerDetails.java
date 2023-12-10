package main.ebs;

import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomerDetails extends JFrame implements ActionListener {

    JTable t1;
    JButton b1;
    private final String[] colName = {"Emp Name", "Meter No", "Address", "State", "City", "Email", "Phone"};
    private String[][] customerData= new String[20][8];


    public CustomerDetails() {
        super("Customer Details");
        setSize(1200, 650);
        setLocation(200, 200);

        // Call the method to read data from file
        try {
            readDataFromFile();
        } catch (IOException e) {
            System.out.println("Error reading file or file is empty: " + e.getMessage());
        }

        // Create JTable with the retrieved data
        t1 = new JTable(customerData, colName);

        b1 = new JButton("Print");
        add(b1, "South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);
    }

    // method to read info from file
    public void readDataFromFile() throws IOException {
        customerData = new String[20][8];
        int i = 0, j = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("customer_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(", ");

                for (String str : data) {
                    if (j < 8) { // Check if j is within bounds
                        String[] parts = str.split("\\s+");
                        String lastWord = parts[parts.length - 1];
                        customerData[i][j++] = lastWord;
                    }
                }

                i++;
                j = 0;
            }
        }
    }


    public String[][] getCustomerData() {
        return customerData;
    }

    public String[] getColName(){
        return colName;
    }

    public JTable getT1() {
        return t1;
    }


    public void actionPerformed(ActionEvent ae) {
        try {
            t1.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerDetails().setVisible(true);
    }
}