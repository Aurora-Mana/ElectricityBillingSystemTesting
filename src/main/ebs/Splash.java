package main.ebs;

import java.awt.*;
import javax.swing.*;
public class Splash
{
    public static void main(String args[])
    {
        fframe f1=new fframe();
        int i;
        int x=1;
        for(i=2;i<=600;i+=4,x++)
        {
            f1.setLocation(800-((i+x)/2),500-(i/2));
            f1.setSize((i+x),i);
            try
            {
                Thread.sleep(10);
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }
    public static class fframe extends JFrame implements Runnable
    {
        public Thread t1;
        public fframe()
        {
            super("Electricity Billing System");
            this.setVisible(true);
            setLayout(new FlowLayout());
            ImageIcon c1=new ImageIcon(ClassLoader.getSystemResource("images/splash.jpg"));
            Image i1=c1.getImage().getScaledInstance(720,550,Image.SCALE_DEFAULT);
            ImageIcon i2=new ImageIcon(i1);

            JLabel l1=new JLabel(i2);
            add(l1);
            t1=new Thread(this);
            t1.start();
        }
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(7000);
                this.setVisible(false);
                new Login().setVisible(true);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

}
