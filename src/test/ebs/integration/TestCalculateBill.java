package test.ebs.integration;

import main.ebs.CalculateBill;
import main.ebs.GenerateBill;
import main.ebs.ReadDataMock;
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
    private GenerateBill generateBill;
    private ReadDataMock readDataMock;
    private WriteFileMockB writeFileMockB;


    @BeforeEach
    public void setUp() {
        // Create an instances
        bill = execute(CalculateBill::new);
        generateBill = execute(GenerateBill::new);
        writeFileMockB = new WriteFileMockB();
        readDataMock = new ReadDataMock();


    }

    @Test
    public void calculateBillAndCheckIfItAppearsInGenerateBill() {
        bill.setWriteFile(writeFileMockB);
        bill.setBillUpdatedMsg(false);

        bill.getC1().select("1004");
        bill.getT1().setText("50");
        bill.getC2().select("January");

        bill.getB1().doClick();
        assertFalse(bill.isVisible());

        // Simulate data written to file
        readDataMock.writeIntoFileInfo("1004", "January", "50", "50");

        // Set up data for GenerateBill
        readDataMock.setFileInfo("Meter No: 1004, Month: January, Units Consumed: 50, Total Charges: 50");
        generateBill.setReadBillData(readDataMock);
        generateBill.getC1().select("1004");
        generateBill.getC2().select("January");

        // Trigger action on GenerateBill
        generateBill.getB1().doClick();

        // Verify the result in GenerateBill
        String generatedBillText = generateBill.getT1().getText();
        System.out.println(generatedBillText);
        assertTrue(generatedBillText.contains("Meter Number: 1004\n"));
        assertTrue(generatedBillText.contains("Month: January\n"));
        assertTrue(generatedBillText.contains("Units Consumed:  50\n"));
        assertTrue(generatedBillText.contains("Total Charges: 50\n"));
    }



}










