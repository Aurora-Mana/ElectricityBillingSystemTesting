package main.ebs;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login extends JFrame implements ActionListener
{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2;
    JPanel p1,p2,p3,p4;
    ReadData readUserData = new ReadData();
    private boolean showMessageDialogs = true;
    private Project project;

    public Project getProject() {
        return project;
    }

    public boolean getShowMessageDialogs(){
        return showMessageDialogs;
    }

    public void setShowMessageDialogs(boolean showMessageDialogs){
        this.showMessageDialogs=showMessageDialogs;
    }
    JTextArea t1;

    public void setReadUserData(ReadData readUserData) {
        this.readUserData = readUserData;
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JTextField getTf1() {
        return tf1;
    }

    public JPasswordField getPf2() {
        return pf2;
    }

    public Login()
    {
        super("Login Page");
        l1=new JLabel("User Name");
        l1.setName("l1");
        l2=new JLabel("Password");
        l2.setName("l2");
        tf1=new JTextField(15);
        tf1.setName("tf1");
        pf2=new JPasswordField(15);
        pf2.setName("pf2");

        ImageIcon ic1=new ImageIcon(ClassLoader.getSystemResource("images/login.jpg"));
        Image i1=ic1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        b1=new JButton("Login",new ImageIcon(i1));

        ImageIcon ic2=new ImageIcon(ClassLoader.getSystemResource("images/cancel.png"));
        Image i2=ic2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        b2=new JButton("Cancel",new ImageIcon(i2));

        b1.addActionListener(this);
        b1.setName("b1");
        b2.addActionListener(this);
        b2.setName("b2");

        ImageIcon ic3=new ImageIcon(ClassLoader.getSystemResource("images/pop2.jpg"));
        Image i3=ic3.getImage().getScaledInstance(340,370,Image.SCALE_DEFAULT);
        ImageIcon icc3=new ImageIcon(i3);

        l3=new JLabel(icc3);
        l3.setName("l3");

        setLayout(new BorderLayout());

        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();

        add(l3,BorderLayout.WEST);
        p2.add(l1);
        p2.add(tf1);
        p2.add(l2);
        p2.add(pf2);
        add(p2,BorderLayout.CENTER);

        p4.add(b1);
        p4.add(b2);
        add(p4,BorderLayout.SOUTH);

        p2.setBackground(Color.WHITE);
        p4.setBackground(Color.WHITE);


        setSize(640,450);
        setLocation(600,400);
        setVisible(true);

    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                String username = tf1.getText();
                String password = pf2.getText();
                boolean userFound = this.readUserData.readUserData(username, password);

                if (userFound) {
                    project = new Project(new CustomerDetails(),
                            new NewCustomer(),
                            new CalculateBill(),
                            new PayBill(),
                            new GenerateBill(),
                            new LastBill());
                    project.setVisible(true);
                    this.setVisible(false);
                } else {
                    // Call the method to handle invalid login
                    invalidLoginAction();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e);
            }
        } else if (ae.getSource() == b2) {
            // Cancel button
            setVisible(false);
        }
    }

    // New method to handle invalid login action
    public void invalidLoginAction() {
        if (showMessageDialogs) {
            JOptionPane.showMessageDialog(null, "Invalid Login");
        }
    }


    public static void main(String[] args){
        new Login().setVisible(true);
    }

}
