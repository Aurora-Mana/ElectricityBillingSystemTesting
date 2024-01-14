package test.ebs.system;

import main.ebs.*;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.core.matcher.JTextComponentMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestGUIProject {

    private FrameFixture frame;
    /*

    @BeforeEach
    public void setUp() {
        Project project = GuiActionRunner.execute(new GuiQuery<Project>() {
            protected Project executeInEDT() throws IOException {
                return new Project(
                        new CustomerDetails(),
                        new NewCustomer(),
                        new CalculateBill(),
                        new PayBill(),
                        new GenerateBill(),
                        new LastBill()
                );
            }
        });

        frame = new FrameFixture(project);
        frame.show();
    }

    @AfterEach
    public void tearDown() {
        frame.cleanUp();
    }

    @Test
    public void testCustomerDetailsMenuItem() {
        frame.menuItem("Customer Details").click();
        frame.dialog("CustomerDetails").requireVisible();
    }

    @Test
    public void testNewCustomerMenuItem() {
        frame.menuItem("New Customer").click();
        frame.dialog("NewCustomer").requireVisible();
    }

    @Test
    public void testCalculateBillMenuItem() {
        frame.menuItem("Calculate Bill").click();
        frame.dialog("CalculateBill").requireVisible();
    }

    @Test
    public void testPayBillMenuItem() {
        frame.menuItem("Pay Bill").click();
        frame.dialog("PayBill").requireVisible();
    }

    @Test
    public void testGenerateBillMenuItem() {
        frame.menuItem("Generate Bill").click();
        frame.dialog("GenerateBill").requireVisible();
    }

    @Test
    public void testLastBillMenuItem() {
        frame.menuItem("Last Bill").click();
        frame.dialog("LastBill").requireVisible();
    }

    @Test
    public void testNotepadMenuItem() {
        frame.menuItem("Notepad").click();
        // Add assertions for Notepad, for example, check if it is running
    }

    @Test
    public void testCalculatorMenuItem() {
        frame.menuItem("Calculator").click();
        // Add assertions for Calculator, for example, check if it is running
    }

    @Test
    public void testWebBrowserMenuItem() {
        frame.menuItem("Web Browser").click();
        // Add assertions for Web Browser, for example, check if it is running
    }

    @Test
    public void testExitMenuItem() {
        frame.menuItem("Exit").click();
        frame.requireNotVisible();
    }

     */
}
