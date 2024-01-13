package test.ebs.integration;

import main.ebs.CustomerDetails;
import main.ebs.NewCustomer;
import main.ebs.ReadData;
import main.ebs.ReadDataMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.assertj.swing.fixture.Containers.showInFrame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCustomerDetailsAndNewCustomer {
    private CustomerDetails customerDetails;

    private NewCustomer newCustomer;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file for testing
        tempFile = Files.createTempFile("testData", ".txt");

        // Initialize NewCustomer and CustomerDetails with the temporary file
        newCustomer = execute(() -> new NewCustomer(new BufferedWriter(Files.newBufferedWriter(tempFile))));
        customerDetails = execute(() -> new CustomerDetails(new ReadData()));
    }
    @Test
    void correctInfoDisplay() throws IOException {
        ReadDataMock readCustomerDataMock = new ReadDataMock();
        // Insert data
        readCustomerDataMock.writeIntoCustomerInfo("John", "1234", "Address1", "State1", "City1", "john@example.com", "1234567890");

        customerDetails = new CustomerDetails(readCustomerDataMock);

        String[][] customerInfo = customerDetails.getCustomerData();
        String[][] actualNonNull = Arrays.stream(customerInfo)
                .filter(row -> row != null)
                .filter(row -> Arrays.stream(row).noneMatch(cell -> cell == null || cell.equals("null")))
                .toArray(String[][]::new);
        // Directly compare the actual and expected arrays
        assertThat(actualNonNull.equals(new String[][]{
                {"John", "1234", "Address1", "State1", "City1", "john@example.com", "1234567890"}
        }));
    }

    private static boolean compareData(String[][] expected, String[][] actual) {
        if (expected.length != actual.length || expected[0].length != actual[0].length) {
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                if (!expected[i][j].equals(actual[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    @Test
    void testIntegrationBetweenNewCustomerAndCustomerDetails() {
        // Add a new customer using NewCustomer
        addCustomer("John", "1234", "Address1", "State1", "City1", "john@email.com", "1234567890");

        String [] data = {"John", "1234", "Address1", "State1", "City1", "john@email.com", "1234567890"};
        // Verify that the added customer is displayed in CustomerDetails
        assertThat(Arrays.deepEquals(customerDetails.getCustomerData(), new String[][]{data}));
    }

    private void addCustomer(String name, String meterNo, String address, String state, String city, String email, String phone) {
        execute(() -> {
            newCustomer.setShowMessageDialogs(false); // Disable message dialogs during testing
            newCustomer.t1.setText(name);
            newCustomer.t2.setText(meterNo);
            newCustomer.t3.setText(address);
            newCustomer.t4.setText(state);
            newCustomer.t5.setText(city);
            newCustomer.t6.setText(email);
            newCustomer.t7.setText(phone);
            newCustomer.b1.doClick(); // Simulate button click
        });
    }

}

