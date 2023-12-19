package main.ebs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCustomerData {

    public void readCustomerData(String[][] customerData) throws IOException {
        int i = 0;
        int j=0;
        try (BufferedReader reader = new BufferedReader(new FileReader("customer_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(", ");

                for (String str : data) {
                    if (j < 8) { // Check if j is within bounds
                        String[] parts = str.split("\\s+");
                        String lastWord = parts[parts.length - 1];
                        customerData[i][j++] = lastWord;
                    }
                }

                i++;
                j = 0;
            }
        }
    }
}
