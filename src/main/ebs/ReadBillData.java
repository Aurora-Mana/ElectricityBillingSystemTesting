package main.ebs;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadBillData {
    public String readAndFindBillData(String month, String meterNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("bill_info.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] billData = line.split(", "); // Adjust this based on the actual format in your file

            System.out.println("Checking line: " + line);

            if (billData[0].startsWith("Meter No: " + meterNumber) && billData[1].startsWith("Month: " + month)) {
                System.out.println("Match found!");
                reader.close();
                return line;
            }
        }

        reader.close();
        return "Not found";
    }

}
