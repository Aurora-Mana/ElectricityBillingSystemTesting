package main.ebs;

import java.io.*;

public class ReadData {

    public boolean readUserData(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(" ");
            if (userData.length >= 2 && userData[0].equals(username) && userData[1].equals(password)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

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
