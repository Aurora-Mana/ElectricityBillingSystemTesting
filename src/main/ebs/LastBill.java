package main.ebs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LastBill extends JFrame implements ActionListener
{
    private JLabel l1;
    private JTextArea t1;
    private JButton b1;
    private Choice c1;
    private JPanel p1;
    private BufferedReader reader;

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    private JLabel getL1(){
        return l1;
    }

    public  JTextArea getT1(){
      return t1;
    }

    public JButton getB1(){
        return b1;
    }

    public Choice getC1(){
        return c1;
    }

    public JPanel getP1(){
        return p1;
    }


    public LastBill(){
        setSize(500,900);
        setLayout(new BorderLayout());

        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        c1 = new Choice();

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


        t1 = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(c1);
        add(p1,"North");

        add(jsp,"Center");
        add(b1,"South");

        b1.addActionListener(this);

        setLocation(350,40);
    }


    public void actionPerformed(ActionEvent ae) {
        try {
            reader = new BufferedReader(new FileReader("bill_info.txt"));
            String meterNumber = c1.getSelectedItem();
            String lastBill = findLastBill(reader, meterNumber);
            reader.close();

            updateTextArea(lastBill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String findLastBill(BufferedReader reader, String meterNumber) throws IOException {
        String line;
        boolean billFound = false;
        String lastBill = "";

        while ((line = reader.readLine()) != null) {
            String[] billData = line.split(",");
            if (isMatchingMeterNumber(billData[0], meterNumber)) {
                billFound = true;
                lastBill = line;
                break;
            }
        }

        return billFound ? lastBill : null;
    }

    public boolean isMatchingMeterNumber(String billData, String meterNumber) {
        return billData.toLowerCase().startsWith("meter no: " + meterNumber.toLowerCase());
    }

    public void updateTextArea(String lastBill) {
        if (lastBill != null) {
            displayLastBillDetails(lastBill);
        } else {
            displayNoBillFoundMessage();
        }
    }

    private void displayLastBillDetails(String lastBill) {
        t1.setText("Details of the Last Bill\n\n\n");
        String[] lastBillData = lastBill.split(", ");
        for (String data : lastBillData) {
            t1.append(data + "\n");
        }
        t1.append("---------------------------------------------------------------\n");
    }

    private void displayNoBillFoundMessage() {
        t1.setText("No bill found for the selected criteria.");
    }


    public static void main(String[] args){

        new LastBill().setVisible(true);
    }
}