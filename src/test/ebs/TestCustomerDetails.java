package test.ebs;
import main.ebs.CustomerDetails;
import main.ebs.ReadCustomerDataMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TestCustomerDetails {
    private CustomerDetails customerDetails;

    @BeforeEach
    void Setup() {
        customerDetails = execute(CustomerDetails::new);
    }

    @Test
    void testEmptyFile() throws IOException {
        customerDetails.readDataFromFile();
        assertNotEquals(0, customerDetails.getCustomerData().length, "CustomerData should be empty for an empty file");
    }


    @Test
    void testJTableCreation() {
        assertNotNull(customerDetails.getT1());
    }

    @Test
    void testColumnHeaders() {
        CustomerDetails customerDetails = new CustomerDetails();
        String[] expectedHeaders = {"Emp Name", "Meter No", "Address", "State", "City", "Email", "Phone"};
        assertArrayEquals(expectedHeaders, customerDetails.getColName());
    }

    @Test
    void testReadDataFromFileValidData() throws IOException {
        customerDetails.readDataFromFile();
        assertNotNull(customerDetails.getCustomerData());
    }

    @Test
    void testReadDataFromFileValidDataMock() throws IOException {
        ReadCustomerDataMock readCustomerDataMock = new ReadCustomerDataMock();
        readCustomerDataMock.writeIntoFileInfo("John", "1001", "Address1", "State1","City1", "johnnnyjohn@email.com","123456789");
        readCustomerDataMock.writeIntoFileInfo("Emily", "1001", "Address2", "State2","City2", "emmy@email.com","987654321");
        // Insert individuals into the string that we will use for mocking
        customerDetails.setReadD(readCustomerDataMock); // Set the reader to the mock reader
        customerDetails.readDataFromFile();
        System.out.println(customerDetails.getCustomerData());
        String[][] customerData = customerDetails.getCustomerData();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.println(customerData[i][j]);
            }
        }
        assertNotNull(customerDetails.getCustomerData());
    }

    @Test
    public void testReadDataFromFile_EmptyRowCells() throws IOException {

        customerDetails.readDataFromFile();

        String[][] customerData = customerDetails.getCustomerData();

        for(int i=0; i<2; i++){
            assertFalse(customerData[i][0].isEmpty());
            assertFalse(customerData[i][1].isEmpty());
            assertFalse(customerData[i][2].isEmpty());
            assertFalse(customerData[i][3].isEmpty());
            assertFalse(customerData[i][4].isEmpty());
            assertFalse(customerData[i][5].isEmpty());
            assertFalse(customerData[i][6].isEmpty());
        }
    }

    @Test
    public void testReadDataFromFile_EmptyRowCellsMock() throws IOException{
        ReadCustomerDataMock readCustomerDataMock = new ReadCustomerDataMock();
        // Prepare the mock data
        readCustomerDataMock.writeIntoFileInfo("John", "1234", "Address1", "State1", "City1", "john@example.com", "1234567890");
        readCustomerDataMock.writeIntoFileInfo("Alice", "5678", "Address2", "State2", "City2", "alice@example.com", "9876543210");

        customerDetails.setReadD(readCustomerDataMock);
        customerDetails.readDataFromFile();
        String[][] customerData = customerDetails.getCustomerData();

            // Assert that cells in the retrieved data are not empty
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 7; j++) {
                    assertNotNull(customerData[i][j]);
                }
            }

    }
    /* ndosha hyn me shume ne pune te newcustomer
    @Test
    public void testFileCorrectFormat(){
        try {
            customerDetails.readDataFromFile();
        } catch (IOException e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }

        for (String[] row : customerDetails.getCustomerData()) {
            //Assert first cell contains letter for name
                assertTrue(row[0].matches("[a-zA-Z]+"), "Invalid format for the first cell");

                // Assert that the second cell is a number
                assertTrue(row[1].matches("\\d+"), "Invalid format for the second cell");

                // Assert that the third cell is a number or letter for address
                assertTrue(row[2].matches("[a-zA-Z\\d]+"), "Invalid format for the third cell");

                // Assert that the fourth cell contains letter for state
                assertTrue(row[3].matches("[a-zA-Z]+"), "Invalid format for the 4th cell");

               // Assert that the fifth cell contains letter for city
               assertTrue(row[4].matches("[a-zA-Z]+"), "Invalid format for the 5th cell");

               // Assert that the sixth cell contain necessary parts for email
               assertTrue(row[5].matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"), "Invalid format for the 6th cell");

              // Assert that the seventh cell contains numbers for cellphone
              assertTrue(row[4].matches("\\d+"), "Invalid format for the 7th cell");
        }
    }

     */



}









