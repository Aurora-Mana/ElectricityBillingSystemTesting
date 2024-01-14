package test.ebs.system;
import main.ebs.*;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGUILastBill {
    LastBill lastBill;

    FrameFixture frame_LB;


    @BeforeEach
    public void setUp() {
        lastBill = GuiActionRunner.execute(LastBill::new);
    }

    @AfterEach
    void afterEach() {
        frame_LB.cleanUp();
    }

    @Test
    void testLabels_Button() {
        frame_LB = new FrameFixture(lastBill);
        frame_LB.show(); // Display the frame for testing
        frame_LB.requireVisible(); // Ensure that the frame is visible


        frame_LB.label("l1").requireText("Generate Bill");
        frame_LB.button("b1").requireText("Generate Bill");
    }


    @Test
    void testGenerateButtonValidInput() {
        frame_LB = new FrameFixture(lastBill);

        frame_LB.show(); // Display the frame for testing
        frame_LB.requireVisible(); // Ensure that the frame is visible

        frame_LB.maximize();


        frame_LB.robot().click(lastBill.getC1());
        frame_LB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);


        frame_LB.button("b1").click();


        // Assert the content of the text area based on the expected behavior
        String expectedText = "Details of the Last Bill\n\n\n"+
                "Meter No: 1001\nMonth: January\nUnits Consumed: 213\nTotal Charges: 1725\n---------------------------------------------------------------\n"; // Add expected content

        // Get the text from the JTextArea
        String actualText = lastBill.getT1().getText();

        // Assert that the text is as expected
        assertEquals(expectedText, actualText);
    }

    @Test
    void testGenerateButtonInvalidInput(){
        frame_LB = new FrameFixture(lastBill);

        frame_LB.show(); // Display the frame for testing
        frame_LB.requireVisible(); // Ensure that the frame is visible

        frame_LB.maximize();


        frame_LB.robot().click(lastBill.getC1());
        frame_LB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN);
        frame_LB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);


        frame_LB.button("b1").click();

        String expectedBillText = "No bill found for the selected criteria.";


        // Get the text from the JTextArea
        String actualText = lastBill.getT1().getText();

        // Assert that the text is as expected
        assertEquals(expectedBillText, actualText);
    }


}
