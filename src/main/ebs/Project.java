package main.ebs;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serial;
import javax.swing.*;
public class Project extends JFrame implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    private final CustomerDetails customerDetails;
    private final NewCustomer newCustomer;
    private CalculateBill calculateBill;
    private final PayBill payBill;
    private final GenerateBill generateBill;
    private final LastBill lastBill;

    private final JMenu utilityMenu;

    public void setCalculateBill(CalculateBill calculateBill) {
        this.calculateBill = calculateBill;
    }

    public Project(
            CustomerDetails customerDetails,
            NewCustomer newCustomer,
            CalculateBill calculateBill,
            PayBill payBill,
            GenerateBill generateBill,
            LastBill lastBill
    ) {
        super("Electricity Billing System");

        this.customerDetails = customerDetails;
        this.newCustomer = newCustomer;
        this.calculateBill = calculateBill;
        this.payBill = payBill;
        this.generateBill = generateBill;
        this.lastBill = lastBill;

        setSize(1500, 800);

        /* Adding background image */
        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("images/main1.jpg"));
        Image i3 = ic.getImage().getScaledInstance(1350, 620,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);

        /* First Column */
        JMenuBar mb  = new JMenuBar();
        JMenu master = new JMenu("Master");
        JMenuItem m1 = new JMenuItem("New Customer");
        JMenuItem m2 = new JMenuItem("Customer Details");
        JMenuItem m3 = new JMenuItem("Deposit Details");
        master.setForeground(Color.BLUE);
        master.setName("master");


        /* ---- Customer Details ---- */
        m1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/icon1.jpg"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        m1.setIcon(new ImageIcon(image1));
        m1.setMnemonic('D');
        m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        m1.setBackground(Color.WHITE);


        /* ---- Meter Details ---- */
        m2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("images/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m2.setIcon(new ImageIcon(image2));
        m2.setMnemonic('M');
        m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        m2.setBackground(Color.WHITE);

        /* ---- Deposit Details  ----- */
        m3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("images/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m3.setIcon(new ImageIcon(image3));
        m3.setMnemonic('N');
        m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        m3.setBackground(Color.WHITE);

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);

        m1.setName("m1");
        m2.setName("m2");
        m3.setName("m3");

        // --------------------------------------------------------------------------------------------


        /* Second Column */
        JMenu user = new JMenu("User");
        JMenuItem u1 = new JMenuItem("Pay Bill");
        JMenuItem u2 = new JMenuItem("Calculate Bill");
        JMenuItem u3 = new JMenuItem("Last Bill");
        user.setForeground(Color.RED);
        user.setName("user");

        /* ---- Pay Bill ---- */
        u1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("images/icon4.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u1.setIcon(new ImageIcon(image4));
        u1.setMnemonic('P');
        u1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        u1.setBackground(Color.WHITE);

        /* ---- Bill Details ---- */
        u2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("images/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u2.setIcon(new ImageIcon(image5));
        u2.setMnemonic('B');
        u2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
        u2.setBackground(Color.WHITE);

        /* ---- Last Bill ----*/
        u3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("images/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u3.setIcon(new ImageIcon(image6));
        u3.setMnemonic('L');
        u3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        u3.setBackground(Color.WHITE);

        u1.addActionListener(this);
        u2.addActionListener(this);
        u3.addActionListener(this);

        u1.setName("u1");
        u2.setName("u2");
        u3.setName("u3");


        // ---------------------------------------------------------------------------------------------

        /* Third Column*/
        JMenu report = new JMenu("Report");
        JMenuItem r1 = new JMenuItem("Generate Bill");
        report.setForeground(Color.BLUE);
        report.setName("report");

        /* ---- Report ---- */
        r1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("images/icon7.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        r1.setIcon(new ImageIcon(image7));
        r1.setMnemonic('R');
        r1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        r1.setBackground(Color.WHITE);

        r1.addActionListener(this);
        r1.setName("r1");

        // -----------------------------------------------------------------------------------------------

        /* Fourth Column*/
        utilityMenu = new JMenu("Utility");
        JMenuItem ut1 = new JMenuItem("Notepad");
        JMenuItem ut2 = new JMenuItem("Calculator");
        JMenuItem ut3 = new JMenuItem("Web Browser");
        utilityMenu.setForeground(Color.RED);
        utilityMenu.setName("utility");

        /* ---- Notepad ---- */
        ut1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("images/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut1.setIcon(new ImageIcon(image8));
        ut1.setMnemonic('C');
        ut1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        ut1.setBackground(Color.WHITE);

        /* ---- Calculator ---- */
        ut2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("images/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut2.setIcon(new ImageIcon(image9));
        ut2.setMnemonic('X');
        ut2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        ut2.setBackground(Color.WHITE);

        /* ---- Web Browser ---- */
        ut3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("images/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut3.setIcon(new ImageIcon(image10));
        ut3.setMnemonic('W');
        ut3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        ut3.setBackground(Color.WHITE);


        ut1.addActionListener(this);
        ut2.addActionListener(this);
        ut3.addActionListener(this);

        ut1.setName("ut1");
        ut2.setName("ut2");
        ut3.setName("ut3");

        // ---------------------------------------------------------------------------------------

        /*Fifth Column */
        JMenu exit = new JMenu("Exit");
        JMenuItem ex = new JMenuItem("Exit");
        exit.setForeground(Color.BLUE);
        exit.setName("exit");

        /* ---- Exit ---- */
        ex.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("images/icon11.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image11));
        ex.setMnemonic('Z');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        ex.setBackground(Color.WHITE);

        ex.addActionListener(this);

        ex.setName("ex");

        // ---------------------------------------------------------------------------------------------


        master.add(m1);
        master.add(m2);
        //master.add(m3);

        user.add(u1);
        user.add(u2);
        user.add(u3);

        report.add(r1);

        utilityMenu.add(ut1);
        utilityMenu.add(ut2);
        utilityMenu.add(ut3);

        exit.add(ex);

        mb.add(master);
        mb.add(user);
        mb.add(report);
        mb.add(utilityMenu);
        mb.add(exit);

        setJMenuBar(mb);

        setFont(new Font("Senserif",Font.BOLD,16));
        setLayout(new FlowLayout());
        setVisible(false);
    }
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        switch (msg) {
            case "Customer Details":
                customerDetails.setVisible(true);
                break;
            case "New Customer":
                newCustomer.setVisible(true);
                break;
            case "Calculate Bill":
                calculateBill.setVisible(true);
                break;
            case "Pay Bill":
                payBill.setVisible(true);
                break;
            case "Notepad":
                openApplication("notepad.exe");
                break;
            case "Calculator":
                openApplication("calc.exe");
                break;
            case "Web Browser":
                openApplication("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Generate Bill":
                generateBill.setVisible(true);
                break;
            case "Last Bill":
                lastBill.setVisible(true);
                break;
        }
    }

    private void openApplication(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerDetails customerDetails = new CustomerDetails();
        NewCustomer newCustomer = null;
        newCustomer = new NewCustomer();
        CalculateBill calculateBill = new CalculateBill();
        PayBill payBill = new PayBill();
        GenerateBill generateBill = new GenerateBill();
        LastBill lastBill = new LastBill();

        Project project = new Project(
                customerDetails, newCustomer, calculateBill,
                payBill, generateBill, lastBill
        );

        project.setVisible(true);
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public NewCustomer getNewCustomer() {
        return newCustomer;
    }

    public CalculateBill getCalculateBill() {
        return calculateBill;
    }

    public PayBill getPayBill() {
        return payBill;
    }

    public GenerateBill getGenerateBill() {
        return generateBill;
    }

    public LastBill getLastBill() {
        return lastBill;
    }



    public boolean isNewCustomerPanelVisible() {
        return newCustomer.isVisible();
    }

    public boolean isCustomerDetailsPanelVisible() {
        return customerDetails.isVisible();
    }

    public boolean isCalculatedBillPanelVisible() {
        return calculateBill.isVisible();
    }

    public boolean isLastBillPanelVisible() {
        return lastBill.isVisible();
    }

    public boolean isGeneratedBillPanelVisible() {
        return generateBill.isVisible();
    }

    public boolean isPayBillVisible() {
        return payBill.isVisible();
    }

    public JMenu getUtilityMenu() {
        return utilityMenu;
    }


}