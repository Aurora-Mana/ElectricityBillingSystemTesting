package test.ebs.unit;

import main.ebs.Login;
import main.ebs.ReadDataMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private Login login;

    @BeforeEach
    void Setup() {
        login = execute(Login::new);
    }

    @Test
    void loginProcessTest() {
        // Inserting info in the text fields, that we know inside the user_info.txt file
        login.getTf1().setText("Admin");
        login.getPf2().setText("12345678");
        // Initiate button click anf assert if the window is still open after the button is clicked
        login.getB1().doClick();
        assertFalse(login.isVisible());

    }

    @Test
    void loginProcessTestMock() {
        // Create the mock data reader and insert info
        ReadDataMock readUserDataMock = new ReadDataMock();
        readUserDataMock.addInfo("Admin", "12345678");
        login.setReadUserData(readUserDataMock);

        // Insert info in the text fields
        login.getTf1().setText("Admin");
        login.getPf2().setText("12345678");

        login.getB1().doClick();
        assertFalse(login.isVisible());

    }

    @Test
    void userNotFoundInTheFile() {
        // Inserting info in the text fields, that we know is not located in the user_info.txt
        login.setShowMessageDialogs(false);
        login.getTf1().setText("User");
        login.getPf2().setText("12345678");
        // Initiate button click anf assert if the window is still open after the button is clicked
        login.getB1().doClick();
        assertTrue(login.isVisible());
        login.setVisible(false);
    }

    @Test
    void userNotFoundInTheFileMock() {
        login.setShowMessageDialogs(false);
        // Create the mock data reader and insert info
        ReadDataMock readUserDataMock = new ReadDataMock();
        readUserDataMock.addInfo("Admin", "12345678");
        login.setReadUserData(readUserDataMock);

        // Insert info in the text fields
        login.getTf1().setText("User");
        login.getPf2().setText("12345678");

        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void textFieldEmpty() {
        login.setShowMessageDialogs(false);
        // Inserting info in the text fields
        login.getTf1().setText("");
        login.getPf2().setText("12345678");
        // Initiate button click anf assert if the window is still open after the button is clicked
        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void textFieldEmptyMock() {
        login.setShowMessageDialogs(false);
        // Create the mock data reader and insert info
        ReadDataMock readUserDataMock = new ReadDataMock();
        readUserDataMock.addInfo("Admin", "12345678");
        login.setReadUserData(readUserDataMock);

        // Insert info in the text fields
        login.getTf1().setText("");
        login.getPf2().setText("12345678");

        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void passwordFieldEmpty() {
        login.setShowMessageDialogs(false);
        // Inserting info in the text field
        login.getTf1().setText("Admin");
        login.getPf2().setText("");
        // Initiate button click anf assert if the window is still open after the button is clicked
        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void passwordFieldEmptyMock() {
        login.setShowMessageDialogs(false);
        // Create the mock data reader and insert info
        ReadDataMock readUserDataMock = new ReadDataMock();
        readUserDataMock.addInfo("Admin", "12345678");
        login.setReadUserData(readUserDataMock);

        // Insert info in the text fields
        login.getTf1().setText("Admin");
        login.getPf2().setText("");

        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void bothFieldsAreEmpty() {
        login.setShowMessageDialogs(false);
        login.getTf1().setText("");
        login.getPf2().setText("");
        // Initiate button click anf assert if the window is still open after the button is clicked
        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void bothFieldsAreEmptyMock() {
        login.setShowMessageDialogs(false);
        // Create the mock data reader and insert info
        ReadDataMock readUserDataMock = new ReadDataMock();
        readUserDataMock.addInfo("Admin", "12345678");
        login.setReadUserData(readUserDataMock);

        login.getTf1().setText("");
        login.getPf2().setText("");

        login.getB1().doClick();
        assertTrue(login.isVisible());

    }

    @Test
    void cancelButtonTest() {
        login.getB2().doClick();
        assertFalse(login.isVisible());
    }

    @Test
    void invalidLoginActionMessage(){
        assertTrue(login.getShowMessageDialogs());
    }
}