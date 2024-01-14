package test.ebs.system;

import main.ebs.*;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.assertj.swing.core.Robot;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.finder.WindowFinder.findDialog;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class TestGUINewCustomer {

    /*
    private FrameFixture frame;
    private NewCustomer newCustomer;
    @BeforeEach
    public void setUp() {
        // Launch the application in the EDT (Event Dispatch Thread)
        application(NewCustomerLauncher.class).start();

        // Retrieve the NewCustomer frame
        frame = findFrame(NewCustomer.class).using(robot());
    }

    private Robot robot() {
        return frame.robot();
    }

    @AfterEach
    public void tearDown() {
        frame.cleanUp();
    }

    @Test
    void testLabels_Button(){
        frame.show();
        frame.requireVisible();

        frame.button();
    }

    @Test
    public void testCancelButton() {
        // Input some information
        inputValidCustomerInfo();

        // Click the "Cancel" button
        frame.button(JButtonMatcher.withText("Cancel")).click();

        // Assert that the frame is closed
        frame.requireNotVisible();

        // Verify that no customer information is written to the file (customize based on your application logic)
        assertThat(getCustomerInfoFromFile()).doesNotContain("Name:");
    }

    @Test
    public void testEmptyFields() {
        // Leave some or all text fields empty
        // ...

        // Click the "Submit" button
        // ...

        // Assert error message
        // ...

        // Verify that no customer information is written to the file
        // ...
    }


    // Launcher class for the NewCustomer frame
    public static class NewCustomerLauncher extends JFrame {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new NewCustomer().setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

     */


}

