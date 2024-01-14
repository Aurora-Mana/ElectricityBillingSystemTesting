package test.ebs.system.java.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.ebs.*;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;

public class UserMenuStepDefinitions {
    public static class CalculateBillMock extends CalculateBill{
        private boolean isInvalidInputCalled;
        private boolean isBillUpdatedCalled;

        public CalculateBillMock(){
            super();
            isInvalidInputCalled = false;
            isBillUpdatedCalled = false;
        }

        @Override
        public void billUpdated(){
            isBillUpdatedCalled = true;
        }

        @Override
        public void incorrectInput(){
            isInvalidInputCalled = true;
        }

        public boolean isInvalidInputCalled() {
            return isInvalidInputCalled;
        }

        public boolean isBillUpdatedCalled() {
            return isBillUpdatedCalled;
        }

    }
    private PayBill payBill;
    private CalculateBillMock calculateBill;
    private LastBill lastBill;
    private Path tempFile;


    private Project project;

    // ---------------------------------------------------------
    @Given("Project is visible")
    public void projectPageIsVisible() throws IOException {
        project = new Project(new CustomerDetails(), new NewCustomer(), new CalculateBill(), new PayBill(), new GenerateBill(), new LastBill());
        project.setVisible(true);
    }
    @When("User opens Calculate Bill")
    public void calculateBillShouldOpen() throws IOException {
        calculateBill = execute(() -> {
            CalculateBillMock bill = new CalculateBillMock();
            bill.setBillUpdatedMsg(false); // Disable message dialogs during testing
            return bill;
        });

        // Set the new instance in the project
        project.setCalculateBill(calculateBill);

        // Now, set it visible using the new method
        project.getCalculateBill().setVisible(true);
    }


    @Then("Calculate Bill should be open")
    public void calculateBillShouldBeOpen(){
        assertTrue(calculateBill.isVisible());
    }

    // ---------------------------------------------------------

    @When("User opens Last Bill")
    public void lastBillShouldOpen() throws IOException {
        lastBill = project.getLastBill();
        project.getLastBill().setVisible(true);
    }

    @Then("Last Bill should be open")
    public void lastBillShouldBeOpen(){
        assertTrue(lastBill.isVisible());
    }

    // ---------------------------------------------------------

    @And("User enters valid data for bill calculation")
    public void userEntersValidDataForBillCalculation() {

        calculateBill = execute(() -> {
            CalculateBillMock bill = new CalculateBillMock();
            bill.setBillUpdatedMsg(false); // Disable message dialogs during testing
            return bill;
        });
        WriteFileMockB writeFileMockB = new WriteFileMockB();
        calculateBill.setWriteFile(writeFileMockB);
        calculateBill.setMeterNumber("1001");
        calculateBill.setMonth("January");
        calculateBill.setUnitsConsumed("50");
        calculateBill.setIncorrectInputMss(false);
        calculateBill.getB1().doClick();
    }

    @Then("Bill should be calculated successfully")
    public void billShouldBeCalculatedSuccessfully() {
        assertTrue(calculateBill.isBillUpdatedCalled);
    }


    // ---------------------------------------------------------

    @And("User enters invalid data for bill calculation")
    public void userEntersInvalidDataForBillCalculation() {
        calculateBill = execute(() -> {
            CalculateBillMock bill = new CalculateBillMock();
            bill.setBillUpdatedMsg(false); // Disable message dialogs during testing
            return bill;
        });
        WriteFileMockB writeFileMockB = new WriteFileMockB();
        calculateBill.setWriteFile(writeFileMockB);
        calculateBill.setMeterNumber("");
        calculateBill.setMonth("");
        calculateBill.setUnitsConsumed("");
        calculateBill.setIncorrectInputMss(false);
    }

    @Then("Invalid input message should be displayed")
    public void billShouldBeCalculatedUnSuccessfully() {
        assertThrows(NumberFormatException.class,() -> {calculateBill.getB1().doClick();});
    }

    // ---------------------------------------------------------
    @And("User enters invalid data for Last Bill")
    public void userCheckLastBillWithNoBillHistory() throws IOException {
        LastBill lastBill = project.getLastBill();
        try {
            // Create a temporary file
            Path tempFile = Files.createTempFile("tempBillFile", ".txt");
            lastBill.setFilePath(String.valueOf(tempFile));
            lastBill.setC1Value("1001");
            lastBill.getB1().doClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("No bill found message should be displayed")
    public void noBillFoundMessageShouldBeDisplayed() {
        assertTrue(lastBill.getT1().getText().contains("No bill found for the selected criteria."));
    }

    // ---------------------------------------------------------

    @And("User enters valid data for Last Bill")
    public void userChecksLastBillWithRecords(){
        LastBill lastBill = project.getLastBill();
        try {
            // Create a temporary file
            Path tempFile = Files.createTempFile("tempBillFile", ".txt");
            Files.write(tempFile, "Meter No: 1002, Month: January, Units Consumed: 50, Total Charges: 1000".getBytes());
            lastBill.setFilePath(String.valueOf(tempFile));
            lastBill.setC1Value("1002");
            lastBill.getB1().doClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Bill info should be displayed")
    public void billInfoShouldBeDisplayed(){
        System.out.println(lastBill.getT1().getText().contains("Details of the Last Bill\n" +
                "\n" +
                "\n" +
                "Meter No: 1002\n" +
                "Month: January\n" +
                "Units Consumed: 50\n" +
                "Total Charges: 1000\n" +
                "---------------------------------------------------------------\n"));
    }
}
