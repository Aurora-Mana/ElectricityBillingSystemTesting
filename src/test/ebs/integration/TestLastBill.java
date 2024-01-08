package test.ebs.integration;

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



