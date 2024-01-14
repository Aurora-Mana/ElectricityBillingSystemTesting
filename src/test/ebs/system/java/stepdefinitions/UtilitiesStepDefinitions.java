package test.ebs.system.java.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.ebs.Project;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilitiesStepDefinitions {

    private Project project;

    @Given("the EBS app is open")
    public void theEBSAppIsOpen() {
        project = new Project(null, null, null, null, null, null);
        project.setVisible(true);
    }

    @When("the user clicks on Utility menu")
    public void theUserClicksOnUtilityMenu() {
        project.getUtilityMenu().doClick();
    }

    @And("the user clicks on Notepad")
    public void theUserClicksOnNotepad() {
        project.getUtilityMenu().getItem(0).doClick();
    }
    @And("the user clicks on Calculator")
    public void theUserClicksOnCalculator() {
        project.getUtilityMenu().getItem(1).doClick();
    }
    @And("the user clicks on Web Browser")
    public void theUserClicksOnWebBrowser() {
        project.getUtilityMenu().getItem(2).doClick();
    }

    @Then("Notepad should be executed")
    public void notepadShouldBeExecuted() {
        assertTrue(isProcessRunning("Notepad.exe"));
    }

    @Then("Calculator should be executed")
    public void calculatorShouldBeExecuted() {
        assertTrue(isProcessRunning("calc.exe"));
    }

    @Then("Web Browser should be executed")
    public void webBrowserShouldBeExecuted() {
        assertTrue(isProcessRunning("chrome.exe"));
    }

    private boolean isProcessRunning(String processName) {
        // Get the list of running processes
        List<ProcessHandle> processList = ProcessHandle.allProcesses().toList();

        // Check if the specified process name is in the list
        for (ProcessHandle process : processList) {
            String cmd = process.info().command().orElse("");
            if (cmd.contains(processName)) {
                return true;
            }
        }

        return false;
    }
}
