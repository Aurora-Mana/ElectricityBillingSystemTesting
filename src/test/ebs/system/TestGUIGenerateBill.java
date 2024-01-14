package test.ebs.system;

import main.ebs.*;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGUIGenerateBill {


        GenerateBill generateBill;

        private FrameFixture frame_GB;



        @BeforeEach
        public void setUp() {
            generateBill = GuiActionRunner.execute(GenerateBill::new);
        }

        @AfterEach
        void afterEach() {
            frame_GB.cleanUp();
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



            frame_GB.robot().click(generateBill.getC1());
            frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_DOWN);
            frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

            frame_GB.robot().click(generateBill.getC2());
            frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER, KeyEvent.VK_DOWN,KeyEvent.VK_DOWN );
            frame_GB.robot().pressAndReleaseKeys(KeyEvent.VK_ENTER);

            ReadDataMock readBillDataMock = new ReadDataMock();
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

}
