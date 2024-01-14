package test.ebs.unit;

import main.ebs.GenerateBill;
import main.ebs.ReadDataMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;

public class TestGenerateBill {
    private GenerateBill generateBill;
    private ReadDataMock readDataMock;

    @BeforeEach
    void setUp() {
        generateBill = execute(GenerateBill::new);
        readDataMock = new ReadDataMock();
        generateBill.setReadBillData(readDataMock);
    }


    @Test
    public void testButtonClick() {
        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));
    }

    @Test
    public void testActionPerformedWithBillFound() {
        // Adding mock data for bill
        readDataMock.writeIntoFileInfo("1001", "January", "100", "$50");

        generateBill.getC1().select("1001");
        generateBill.getC2().select("January");

        generateBill.actionPerformed(null);

        String expectedText = "\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF January ,2018\n\n\n" +
                "Meter Number: 1001\n" +
                "Month: January\n" +
                "Units Consumed: 100\n" +
                "Total Charges: $50\n" +
                "---------------------------------------------------------------\n";

        assertEquals(expectedText, generateBill.getT1().getText());
    }

    @Test
    public void testActionPerformedWithNoBillFound() {
        // No need to add mock data, as the mock is initially empty
        generateBill.getC1().select("1001");
        generateBill.getC2().select("January");

        generateBill.actionPerformed(null);

        assertEquals("No bill found for the selected criteria.", generateBill.getT1().getText());
    }
}

