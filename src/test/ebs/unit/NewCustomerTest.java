package test.ebs.unit;

import main.ebs.NewCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NewCustomerTest {

    private NewCustomer newCustomer;
    @BeforeEach
    public void setUp() throws IOException {
        newCustomer = new NewCustomer();
    }
    @Test
    public void testActionPerformed() throws IOException {

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

    @Test
    public void testCancelButton(){
        newCustomer.b2.doClick();
        assertFalse(newCustomer.isVisible());
    }

    @Test
    public void wrongTypeOfDataEntered(){
        newCustomer.t1.setText("Filan");
        newCustomer.t2.setText("number");
        newCustomer.t3.setText("rr. F F");
        newCustomer.t4.setText("Vlorë");
        newCustomer.t5.setText("Konispol");
        newCustomer.t6.setText("filan@festeku.com");
        newCustomer.t7.setText("+355123456789");
        newCustomer.setWrongDataTypeWarning(false); // Ensuring the warning table doesn't pop up

        assertEquals(null, newCustomer.getCustomerInfo());

    }
    @Test
    public void emptyDataFieldSubmitted(){
        newCustomer.t1.setText("");
        newCustomer.t2.setText("1002");
        newCustomer.t3.setText("rr. F F");
        newCustomer.t4.setText("Vlorë");
        newCustomer.t5.setText("Konispol");
        newCustomer.t6.setText("filan@festeku.com");
        newCustomer.t7.setText("+355123456789");
        newCustomer.setEmptyFieldsWarning(false); // Ensuring the warning table doesn't pop up

        assertEquals(null, newCustomer.getCustomerInfo());

    }

}
