package test.ebs;
import main.ebs.GenerateBill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.ActionEvent;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;

public class TestGenerateBill {
    private GenerateBill generateBill;

    @BeforeEach
    void setUp() {
        generateBill = execute(GenerateBill::new);
    }

    @Test
    public void testGUIInitialization() {
        assertNotNull(generateBill.getB1());
        assertNotNull(generateBill.getC1());
        assertNotNull(generateBill.getC2());
    }


    @Test
    public void testButtonClick() {
        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));
    }

    @Test
    public void testFileReadingDataPopulation() {

        generateBill.getC1().select("1001");
        generateBill.getC2().select("January");

        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));
        assertNotNull(generateBill.t1.getText());

    }

    /*i think this is done with mocking
    as well as checking invalid data and whatnot
    @Test
    public void testSuccessfulBillRetrieval() {
        GenerateBill generateBill = new GenerateBill();
        generateBill.getC1().select("1001");
        generateBill.getC2().select("January");

        ActionEvent actionEvent = new ActionEvent(generateBill.getB1(), ActionEvent.ACTION_PERFORMED, null);
        assertDoesNotThrow(() -> generateBill.actionPerformed(actionEvent));

        String expectedText = "\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF January ,2018\n\n\n" +
                "Meter Number: 1001\nMonth: January\nUnits Consumed: [units value]\nTotal Charges: [charges value]\n" +
                "---------------------------------------------------------------\n";

        assertEquals(expectedText, generateBill.getT1().getText());
    }

     */




}
