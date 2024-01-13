package test.ebs.unit;

import main.ebs.*;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CustomMockCustomerDetails extends CustomerDetails {
    boolean setVisibleCalled = false;

    @Override
    public void setVisible(boolean visible) {
        setVisibleCalled = true;
    }
}

class CustomMockNewCustomer extends NewCustomer {
    boolean setVisibleCalled = false;

    CustomMockNewCustomer() throws java.io.IOException {

    }

    @Override
    public void setVisible(boolean visible) {
        setVisibleCalled = true;
    }
}

class CustomMockCalculateBill extends CalculateBill {
    boolean setVisibleCalled = false;

    @Override
    public void setVisible(boolean visible) {
        setVisibleCalled = true;
    }
}

class CustomMockPayBill extends PayBill {
    boolean setVisibleCalled = false;

    @Override
    public void setVisible(boolean visible) {
        setVisibleCalled = true;
    }
}

class CustomMockGenerateBill extends GenerateBill {
    boolean setVisibleCalled = false;

    @Override
    public void setVisible(boolean visible) {
        setVisibleCalled = true;
    }
}

class CustomMockLastBill extends LastBill {
    boolean setVisibleCalled = false;

    @Override
    public void setVisible(boolean visible) {
        setVisibleCalled = true;
    }
}

public class ProjectTest {

    @Test
    void actionPerformed_customerDetailsVisibility() {
        CustomMockCustomerDetails customerDetails = new CustomMockCustomerDetails();
        Project project = createProjectWithCustomMocks(customerDetails, null, null, null, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Customer Details")));

        assertTrue(customerDetails.setVisibleCalled);
    }

    @Test
    void actionPerformed_newCustomerVisibility() throws IOException {
        CustomMockNewCustomer newCustomer = new CustomMockNewCustomer();
        Project project = createProjectWithCustomMocks(null, newCustomer, null, null, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "New Customer")));

        assertTrue(newCustomer.setVisibleCalled);
    }

    @Test
    void actionPerformed_calculateBillVisibility() {
        CustomMockCalculateBill calculateBill = new CustomMockCalculateBill();
        Project project = createProjectWithCustomMocks(null, null, calculateBill, null, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Calculate Bill")));

        assertTrue(calculateBill.setVisibleCalled);
    }

    @Test
    void actionPerformed_payBillVisibility() {
        CustomMockPayBill payBill = new CustomMockPayBill();
        Project project = createProjectWithCustomMocks(null, null, null, payBill, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Pay Bill")));

        assertTrue(payBill.setVisibleCalled);
    }

    @Test
    void actionPerformed_generateBillVisibility() {
        CustomMockGenerateBill generateBill = new CustomMockGenerateBill();
        Project project = createProjectWithCustomMocks(null, null, null, null, generateBill, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Generate Bill")));

        assertTrue(generateBill.setVisibleCalled);
    }

    @Test
    void actionPerformed_lastBillVisibility() {
        CustomMockLastBill lastBill = new CustomMockLastBill();
        Project project = createProjectWithCustomMocks(null, null, null, null, null, lastBill);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Last Bill")));

        assertTrue(lastBill.setVisibleCalled);
    }

    @Test
    void actionPerformed_openApplicationNotepad() {
        Project project = createProjectWithCustomMocks(null, null, null, null, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Notepad")));
        // Add assertions to verify the behavior or visibility of components related to "Notepad"
    }

    @Test
    void actionPerformed_openApplicationCalculator() {
        Project project = createProjectWithCustomMocks(null, null, null, null, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Calculator")));
    }

    @Test
    void actionPerformed_openApplicationWebBrowser() {
        Project project = createProjectWithCustomMocks(null, null, null, null, null, null);

        assertDoesNotThrow(() -> project.actionPerformed(new ActionEvent(project, ActionEvent.ACTION_PERFORMED, "Web Browser")));
    }

    @Test
    void gettersReturnNonNullInstances() {
        CustomerDetails customerDetails = new CustomerDetails();
        NewCustomer newCustomer;
        try {
            newCustomer = new NewCustomer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CalculateBill calculateBill = new CalculateBill();
        PayBill payBill = new PayBill();
        GenerateBill generateBill = new GenerateBill();
        LastBill lastBill = new LastBill();

        Project project = new Project(
                customerDetails, newCustomer, calculateBill,
                payBill, generateBill, lastBill
        );

        assertNotNull(project.getCustomerDetails());
        assertNotNull(project.getNewCustomer());
        assertNotNull(project.getCalculateBill());
        assertNotNull(project.getPayBill());
        assertNotNull(project.getGenerateBill());
        assertNotNull(project.getLastBill());
    }

    private Project createProjectWithCustomMocks(
            CustomerDetails customerDetails,
            NewCustomer newCustomer,
            CalculateBill calculateBill,
            PayBill payBill,
            GenerateBill generateBill,
            LastBill lastBill
    ) {
        return new Project(
                customerDetails,
                newCustomer,
                calculateBill,
                payBill,
                generateBill,
                lastBill
        );
    }
}
