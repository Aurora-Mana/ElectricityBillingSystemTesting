package main.ebs;

import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class CustomerDetails extends JFrame implements ActionListener {

    JTable t1;
    JButton b1;
    String x[] = {"Emp Name", "Meter No", "Address", "State", "City", "Email", "Phone"};
    String y[][] = new String[20][8];
    int i = 0, j = 0;

    CustomerDetails() {
        super("Customer Details");
        setSize(1200, 650);
        setLocation(200, 200);

        // Read data from the file "customer_info.txt"
        try {
            BufferedReader reader = new BufferedReader(new FileReader("customer_info.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                for (String str : data) {
                    String[] parts = str.split("\\s+"); // Split by whitespace
                    String lastWord = parts[parts.length - 1]; // Get the last element
                    y[i][j++] = lastWord; // Assign last element to y
                }

                i++; // Move to the next row in y for the next line of data
                j = 0; // Reset column index for the next row
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create JTable with the retrieved data
        t1 = new JTable(y, x);

        b1 = new JButton("Print");
        add(b1, "South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);
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
