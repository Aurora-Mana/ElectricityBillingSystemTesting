package test.ebs;
import main.ebs.CalculateBill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;


public class TestCalculateBill {

    private CalculateBill bill;

    @BeforeEach
    public void setUp() {
        // Create an instance of CalculateBill within the EDT
        bill = execute(CalculateBill::new);
    }
    @Test
    void testInvalidMeterNoNoLessThan1001(){
        assertTrue(bill.getMeterNumber()<1001);
    }

    @Test
    void testInvalidMeterNoNoMoreThan1010(){
        assertTrue(bill.getMeterNumber()>1010);
    }
    @Test
    void testMeterNumberRange() {
        Choice meterChoice = bill.c1;
        // Define the expected range
        int lowerBound = 1001;
        int upperBound = 1010;

        // Use IntStream to check if all meter numbers are within the range
        boolean allWithinRange = IntStream.range(0, meterChoice.getItemCount())
                .allMatch(i -> {
                    String meterNumber = meterChoice.getItem(i);
                    int meterValue = Integer.parseInt(meterNumber);
                    return meterValue >= lowerBound && meterValue <= upperBound;
                });

        assertTrue(allWithinRange, "All meter numbers should be within the range " + lowerBound + " - " + upperBound);
    }


    @Test
    void testValidInput(){
        bill.c1.select("1001");
        bill.t1.setText("50");
        bill.c2.select("January");

        // Perform action
       bill.actionPerformed(null);

        // Assertions
        String fileContent = bill.getFileContent("bill_info.txt");
        assertTrue(fileContent.contains("Meter No: 1001, Month: January, Units Consumed: 50, Total Charges:"));
    }

    /*

    this one will probably need mocking to fix completely
    @Test
    void testInvalidInputForUnitConsumed() {
        // Set necessary values (e.g., Meter No and Units Consumed)
        bill.setMeterNumber("1005");
        bill.setUnitsConsumed("abc");//invalid might need mocking to fix
        bill.setMonth("March");

        bill.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SimulatedActionCommand"));

        // get content from file
        String fileContent = bill.getFileContent("bill_info.txt");

        // Assertion: Check that the file content does not contain the invalid input
        assertFalse(fileContent.contains("Meter No: 1005, Month: March, Units Consumed: abc, Total Charges:"));

    }

     */

    @Test
    void testInvalidInputMissingMonth(){
        bill.setMeterNumber("1002");
        bill.setUnitsConsumed("30");
        bill.setMonth("");

        bill.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SimulatedActionCommand"));

        String fileContent = bill.getFileContent("bill_info.txt");
        assertFalse(fileContent.contains("Meter No: 1002, Month: , Units Consumed: 30, Total Charges:"));

    }


    @Test
    void testGetFileContentWithError() {
        String fileName = "bill_info.txt";
        String actualContent = bill.getFileContent(fileName);
        assertEquals("Error reading file or file is empty: null", actualContent,
                "Error message should be present for a non-existent file or file with no read permissions");
    }

    @Test
    void testGetFileContentEmptyFile() {
        String fileName = "bill_info.txt";
        String actualContent = bill.getFileContent(fileName);
        assertEquals("", actualContent, "File content should be empty for an empty file");
    }
}










