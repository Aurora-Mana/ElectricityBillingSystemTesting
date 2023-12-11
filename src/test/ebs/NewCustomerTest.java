package test.ebs;

import main.ebs.NewCustomer;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class NewCustomerTest {
    @Test
    public void testActionPerformed() throws IOException {
        NewCustomer newCustomer = new NewCustomer();

        newCustomer.t1.setText("Filan");
        newCustomer.t2.setText("321");
        newCustomer.t3.setText("rr. F F");
        newCustomer.t4.setText("Vlorë");
        newCustomer.t5.setText("Konispol");
        newCustomer.t6.setText("filan@festeku.com");
        newCustomer.t7.setText("+355123456789");



        // Verifying that the data was written to the StringWriter
        String expectedData = "Name: Filan, Meter No: 321, Address: rr. F F, State: Vlorë, City: Konispol, Email: filan@festeku.com, Phone Number: +355123456789";
        assertEquals(expectedData, newCustomer.getCustomerInfo());
    }
}
