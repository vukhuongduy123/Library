package FTP;

import java.io.IOException;
 
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
 
public class FTPSample {
    private static final String FTP_SERVER_ADDRESS = "192.168.1.11";
    private static final int FTP_SERVER_PORT_NUMBER = 21;
    private static final int FTP_TIMEOUT = 6000;
    private static final int BUFFER_SIZE = 1024 * 1024 * 1;
    private static final String FTP_USERNAME = "FTP-User";
    private static final String FTP_PASSWORD = "Ditmemay@123";
    private String status;
    private FTPClient ftpClient;
    private String[] listFile;
 
    /**
     * main
     * 
     * @param args
     */
    // public static void main(String[] args) {
    //     FTPSample ftpSample = new FTPSample();
    //     ftpSample.connectFTPServer();
    // }
    
    /**
     * connect ftp server
     * 
     * @author viettuts.vn
     */
    public void connectFTPServer() {
        ftpClient = new FTPClient();
        try {
            // System.out.println("connecting ftp server...");
            // connect to ftp server
            status = "Something go wrong !";
            ftpClient.setDefaultTimeout(FTP_TIMEOUT);
            ftpClient.connect(FTP_SERVER_ADDRESS, FTP_SERVER_PORT_NUMBER);
            // run the passive mode command
            ftpClient.enterLocalPassiveMode();
            // check reply code
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                disconnectFTPServer();
                status = "FTP server not respond!";
                
            } else {
                ftpClient.setSoTimeout(FTP_TIMEOUT);
                // login ftp server
                if (!ftpClient.login(FTP_USERNAME, FTP_PASSWORD)) {
                    status = "Username or password is incorrect!";
                    
                }
                ftpClient.setDataTimeout(FTP_TIMEOUT);
                status = "connected";
    
                listFile = ftpClient.listNames();
                    printNames(listFile);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * disconnect ftp server
     * 
     * @author viettuts.vn
     */


    public String getStatus(){
        return status;
    }

    public String [] getListFileFTP(){
        return listFile;
    }
    private void disconnectFTPServer() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static void printNames(String files[]) {
        if (files != null && files.length > 0) {
            for (String aFile: files) {
                System.out.println(aFile);
            }
        }
    }

}