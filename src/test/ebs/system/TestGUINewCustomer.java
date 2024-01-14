package test.ebs.system;

import main.ebs.*;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import org.junit.jupiter.api.Test;


public class TestGUINewCustomer {


    private FrameFixture frame;

    @BeforeEach
    public void setUp() throws IOException {
        NewCustomer newCustomer = new NewCustomer();
        frame = new FrameFixture( newCustomer);
    }


    @AfterEach
    public void tearDown() {
        frame.cleanUp();
    }

    @Test
    void testLabels(){
        frame.show();
        frame.requireVisible();

        frame.label("l1").requireText("Name");
        frame.label("l2").requireText("Meter No");
        frame.label("l3").requireText("Address");
        frame.label("l4").requireText("State");
        frame.label("l5").requireText("City");
        frame.label("l6").requireText("Email");
        frame.label("l7").requireText("Phone Number");
    }

    @Test
    void testButtons(){
        frame.show();
        frame.requireVisible();

        frame.button("b1").requireText("Submit");
        frame.button("b2").requireText("Cancel");
    }

    @Test
    void testTextFiled(){
        frame.show();
        frame.requireVisible();

        frame.textBox("t1").requireEditable();
        frame.textBox("t2").requireEditable();
        frame.textBox("t3").requireEditable();
        frame.textBox("t4").requireEditable();
        frame.textBox("t5").requireEditable();
        frame.textBox("t6").requireEditable();
        frame.textBox("t7").requireEditable();
    }



    @Test
    public void testEmptyFieldsWarning() {
        frame.show();
        frame.requireVisible();
        // Click the submit button without entering any information
        frame.textBox("t1").enterText("A");
        frame.button("b1").click();

        // Assert that the empty fields warning dialog appears
        frame.optionPane().requireMessage("Please fill in all required fields");
    }



}

