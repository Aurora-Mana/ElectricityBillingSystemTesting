package main.ebs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NewCustomer extends JFrame implements ActionListener{
    public JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    public JTextField t1,t2,t3,t4,t5,t6,t7;
    public JButton b1,b2;

    private final BufferedWriter writer;

    private boolean showMessageDialogs = true;

    public void setShowMessageDialogs(boolean showMessageDialogs) {
        this.showMessageDialogs = showMessageDialogs;
    }

    public NewCustomer() throws IOException {
            this(new BufferedWriter(new FileWriter("customer_info.txt", true)));

    }

    public NewCustomer(BufferedWriter writer) {
        super("Add Customer");
        this.writer = writer;
        initialize();
    }

    public void initialize() {
        setLocation(350,200);
        setSize(650,600);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(9,2,10,10));

        p.setBackground(Color.WHITE);

        l1 = new JLabel("Name");
        t1 = new JTextField();
        p.add(l1);
        p.add(t1);
        l1.setName("l1");
        t1.setName("t1");
        l2 = new JLabel("Meter No");
        t2 = new JTextField();
        p.add(l2);
        p.add(t2);
        l2.setName("l2");
        t2.setName("t2");
        l3 = new JLabel("Address");
        t3 = new JTextField();
        p.add(l3);
        p.add(t3);
        l3.setName("l3");
        t3.setName("t3");
        l4 = new JLabel("State");
        t4 = new JTextField();
        p.add(l4);
        p.add(t4);
        l4.setName("l4");
        t4.setName("t4");
        l5 = new JLabel("City");
        t5 = new JTextField();
        p.add(l5);
        p.add(t5);
        l5.setName("l5");
        t5.setName("t5");
        l6 = new JLabel("Email");
        t6 = new JTextField();
        p.add(l6);
        p.add(t6);
        l6.setName("l6");
        t6.setName("t6");
        l7 = new JLabel("Phone Number");
        t7 = new JTextField();
        p.add(l7);
        p.add(t7);
        l7.setName("l7");
        t7.setName("t7");

        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        p.add(b1);
        p.add(b2);
        setLayout(new BorderLayout());

        add(p,"Center");

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("images/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 280,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);


        add(l8,"West");
        //for changing the color of the whole
        getContentPane().setBackground(Color.WHITE);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b1.setName("b1");
        b2.setName("b2");
    }

    public void actionPerformed(ActionEvent ae) {
        String customerInfo = getCustomerInfo();

        try {
            writer.write(customerInfo);
            writer.newLine();
            writer.close();

            successfulAddition();
            this.setVisible(false);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void successfulAddition() {
        if (showMessageDialogs) {
            JOptionPane.showMessageDialog(null, "Customer Information Recorded Successfully");
        }
    }
    public String getCustomerInfo() {
        String name = t1.getText();
        String meterNo = t2.getText();
        String address = t3.getText();
        String state = t4.getText();
        String city = t5.getText();
        String email = t6.getText();
        String phoneNumber = t7.getText();

        // Prepare the data to be written to the file
        return "Name: " + name + ", Meter No: " + meterNo + ", Address: " + address +
                ", State: " + state + ", City: " + city + ", Email: " + email + ", Phone Number: " + phoneNumber;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                new NewCustomer().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}