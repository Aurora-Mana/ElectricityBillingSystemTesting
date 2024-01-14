package test.ebs.integration;

import main.ebs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;

public class TestGenerateBill {
    private GenerateBill generateBill;

    @BeforeEach
    void setUp() {
        generateBill = execute(GenerateBill::new);
    }


    @Test
    public void testFileReadingDataPopulation() {

        generateBill.getC1().select("1001");
        generateBill.getC2().select("January");

        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));
        assertNotNull(generateBill.t1.getText());

    }



    @Test
    public void testFileReadingDataPopulationMock(){
        // Create an instance of the mock and set it as the data provider for GenerateBill
        ReadDataMock readBillDataMock = new ReadDataMock();


        generateBill.getC1().select("1001");
        generateBill.getC2().select("January");
        readBillDataMock.writeIntoFileInfo("1001","January", "50","250");
        generateBill.setReadBillData(readBillDataMock);

        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));
        assertNotNull(generateBill.getT1().getText());

    }



    @Test
    public void testSuccessfulBillRevivalMock() throws IOException {
        ReadDataMock readBillDataMock = new ReadDataMock();
        generateBill.getC1().select("1002");
        generateBill.getC2().select("March");
        readBillDataMock.writeIntoFileInfo("1002", "March", "40","444");
        generateBill.setReadBillData(readBillDataMock);


        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));

        String actualText = generateBill.getT1().getText();
        String expectedText = "\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF March ,2018\n\n\n" +
                "Meter Number: 1002\nMonth: March\nUnits Consumed: 40\nTotal Charges: 444\n---------------------------------------------------------------\n";

        assertEquals(expectedText.trim(), actualText.trim(), "Expected: " + expectedText + "\nActual: " + actualText);

    }




}
