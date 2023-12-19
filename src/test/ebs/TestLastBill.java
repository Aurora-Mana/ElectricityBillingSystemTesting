package test.ebs;
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
    public void testGUIInitialization() {
        assertNotNull(lastBill.getB1());
        assertNotNull(lastBill.getC1());
        assertNotNull(lastBill.getP1());
        assertNotNull(lastBill.getT1());
    }


    @Test
    void testFindLastBill_WithMatchingMeterNumber() throws IOException {
        StringReader stringReader = new StringReader("Meter No: 1001, Month: January, Units Consumed: 20, Total Charges: 300");
        BufferedReader reader = new BufferedReader(stringReader);

        String result = lastBill.findLastBill(reader, "1001");

        assertEquals("Meter No: 1001, Month: January, Units Consumed: 20, Total Charges: 300", result);
    }

    @Test
    void testFindLastBill_WithNonMatchingMeterNumber() throws IOException {
        StringReader stringReader = new StringReader("Meter No: 1002, Month: February, Units Consumed: 25, Total Charges: 350");
        BufferedReader reader = new BufferedReader(stringReader);

        LastBill lastBill = new LastBill();
        String result = lastBill.findLastBill(reader, "1001");

        assertNull(result);
    }

    @Test
    void testIsMatchingMeterNumber_WithMatchingMeterNumber() {
        LastBill lastBill = new LastBill();
        boolean result = lastBill.isMatchingMeterNumber("Meter No: 1001", "1001");

        assertTrue(result);
    }

    @Test
    void testIsMatchingMeterNumber_WithNonMatchingMeterNumber() {
        LastBill lastBill = new LastBill();
        boolean result = lastBill.isMatchingMeterNumber("Meter No: 1002", "1001");

        assertFalse(result);
    }

    @Test
    void testUpdateTextArea_WithLastBillDetails() {
        LastBill lastBill = new LastBill();
        lastBill.updateTextArea("Meter No: 1001, Month: January, Units Consumed: 20, Total Charges: 300");

        assertEquals("Details of the Last Bill\n\n\n" +
                "Meter No: 1001\nMonth: January\nUnits Consumed: 20\nTotal Charges: 300\n" +
                "---------------------------------------------------------------\n", lastBill.getT1().getText());
    }

    @Test
    void testUpdateTextArea_WithNoBillFound() {
        LastBill lastBill = new LastBill();
        lastBill.updateTextArea(null);

        assertEquals("No bill found for the selected criteria.", lastBill.getT1().getText());
    }


    @Test
    void testFindLastBill_WithEmptyFile() throws IOException {
        StringReader stringReader = new StringReader("");
        BufferedReader reader = new BufferedReader(stringReader);
        String result = lastBill.findLastBill(reader, "1001");

        assertNull(result);
    }

    @Test
    void testFindLastBill_WithInvalidFileFormat() throws IOException {
        StringReader stringReader = new StringReader("Invalid Format");
        BufferedReader reader = new BufferedReader(stringReader);
        String result = lastBill.findLastBill(reader, "1001");

        assertNull(result);
    }



}



