package test.ebs.system;

import main.ebs.*;
import org.assertj.swing.edt.GuiActionRunner;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;


public class TestGUILogin {

    Login login_user;

    private FrameFixture frame;

    @BeforeEach
    public void setUp() {
        login_user = GuiActionRunner.execute(Login::new);
        frame= new FrameFixture(login_user);
    }

    @AfterEach
    void afterEach() {
        frame.cleanUp();
    }

    //GUI TESTING FOR CALCULATE BILL CLASS

    @Test
    void testLabels_Button_TextFields(){
        frame.show(); // Display the frame for testing
        frame.requireVisible(); // Ensure that the frame is visible

        frame.label("l1").requireText("User Name");
        frame.label("l2").requireText("Password");
        frame.button("b1").requireText("Login");
        frame.button("b2").requireText("Cancel");
    }



    @Test
    void testLoginAndNavigateToHomePage() throws IOException {
        frame.show(); // Display the frame for testing
        frame.requireVisible(); // Ensure that the frame is visible

        frame.maximize();

        // Simulate user input
        frame.textBox("tf1").enterText("Admin");
        frame.textBox("pf2").enterText("12345678");

        // Click the Login button
        frame.button("b1").click();

        //assert that we are no longer in login window
        frame.requireNotVisible();
    }


    @Test
        public void testInvalidLogin() {
            frame.show(); // Display the frame for testing
            frame.requireVisible(); // Ensure that the frame is visible

            frame.maximize();

            // Simulate user input
            frame.textBox("tf1").enterText("invalidUser");
            frame.textBox("pf2").enterText("invalidPassword");

            // Click the Login button
            frame.button("b1").click();

            frame.optionPane().requireMessage("Invalid Login");
        }

        @Test
        public void testCancelButton() {
            frame.show(); // Display the frame for testing
            frame.requireVisible(); // Ensure that the frame is visible
            // Click the Cancel button
            frame.button("b2").click();

            // Perform assertions after clicking Cancel button
            // Example: Assert that the Login frame is no longer visible
            frame.requireNotVisible();
        }



    }

