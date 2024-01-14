package test.ebs.system.java.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import main.ebs.Login;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepDefinitions {

    public static class LoginMock extends Login {

        private boolean isInvalidLoginActionCalled;

        public LoginMock() {
            super();
            isInvalidLoginActionCalled = false;
        }
        @Override
        public void invalidLoginAction() {
            isInvalidLoginActionCalled = true;
        }

        public boolean getIsInvalidLoginActionCalled() { return isInvalidLoginActionCalled; }
    }

    private LoginMock login;

    private static final String validUsername = "Admin";
    private static final String validPassword = "12345678";


    @Given("the login page is open")
    public void theLoginPageIsOpen() {
        login = new LoginMock();
        login.setShowMessageDialogs(false); // To prevent pop-up dialogs during testing
    }

    @When("user enters valid credentials and clicks on login")
    public void userEntersValidCredentialsAndClickOnLogin() {
        // Set valid credentials
        login.getTf1().setText(validUsername);
        login.getPf2().setText(validPassword);

        // Trigger the login button
        login.getB1().doClick();
    }

    @Then("the main project page should be visible")
    public void theMainProjectPageShouldBeVisible() {
        // Check if the project window is visible
        assertTrue(login.getProject().isVisible());
    }

    @When("user enters invalid credentials and clicks on login")
    public void userEntersInvalidCredentialsAndClickOnLogin() {
        // Set invalid credentials
        login.getTf1().setText("invalidUser");
        login.getPf2().setText("invalidPassword");

        // Trigger the login button
        login.getB1().doClick();
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        // Check if an error dialog is shown
        assertTrue(login.getIsInvalidLoginActionCalled());
    }
}
