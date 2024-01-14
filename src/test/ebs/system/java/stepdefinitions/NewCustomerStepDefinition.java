package test.ebs.system.java.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.ebs.*;
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

    private Project project;

    @Given("Project page is visible")
    public void projectPageIsVisible() throws IOException {
        project = new Project(new CustomerDetails(), new NewCustomer(), new CalculateBill(), new PayBill(), new GenerateBill(), new LastBill());
        project.setVisible(true);
    }

    @And("The NewCustomer page is visible")
    public void newCustomerWindowOpens() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");

        // Initialize NewCustomer and CustomerDetails with the temporary file
        newCustomer = execute(() -> {
            NewCustomerMock customer = new NewCustomerMock(new BufferedWriter(Files.newBufferedWriter(tempFile)));
            customer.setShowMessageDialogs(false); // Disable message dialogs during testing
            return customer;
        });
        newCustomer.setVisible(true);

    }
    @When("User submits correct info format")
    public void userSubmitsCorrectInfo(){

        newCustomer.setShowMessageDialogs(false); // Disable message dialogs during testing
        newCustomer.t1.setText("John");
        newCustomer.t2.setText("1234");
        newCustomer.t3.setText("Address1");
        newCustomer.t4.setText("State1");
        newCustomer.t5.setText("City1");
        newCustomer.t6.setText("email1@emialing.com");
        newCustomer.t7.setText("0123456789");
        newCustomer.getB1().doClick();
    }

    @Then("The NewCustomer page closes")
    public void theNewCustomerPageShouldClose(){
        assertFalse(newCustomer.isVisible());
    }


    // ---------------------------------------------------------------------

    @When("User submits incorrect information")
    public void userSubmitsIncorrectInformation(){
        newCustomer.setWrongDataTypeWarning(false); // Disable message dialogs during testing
        newCustomer.t1.setText("John");
        newCustomer.t2.setText("1234");
        newCustomer.t3.setText("Address");
        newCustomer.t4.setText("State");
        newCustomer.t5.setText("City");
        newCustomer.t6.setText("email@emialing.com");
        newCustomer.t7.setText("number");
        newCustomer.getB1().doClick();
    }

    @Then("The NewCustomer page wrong data type warning displays")
    public void theNewCustomerPageTypeWarningPop(){
        assertTrue(newCustomer.isInvalidInformationCalled());
    }


    // ---------------------------------------------------------------------

    @When("One or more of the fields are empty")
    public void emptyFieldRun() throws IOException {

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
    public void emptyDataFieldWarningShouldHappen() {
        assertTrue(newCustomer.isEmptyFieldsCalled);
    }
}