import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class LastBill extends JFrame implements ActionListener
{
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;
    LastBill(){
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
    public void actionPerformed(ActionEvent ae){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("bill_info.txt"));
            String line;
            String meterNumber = c1.getSelectedItem();
            String lastBill = "";
            boolean billFound = false;

            t1.setText("");

            while((line = reader.readLine())!=null){
                String[] billData = line.split(",");
                if(billData[0].startsWith("Meter No: " + meterNumber)){
                    billFound = true;
                    lastBill = line;
                }
            }
            reader.close();

            if (billFound) {
                t1.setText("Details of the Last Bill\n\n\n");
                String[] lastBillData = lastBill.split(", ");
                for (String data : lastBillData) {
                    t1.append(data + "\n");
                }
                t1.append("---------------------------------------------------------------\n");
            } else {
                t1.setText("No bill found for the selected criteria.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new LastBill().setVisible(true);
    }
}