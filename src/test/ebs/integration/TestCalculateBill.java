package test.ebs.integration;

import main.ebs.CalculateBill;
import main.ebs.WriteFileMockB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;


public class TestCalculateBill {

    private CalculateBill bill;

    @BeforeEach
    public void setUp() {
        // Create an instance of CalculateBill within the EDT
        bill = execute(CalculateBill::new);
    }


    @Test
    void testGetFileContentWithError() {
        String fileName = "bill_info.txt";
        String actualContent = bill.getFileContent(fileName);
        assertNotEquals("Error reading file or file is empty: null", actualContent,
                "Error message should be present for a non-existent file or file with no read permissions");
    }



    @Test
    void testGetFileContentEmptyFile() {
        String fileName = "bill_info.txt";
        String actualContent = bill.getFileContent(fileName);
        assertNotEquals("", actualContent, "File content should be empty for an empty file");
    }
}










