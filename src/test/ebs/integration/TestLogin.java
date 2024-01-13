package test.ebs.integration;

import main.ebs.Login;
import main.ebs.Project;
import main.ebs.ReadData;
import main.ebs.ReadDataMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {

    @Test
    void successfulLoginIntegration() {
        // Set up test data in the actual database
        ReadDataMock readUserData = new ReadDataMock();
        readUserData.addInfo("User_1", "userTest");

        // Create instances of Login and Project
        Login login = new Login();
        login.setReadUserData(readUserData);

        // Simulate user input
        login.getTf1().setText("User_1");
        login.getPf2().setText("userTest");

        try {
            // Trigger login action
            login.getB1().doClick();
            Project project = login.getProject();

            // Check if the Project instance is created
            assertNotNull(project);
            assertTrue(project.isVisible());
            assertNotNull(project.getCustomerDetails());
            assertNotNull(project.getNewCustomer());
            assertNotNull(project.getCalculateBill());
            assertNotNull(project.getPayBill());
            assertNotNull(project.getGenerateBill());
            assertNotNull(project.getLastBill());

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
