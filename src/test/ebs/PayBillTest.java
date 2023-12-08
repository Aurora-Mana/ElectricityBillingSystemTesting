package test.ebs;

import main.ebs.PayBill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PayBillTest {
    @Test
    void testPayBill() {
        assertDoesNotThrow(() -> new PayBill().setVisible(true));
    }
}
