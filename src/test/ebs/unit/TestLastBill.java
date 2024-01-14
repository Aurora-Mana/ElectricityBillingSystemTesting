package test.ebs.unit;

import main.ebs.LastBill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;


public class TestLastBill {

    private LastBill lastBill;

    @BeforeEach
    void setUp(){
        lastBill = execute(LastBill::new);
    }




    @Test
    void testIsMatchingMeterNumber_WithMatchingMeterNumber() {
        boolean result = lastBill.isMatchingMeterNumber("Meter No: 1001", "1001");

        assertTrue(result);
    }

    @Test
    void testIsMatchingMeterNumber_WithNonMatchingMeterNumber() {
        boolean result = lastBill.isMatchingMeterNumber("Meter No: 1002", "1001");

        assertFalse(result);
    }

    @Test
    void testUpdateTextArea_WithLastBillDetails() {
        lastBill.updateTextArea("Meter No: 1001, Month: January, Units Consumed: 20, Total Charges: 300");

        assertEquals("Details of the Last Bill\n\n\n" +
                "Meter No: 1001\nMonth: January\nUnits Consumed: 20\nTotal Charges: 300\n" +
                "---------------------------------------------------------------\n", lastBill.getT1().getText());
    }

    @Test
    void testUpdateTextArea_WithNoBillFound() {
        lastBill.updateTextArea(null);

        assertEquals("No bill found for the selected criteria.", lastBill.getT1().getText());
    }




}



