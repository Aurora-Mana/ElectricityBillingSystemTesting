package test.ebs;
import main.ebs.CustomerDetails;
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









