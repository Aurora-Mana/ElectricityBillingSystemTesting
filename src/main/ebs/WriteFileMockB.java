package main.ebs;

public class WriteFileMockB extends WriteFileB {
    private StringBuilder fileInfo = new StringBuilder();
    private StringBuilder userInfo = new StringBuilder();


    @Override
    public void writeBillData(int a, int b, String c, int p3) {
        fileInfo.append("Meter No: ").append(a)
                .append(", Month: ").append(c)
                .append(", Units Consumed: ").append(b)
                .append(", Total Charges: ").append(p3)
                .append("\n");
    }

    public String getFileInfo() {
        return fileInfo.toString();
    }

    @Override
    public void writeUserData(String data) {
        userInfo.append(data);
    }

    public StringBuilder getUserInfo() {
        return userInfo;
    }
}