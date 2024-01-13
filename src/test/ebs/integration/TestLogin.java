package test.ebs.integration;

import main.ebs.Login;
import main.ebs.Project;
import main.ebs.ReadDataMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin {

    ReadDataMock readUserDataMock =  new ReadDataMock();
    Login login =  new Login();
    @Test
    void successfulLogIn(){
        login.setReadUserData(readUserDataMock);
        readUserDataMock.addInfo("User_1", "userTest");

        login.getTf1().setText("User_1");
        login.getPf2().setText("userTest");

        // Trigger login action
        login.getB1().doClick();

        assertFalse(login.isVisible());
        Project project = new Project();
        assertTrue(project.isVisible());
    }
}
