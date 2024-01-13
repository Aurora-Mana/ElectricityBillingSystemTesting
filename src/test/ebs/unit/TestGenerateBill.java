package test.ebs.unit;

import main.ebs.GenerateBill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

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



}
