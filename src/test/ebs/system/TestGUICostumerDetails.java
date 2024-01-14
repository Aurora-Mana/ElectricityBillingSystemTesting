package test.ebs.system;

import main.ebs.*;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGUICostumerDetails {


        CustomerDetails customerDetails;
        private  FrameFixture frame_CD;

        ReadDataMock readCustomerDataMock = new ReadDataMock();


        @BeforeAll
        public static void setUpOnce() {
            FailOnThreadViolationRepaintManager.install();
        }

        @BeforeEach
        public void setUp() {
            customerDetails = GuiActionRunner.execute(() -> {
                CustomerDetails cd = new CustomerDetails();
                cd.setReadD(readCustomerDataMock);
                return cd;
            });
        }

        @AfterEach
        void afterEach() {
            frame_CD.cleanUp();
        }
    //GUI TESTING CUSTOMER DETAILS


    @Test
    void testTableContents() {
        frame_CD = new FrameFixture(customerDetails);
        frame_CD.show();


        // Access the JTable component
        JTableFixture table = frame_CD.table("t1");

        // Retrieve actual data from the table
        String[][] actualData = table.contents();

        // Compare actual data with expected data
        // For example, assert actualData contains the expected customer details
        assertEquals("cus1", actualData[0][0]);
        assertEquals("160", actualData[0][1]);

    }

    @Test
    void testColumnNames() {
        frame_CD = new FrameFixture(customerDetails);
        frame_CD.show();

        // Access the JTable component
        JTableFixture table = frame_CD.table("t1");


        // Retrieve the JTable
        JTable jTable = table.target();

        // Get the number of columns
        int actualColumnCount = jTable.getColumnCount();

        //check if num of columns match
        assertEquals(7, actualColumnCount);

        // Create an array to store column names
        String[] actualColumnNames = new String[actualColumnCount];

        // Iterate over columns to get names
        for (int i = 0; i < actualColumnCount; i++) {
            actualColumnNames[i] = jTable.getColumnName(i);
        }

        // Compare actual column names with expected column names
        String[] expectedColumnNames = {"Emp Name", "Meter No", "Address", "State", "City", "Email", "Phone"};
        assertArrayEquals(expectedColumnNames, actualColumnNames);
    }

    @Test
    void testPrintButton() {
        frame_CD = new FrameFixture(customerDetails);
        frame_CD.show();

        // Simulate a button click
        frame_CD.button("b1").click();
        // Verify that the t1.print() method is called

    }


}
