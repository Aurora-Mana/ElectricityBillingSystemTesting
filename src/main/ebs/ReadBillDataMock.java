package main.ebs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadBillDataMock extends ReadBillData {
    private String fileInfo="";

    @Override
    public String readAndFindBillData(String month, String meterNumber) throws IOException {
        String[] lines = fileInfo.split("\n");
        for (String line : lines) {
            System.out.println("Checking line: " + line);
            String[] billData = line.split(", "); // Adjust this based on the actual format in your file
            // Check if billData contains necessary information
            if (billData.length >= 4 &&
                    billData[0].startsWith("Meter No: " + meterNumber) &&
                    billData[1].startsWith("Month: " + month)) {
                System.out.println("Match found!");
                System.out.println(line);
                return line;
            }
        }
        return "Not found";
    }

    public String getFileInfo() {
        return fileInfo;
    }
    public void setFileInfo(String
fileInfo) {
        this.fileInfo = fileInfo;
    }

    public void writeIntoFileInfo(String meterNo, String month, String unitsCon, String total){
        fileInfo= fileInfo + "Meter No: " + meterNo +", Month: " + month + ", Units Consumed:" + unitsCon + ", Total Charges: " + total + "\n";
    }
}
