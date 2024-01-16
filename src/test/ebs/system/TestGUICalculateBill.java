package test.ebs.system;

import java.awt.event.KeyEvent;
import main.ebs.*;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.swing.timing.Pause.pause;
import static org.junit.jupiter.api.Assertions.*;

public class TestGUICalculateBill {

    CalculateBill calculateBill;

    private FrameFixture frame_CB;


    @BeforeEach
    public void setUp() {
        calculateBill = GuiActionRunner.execute(CalculateBill::new);
    }

    @AfterEach
    void afterEach() {
        frame_CB.cleanUp();
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

}