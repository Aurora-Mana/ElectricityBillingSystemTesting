package main.ebs;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.swing.*;

public class PayBill extends JFrame{

    private JEditorPane j;

    public PayBill() {
        this(new JEditorPane());
    }

    public PayBill(JEditorPane editorPane) {
        this.j = editorPane;
        initialize();
    }

    private void initialize() {
        j.setEditable(false);

        try {
            loadPage(URI.create("https://paytm.com/electricity-bill-payment").toURL());
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");
        }

        JScrollPane scrollPane = new JScrollPane(j);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800,600));
        setSize(800,800);
        setLocation(250,120);
        setVisible(true);
    }

    public void loadPage(java.net.URL url) throws IOException {
        j.setPage(url);
    }

    public JEditorPane getEditorPane() { return j;}

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new PayBill().setVisible(true));
    }
}