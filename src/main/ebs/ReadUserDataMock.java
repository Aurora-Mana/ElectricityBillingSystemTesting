package main.ebs;

public class ReadUserDataMock extends ReadUserData{
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
}
