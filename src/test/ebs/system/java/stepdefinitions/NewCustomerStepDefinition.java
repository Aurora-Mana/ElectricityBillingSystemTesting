package test.ebs.system.java.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.ebs.CustomerDetails;
import main.ebs.NewCustomer;
import main.ebs.ReadData;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewCustomerStepDefinition {
    public static class NewCustomerMock extends NewCustomer{
        private boolean isInvalidInformationCalled;
        private boolean isEmptyFieldsCalled;


        public NewCustomerMock() throws IOException {
            super();
            isInvalidInformationCalled = false;
            isEmptyFieldsCalled = false;
        }

        public NewCustomerMock(Writer writer) {
            super(writer);
            isInvalidInformationCalled =false;
            isEmptyFieldsCalled = false;
        }

        @Override
        public void wrongDataType(){
            isInvalidInformationCalled = true;
        }

        public boolean isInvalidInformationCalled() {
            return isInvalidInformationCalled;
        }

        @Override
        public void emptyFields(){
            isEmptyFieldsCalled = true;
        }
        public boolean isEmptyFieldsCalled() {
            return isEmptyFieldsCalled;
        }
    }
    private NewCustomerMock newCustomer;
    private CustomerDetails customerDetails;
    private Path tempFile;

    @Given("The values are of the proper type")
    public void correctInfoTypeIsEntered() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");

        // Initialize NewCustomer and CustomerDetails with the temporary file
        newCustomer = execute(() -> {
            NewCustomerMock customer = new NewCustomerMock(new BufferedWriter(Files.newBufferedWriter(tempFile)));
            customer.setShowMessageDialogs(false); // Disable message dialogs during testing
            return customer;
        });

        newCustomer.setShowMessageDialogs(false); // Disable message dialogs during testing
        newCustomer.t1.setText("John");
        newCustomer.t2.setText("1234");
        newCustomer.t3.setText("Address");
        newCustomer.t4.setText("State");
        newCustomer.t5.setText("City");
        newCustomer.t6.setText("email@emialing.com");
        newCustomer.t7.setText("0123456789");
        newCustomer.b1.doClick(); // Simulate button click
    }

    @When("User submits information")
    public void submitCorrectInfoType(){
        // Simulate button click
        newCustomer.getB1().doClick();
    }

    @Then("The NewCustomer page closes")
    public void NewCustomerClosesAfterAddition(){
        // Check if the project window is visible
        assertFalse(newCustomer.isVisible());
    }

    @Given("The values are entered incorrectly")
    public void incorrectInformationTypeEntered() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");

        // Initialize NewCustomer and CustomerDetails with the temporary file
        newCustomer = execute(() -> {
            NewCustomerMock customer = new NewCustomerMock(new BufferedWriter(Files.newBufferedWriter(tempFile)));
            customer.setShowMessageDialogs(false); // Disable message dialogs during testing
            return customer;
        });

        newCustomer.setWrongDataTypeWarning(false); // Disable message dialogs during testing
        newCustomer.t1.setText("John");
        newCustomer.t2.setText("1234");
        newCustomer.t3.setText("Address");
        newCustomer.t4.setText("State");
        newCustomer.t5.setText("City");
        newCustomer.t6.setText("email@emialing.com");
        newCustomer.t7.setText("number");
        newCustomer.b1.doClick(); // Simulate button click
    }

    @Then("The NewCustomer page wrong data type warning displays")
    public void wrongDataTypeWarningDisplayed(){
        assertTrue(newCustomer.isInvalidInformationCalled);
    }

    @Given("One or more of the fields are empty")
    public void emptyFieldsTesting() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");

        // Initialize NewCustomer and CustomerDetails with the temporary file
        newCustomer = execute(() -> {
            NewCustomerMock customer = new NewCustomerMock(new BufferedWriter(Files.newBufferedWriter(tempFile)));
            customer.setShowMessageDialogs(false); // Disable message dialogs during testing
            return customer;
        });

        newCustomer.setShowMessageDialogs(false); // Disable message dialogs during testing
        newCustomer.t1.setText("John");
        newCustomer.t2.setText("1234");
        newCustomer.t3.setText("");
        newCustomer.t4.setText("State");
        newCustomer.t5.setText("City");
        newCustomer.t6.setText("email@emialing.com");
        newCustomer.t7.setText("0123456789");
        newCustomer.b1.doClick(); // Simulate button click
    }

    @Then("The NewCustomer page empty data field warning displays")
    public void emptyDataFieldsWarningDisplayed(){
        assertTrue(newCustomer.isEmptyFieldsCalled);
    }

    @Given("User created a new customer")
    public void userCreatesNewCustomer() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");

        // Initialize NewCustomer and CustomerDetails with the temporary file
        newCustomer = execute(() -> {
            NewCustomerMock customer = new NewCustomerMock(new BufferedWriter(Files.newBufferedWriter(tempFile)));
            customer.setShowMessageDialogs(false); // Disable message dialogs during testing
            return customer;
        });

        newCustomer.setShowMessageDialogs(false); // Disable message dialogs during testing
        newCustomer.t1.setText("John");
        newCustomer.t2.setText("1234");
        newCustomer.t3.setText("Address");
        newCustomer.t4.setText("State");
        newCustomer.t5.setText("City");
        newCustomer.t6.setText("email@emialing.com");
        newCustomer.t7.setText("0123456789");
        newCustomer.b1.doClick(); // Simulate button click
    }

    @When("User views the customer information")
    public void openingAndSettingUpCustomerInfo(){
        customerDetails = execute(() -> new CustomerDetails(new ReadData()));
    }

    @Then("The information is correct")
    public void confirmationThatTheInformationIsCorrect(){
        String[][] customerInfo = customerDetails.getCustomerData();
        String[][] actualNonNull = Arrays.stream(customerInfo)
                .filter(row -> row != null)
                .filter(row -> Arrays.stream(row).noneMatch(cell -> cell == null || cell.equals("null")))
                .toArray(String[][]::new);e
        // Directly compare the actual and expected arrays
        assertThat(actualNonNull.equals(new String[][]{
                {"John", "1234", "Address1", "State1", "City1", "john@example.com", "1234567890"}
        }));
    }
}
