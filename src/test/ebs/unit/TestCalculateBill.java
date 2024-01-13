package test.ebs.unit;

import main.ebs.CalculateBill;
import main.ebs.WriteFileMockB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;


public class TestCalculateBill {

    private CalculateBill bill;

    @BeforeEach
    public void setUp() {

        bill = execute(CalculateBill::new);
        bill.setVisible(false);
    }
    @Test
    void testInvalidMeterNoNoLessThan1001(){
        assertFalse(bill.getMeterNumber()<1001);
    }

    @Test
    void testInvalidMeterNoNoMoreThan1010(){

        assertFalse(bill.getMeterNumber()>1010);
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
    void testValidInputMockVer(){
        WriteFileMockB writeFileMockB = new WriteFileMockB();
        bill.setWriteFile(writeFileMockB);

        bill.c1.select("1001");
        bill.t1.setText("50");
        bill.c2.select("January");

        // Perform action
        bill.getB1().doClick();

        // Assertions
        String fileContent = writeFileMockB.getFileInfo();
        assertTrue(fileContent.contains("Meter No: 1001, Month: January, Units Consumed: 50, Total Charges:"));
    }


    @Test
    void testCalculateBill_CancelOperation() {

        JButton cancelButton = bill.getB2();
        cancelButton.doClick();

        assertFalse(bill.isVisible());
    }


    @Test
    void testInvalidInputForUnitConsumed() {
        // Set necessary values (e.g., Meter No and Units Consumed)
        bill.setMeterNumber("1005");
        bill.setUnitsConsumed("abc");
        bill.setMonth("March");

        assertThrows(NumberFormatException.class,() -> {bill.getB1().doClick();});
    }



    @Test
    void testInvalidInputMissingMonth(){
        bill.setMeterNumber("1002");
        bill.setUnitsConsumed("30");
        bill.setMonth("");

        bill.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SimulatedActionCommand"));

        String fileContent = bill.getFileContent("bill_info.txt");
        assertFalse(fileContent.contains("Meter No: 1002, Month: , Units Consumed: 30, Total Charges:"));

    }

}










