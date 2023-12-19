package main.ebs;

import java.util.Arrays;

public class ReadCustomerDataMock extends ReadCustomerData{
    String fileInfo;
    @Override
    public void readCustomerData(String[][] customerData){
        int i = 0;
        int j = 0;

        String[] lines = fileInfo.split("\n+");
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
        fileInfo+="Name: " + name + ", Meter No: " + meterNo + ", Address: " + address + ", State: " + state + ", City: " + city + ", Email: " + email + ", Phone Number: " + phoneNumber;;
    }
}
