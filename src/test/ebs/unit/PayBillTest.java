package test.ebs.unit;

import main.ebs.PayBill;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class PayBillTest {
    public static class MockEditorPane extends JEditorPane {
        private URL lastSetPageUrl;

        @Override
        public void setPage(URL url) throws IOException {
            //Simulate the behavior of setPage and store the last URL set
            this.lastSetPageUrl = url;
        }
    }

    public static class MockFailingEditorPane extends JEditorPane {
       @Override
        public void setPage(URL url) throws IOException {
           //Simulate IOException
           throw new IOException("Simulated IOException.");
       }
    }

    @Test
    public void testLoadPageSuccess() throws IOException {
        MockEditorPane mockEditorPane = new MockEditorPane();
        PayBill payBill = new PayBill(mockEditorPane);
        payBill.setVisible(false);

        payBill.loadPage(URI.create("https://www.google.com").toURL());

        //Verify that setPage was called with the correct URL
        assertEquals(URI.create("https://www.google.com").toURL(), mockEditorPane.lastSetPageUrl);
    }

    @Test
    public void testLoadPageFailure() throws IOException {
        MockFailingEditorPane mockFailingEditorPane = new MockFailingEditorPane();
        PayBill payBill = new PayBill(mockFailingEditorPane);
        payBill.setVisible(false);

        //Verify that an exception is thrown when supplied an invalid url
        assertThrows(IllegalArgumentException.class, () -> payBill.loadPage(URI.create("invalid url").toURL()));

        //Verify the content type after the exception was thrown
        assertEquals("text/html", payBill.getEditorPane().getContentType());

    }

    @Test
    public void testInitialization() {
        //Test constructor for the initialization
        PayBill payBill = new PayBill();
        assertNotNull(payBill.getContentPane());
        assertNotNull(payBill.getComponent(0)); //JScrollPane
        assertNotNull(payBill.getEditorPane());
    }

    @Test
    public void testConstructorWithEditorPane() {
        //Test constructor with injected JEditorPane
        MockEditorPane mockEditorPane = new MockEditorPane();
        PayBill payBill = new PayBill(mockEditorPane);

        //Verify that JEditorPane is properly set
        assertEquals(mockEditorPane, payBill.getEditorPane());
    }

    @Test
    public void testMainMethod() {
        //Test main method to ensure it doesn't throw exceptions
        assertDoesNotThrow(() -> PayBill.main(new String[0]));
    }
}
