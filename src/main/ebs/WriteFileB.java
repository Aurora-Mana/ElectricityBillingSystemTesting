package main.ebs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileB{
public void writeBillData(int a, int b, String c, int p3)  throws IOException {
        // Writing data to a file named "bill_info.txt"
        BufferedWriter writer = new BufferedWriter(new FileWriter("bill_info.txt", true));
        // Meter number for this month will the value calculated in p3
        writer.write("Meter No: " + a + ", Month: " + c + ", Units Consumed: " + b + ", Total Charges: " + p3);
        writer.newLine();
        writer.close();
        }
    }
