package test.ebs.system;
import java.awt.event.KeyEvent;
import main.ebs.ReadBillDataMock;
import main.ebs.*;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.swing.timing.Pause.pause;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class TestGUI {
    CalculateBill calculateBill;

    private FrameFixture frame_CB;

    GenerateBill generateBill;

    private FrameFixture frame_GB;


    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        calculateBill = GuiActionRunner.execute(CalculateBill::new);
        generateBill = GuiActionRunner.execute(GenerateBill::new);


    }


    //GUI TESTING FOR CALCULATE BILL CLASS
    @Test
    void testLabels() {
        frame_CB = new FrameFixture(calculateBill);

        frame_CB.show(); // Display the frame for testing
        frame_CB.requireVisible(); // Ensure that the frame is visible

        frame_CB.label("l1").requireText("Calculate Electricity Bill");
        frame_CB.label("l2").requireText("Meter No");
        frame_CB.label("l3").requireText("Units Consumed");
        frame_CB.label("l5").requireText("Month");

    }


     @Test
    void testSubmitButton() {
         frame_CB = new FrameFixture(calculateBill);

         frame_CB.show(); // Display the frame for testing
         frame_CB.requireVisible(); // Ensure that the frame is visible

        WriteFileMockB writeFileMockB = new WriteFileMockB();
        calculateBill.setWriteFile(writeFileMockB);

        frame_CB.robot().click(calculateBill.c1);
        frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN, KeyEvent.VK_DOWN);
        frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

         frame_CB.robot().click(calculateBill.c2);
         frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN, KeyEvent.VK_DOWN);
         frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

        frame_CB.textBox("t1").enterText("50");



        pause(1000);
        // Click the "Submit" button
        frame_CB.button("b1").click();

        frame_CB.optionPane().requireMessage("Bill Updated");
    }



    @Test
    void testMeterNumberChoice() {
        frame_CB = new FrameFixture(calculateBill);

        frame_CB.show(); // Display the frame for testing
        frame_CB.requireVisible(); // Ensure that the frame is visible

        // Simulate user selecting a meter number
        //for our test we chose number 1003
        frame_CB.robot().click(calculateBill.c1);
        frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN, KeyEvent.VK_DOWN);
        frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);


        // Verify the selected meter number
        String selectedMeterNumber = calculateBill.getC1().getSelectedItem();
        assertEquals("1003", selectedMeterNumber, "Selected meter number should be 1003");
    }



    @Test

    void testMonthChoice() {
        frame_CB = new FrameFixture(calculateBill);

        frame_CB.show(); // Display the frame for testing
        frame_CB.requireVisible(); // Ensure that the frame is visible

        // Simulate user selecting a month
        //we have selected February
        frame_CB.robot().click(calculateBill.c2);
        frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN);
        frame_CB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

        pause(1000);
        // Verify the selected month
        // Verify the selected meter number
        String selectedMeterNumber = calculateBill.getC2().getSelectedItem();
        assertEquals("February", selectedMeterNumber, "Selected month should be February");
    }

    @Test
    void testCancelButton() {
        frame_CB = new FrameFixture(calculateBill);

        frame_CB.show(); // Display the frame for testing
        frame_CB.requireVisible(); // Ensure that the frame is visible

        // Click the "Cancel" button
        frame_CB.button("b2").click();

        // Verify that the CalculateBill window is closed
        frame_CB.requireNotVisible();
    }


    //GUI TESTING FOR GENERATE BILL CLASS
    @Test
    void testLabels_Button() {
        frame_GB = new FrameFixture(generateBill);
        frame_GB.show(); // Display the frame for testing
        frame_GB.requireVisible(); // Ensure that the frame is visible


        frame_GB.label("l1").requireText("Generate Bill");
        frame_GB.button("b1").requireText("Generate Bill");
    }


    @Test
    void testGenerateButtonValidInput() {
        frame_GB = new FrameFixture(generateBill);

        frame_GB.show(); // Display the frame for testing
        frame_GB.requireVisible(); // Ensure that the frame is visible

        frame_GB.maximize();

        WriteFileMockB writeFileMockB = new WriteFileMockB();
        calculateBill.setWriteFile(writeFileMockB);


        frame_GB.robot().click(generateBill.getC1());
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN);
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

        frame_GB.robot().click(generateBill.getC2());
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER, KeyEvent.VK_DOWN,KeyEvent.VK_DOWN );
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

        ReadBillDataMock readBillDataMock = new ReadBillDataMock();
        readBillDataMock.writeIntoFileInfo("1002", "March", "40","444");
        generateBill.setReadBillData(readBillDataMock);

        frame_GB.button("b1").click();

        String expectedBillText = "\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF March ,2018\n\n\n" +
                "Meter Number: 1002\nMonth: March\nUnits Consumed: 40\nTotal Charges: 444\n---------------------------------------------------------------\n";


        // Get the text from the JTextArea
        String actualText = generateBill.getT1().getText();

        // Assert that the text is as expected
        assertEquals(expectedBillText, actualText);
    }


    @Test
    void testGenerateButtonInvalidInput(){
        frame_GB = new FrameFixture(generateBill);
        frame_GB.show(); // Display the frame for testing
        frame_GB.requireVisible(); // Ensure that the frame is visible

        frame_GB.maximize();

        WriteFileMockB writeFileMockB = new WriteFileMockB();
        calculateBill.setWriteFile(writeFileMockB);


        frame_GB.robot().click(generateBill.getC1());
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN, KeyEvent.VK_DOWN,KeyEvent.VK_DOWN,KeyEvent.VK_DOWN,KeyEvent.VK_DOWN);
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

        frame_GB.robot().click(generateBill.getC2());
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER, KeyEvent.VK_DOWN,KeyEvent.VK_DOWN );
        frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);


        frame_GB.button("b1").click();
        
        String expectedBillText = "No bill found for the selected criteria.";


        // Get the text from the JTextArea
        String actualText = generateBill.getT1().getText();

        // Assert that the text is as expected
        assertEquals(expectedBillText, actualText);
    }


    //GUI TESTING CUSTOMER DETAILS


}


