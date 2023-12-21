package main.ebs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadUserData {
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
}
