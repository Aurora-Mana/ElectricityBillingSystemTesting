package main.ebs;

import java.io.IOException;

public class ReadDataMock extends ReadData {

    private String fileInfo = "";
    @Override
    public boolean readUserData(String username, String password){
        if(fileInfo.equals("")){
            return false;
        }
        String[] lines = fileInfo.split("\n");
        for (String line: lines){
            String[] info = line.split(" ");
            if(info[0].equals(username) && info[1].equals(password)){
                return true;
            }
        }
        return false;
    }

    public void addInfo(String username, String password){
        fileInfo += username + " " + password + "\n";
    }

    String fileInfoCustomer;
    @Override
    public void readCustomerData(String[][] customerData){
        int i = 0;
        int j = 0;

        String[] lines = fileInfoCustomer.split("\n+");
        for (int l=0; l<lines.length; l++){
            for (String str: lines){
                if(j < 8){
                    String[] parts = str.split("\\s+");
                    String lastWord = parts[parts.length - 1];
                    customerData[i][j++] = lastWord;
                }
            }
            i++;
            j = 0;
        }
    }

    public void writeIntoFileInfo(String name, String meterNo, String address, String state, String city, String email, String phoneNumber){
        fileInfoCustomer+="Name: " + name + ", Meter No: " + meterNo + ", Address: " + address + ", State: " + state + ", City: " + city + ", Email: " + email + ", Phone Number: " + phoneNumber;;
    }

    private String fileInfoBillData="";

    @Override
    public String readAndFindBillData(String month, String meterNumber) throws IOException {
        String[] lines = fileInfoBillData.split("\n");
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
        return fileInfoBillData;
    }
    public void setFileInfo(String
                                    fileInfo) {
        this.fileInfoBillData = fileInfo;
    }

    public void writeIntoFileInfo(String meterNo, String month, String unitsCon, String total){
        fileInfoBillData= fileInfoBillData + "Meter No: " + meterNo +", Month: " + month + ", Units Consumed:" + unitsCon + ", Total Charges: " + total + "\n";
    }
}
