import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class generate_bill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1, c2;
    JPanel p1;

    generate_bill() {
        setSize(500, 900);
        setLayout(new BorderLayout());

        p1 = new JPanel();
        l1 = new JLabel("Generate Bill");
        c1 = new Choice();
        c2 = new Choice();

        c1.add("1001");
        c1.add("1002");
        c1.add("1003");
        c1.add("1004");
        c1.add("1005");
        c1.add("1006");
        c1.add("1007");
        c1.add("1008");
        c1.add("1009");
        c1.add("1010");


        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");


        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(c1);
        p1.add(c2);
        add(p1, "North");

        add(jsp, "Center");
        add(b1, "South");

        b1.addActionListener(this);

        setLocation(350, 40);
    }

    public void actionPerformed(ActionEvent ae) {
        try {

            String month = c2.getSelectedItem();
            String meterNumber = c1.getSelectedItem();

            t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF " + month + " ,2018\n\n\n");

            BufferedReader reader = new BufferedReader(new FileReader("bill_info.txt"));
            String line;
            boolean billFound = false;

            while ((line = reader.readLine()) != null) {
                String[] billData = line.split(", "); // Adjust this based on the actual format in your file

                System.out.println("Checking line: " + line);

                if (billData[0].startsWith("Meter No: " + meterNumber) && billData[1].startsWith("Month: " + month)) {
                    System.out.println("Match found!");
                    System.out.println(billData[0]);
                    System.out.println(billData[1]);
                    System.out.println(billData[2]);
                    System.out.println(billData[3]);

                    t1.append("Meter Number: " + billData[0].substring(10) + "\n"); // Extracting the value after "Meter No: "
                    t1.append("Month: " + billData[1].substring(7) + "\n"); // Extracting the value after "Month: "
                    t1.append("Units Consumed: " + billData[2].substring(16) + "\n"); // Extracting the value after "Units Consumed: "
                    t1.append("Total Charges: " + billData[3].substring(17) + "\n"); // Extracting the value after "Total Charges: "
                    t1.append("---------------------------------------------------------------\n");
                    billFound = true;

                }
            }
            reader.close();

            if (!billFound) {
                t1.setText("No bill found for the selected criteria.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new generate_bill().setVisible(true);
    }
}
