package test.ebs.system;

import main.ebs.*;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestGUIProject {

    private FrameFixture frame;
    Project project;



    @BeforeEach
    public void setUp() {
        project = GuiActionRunner.execute(new GuiQuery<Project>() {
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
    }

    @AfterEach
    public void tearDown() {
        frame.cleanUp();
    }

    @Test
    void testMasterMenu(){
        frame.show();
        frame.requireVisible();

        frame.menuItem("master").isEnabled();
        frame.menuItem("m1").isEnabled();
        frame.menuItem("m2").isEnabled();

        // Check that when menu item m1/New Customer is clicked
        // the user goes to the new customer panel
        frame.menuItem("m1").click();
        assertTrue(project.isNewCustomerPanelVisible());

        // Check that when menu item m2/Customer Details is clicked
        // the user goes to the customer details panel
        frame.menuItem("m2").click();
        assertTrue(project.isCustomerDetailsPanelVisible());
    }


    @Test
    void testUserMenu(){
        frame.show();
        frame.requireVisible();

        frame.menuItem("user").isEnabled();
        frame.menuItem("u1").isEnabled();
        frame.menuItem("u2").isEnabled();
        frame.menuItem("u3").isEnabled();

        // Check that when menu item u1 is clicked
        // the user goes to the pay bill window
        frame.menuItem("u1").click();
        assertTrue(project.isPayBillVisible());

        // Check that when menu item u2  is clicked
        // the user goes to the calculate bill window
        frame.menuItem("u2").click();
        assertTrue(project.isCalculatedBillPanelVisible());

        // Check that when menu item u3 is clicked
        // the user goes to the last bill window
        frame.menuItem("u3").click();
        assertTrue(project.isLastBillPanelVisible());

    }


    @Test
    void testReportMenu(){
        frame.show();
        frame.requireVisible();

        frame.menuItem("report").isEnabled();
        frame.menuItem("r1").isEnabled();

        //check that when u click r1 class Generate bill is displayed
        frame.menuItem("r1").click();
        assertTrue(project.isGeneratedBillPanelVisible());

    }

    @Test
    void testUtilityMenu(){
        frame.show();
        frame.requireVisible();

        frame.menuItem("utility").isEnabled();
        frame.menuItem("u1").isEnabled();
        frame.menuItem("u2").isEnabled();
        frame.menuItem("u3").isEnabled();

    }

    @Test
    void testExitMenu(){
        frame.show();
        frame.requireVisible();

        frame.menuItem("exit").isEnabled();
        frame.menuItem("ex").isEnabled();

    }




}
