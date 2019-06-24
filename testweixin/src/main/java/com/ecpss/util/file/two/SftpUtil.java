package com.ecpss.util.file.two;

import ch.qos.logback.core.util.FileUtil;
import com.ecpss.util.file.SFTPChannel;
import com.jcraft.jsch.*;
import org.apache.logging.log4j.*;

import java.io.*;
import java.util.Properties;
import java.util.logging.LogManager;

/**
 * Created by xc on 2019/6/18.
 */
public class SftpUtil {
    private org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(SFTPChannel.class);
    
    public static final String NO_FILE = "No such file";
    
    private ChannelSftp sftp = null;
    
    private Session sshSession = null;
    
    private String username;
    
    private String password;
    
    private String host;
    
    private int port;
    
    public SftpUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }
    
    /**
     * 连接sftp服务器 
     * @return ChannelSftp sftp类型 
     * @throws Exception
     */
    public ChannelSftp connect() throws Exception {
        log.info("SftpUtil-->connect--ftp连接开始>>>>>>host=" + host + ">>>port" + port + ">>>username=" + username);
        JSch jsch = new JSch();
        try {
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            log.info("ftp---Session created.");
            sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            log.info("ftp---Session connected.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("Opening Channel.");
            sftp = (ChannelSftp) channel;
            log.info("ftp---Connected to " + host);
        }
        catch (JSchException e) {
            throw new Exception("SftpUtil-->connect异常" + e.getMessage());
        }
        return sftp;
    }
    
    /**
     * 载单个文件 
     * @param directory       ：远程下载目录(以路径符号结束) 
     * @param remoteFileName  FTP服务器文件名称 如：xxx.txt ||xxx.txt.zip 
     * @param localFile       本地文件路径 如 D:\\xxx.txt 
     * @return
     * @throws Exception
     */
    public File downloadFile(String directory, String remoteFileName,String localFile) throws Exception {
        log.info(">>>>>>>>SftpUtil-->downloadFile--sftp下载文件"+remoteFileName+"开始>>>>>>>>>>>>>");
        connect();
        File file = null;
        OutputStream output = null;
        try {
            file = new File(localFile);
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
            sftp.cd(directory);
            output = new FileOutputStream(file);
            sftp.get(remoteFileName, output);
            log.info("===DownloadFile:" + remoteFileName + " success from sftp.");
        }
        catch (SftpException e) {
            if (e.toString().equals(NO_FILE)) {
                log.info(">>>>>>>>SftpUtil-->downloadFile--sftp下载文件失败" + directory +remoteFileName+ "不存在>>>>>>>>>>>>>");
                throw new Exception("SftpUtil-->downloadFile--sftp下载文件失败" + directory +remoteFileName + "不存在");
            }
            throw new Exception("ftp目录或者文件异常，检查ftp目录和文件" + e.toString());
        }
        catch (FileNotFoundException e) {
            throw new Exception("本地目录异常，请检查" + file.getPath() + e.getMessage());
        }
        catch (IOException e) {
            throw new Exception("创建本地文件失败" + file.getPath() + e.getMessage());
        }
        finally {
            if (output != null) {
                try {
                    output.close();
                }
                catch (IOException e) {
                    throw new Exception("Close stream error."+ e.getMessage());
                }
            }
            disconnect();
        }
        
        log.info(">>>>>>>>SftpUtil-->downloadFile--sftp下载文件结束>>>>>>>>>>>>>");
        return file;
    }
    
    /**
     * 上传单个文件 
     * @param directory      ：远程下载目录(以路径符号结束) 
     * @param uploadFilePath 要上传的文件 如：D:\\test\\xxx.txt 
     * @param fileName       FTP服务器文件名称 如：xxx.txt ||xxx.txt.zip 
     * @throws Exception
     */
    public void uploadFile(String directory, String uploadFilePath, String fileName)
            throws Exception {
        log.info(">>>>>>>>SftpUtil-->uploadFile--ftp上传文件开始>>>>>>>>>>>>>");
        FileInputStream in = null;
        connect();
        try {
            sftp.cd(directory);
        }
        catch (SftpException e) {
            try {
                sftp.mkdir(directory);
                sftp.cd(directory);
            }
            catch (SftpException e1) {
                throw new Exception("sftp创建文件路径失败，路径为" + directory);
            }
            
        }
        File file = new File(uploadFilePath);
        try {
            in = new FileInputStream(file);
            sftp.put(in, fileName);
        }
        catch (FileNotFoundException e) {
            throw new Exception("文件不存在-->" + uploadFilePath);
        }
        catch (SftpException e) {
            throw new Exception("sftp异常-->" + e.getMessage());
        }
        finally {
            if (in != null){
                try {
                    in.close();
                }
                catch (IOException e) {
                    throw new Exception("Close stream error."+ e.getMessage());
                }
            }
            disconnect();
        }
        log.info(">>>>>>>>SftpUtil-->uploadFile--sftp上传文件结束>>>>>>>>>>>>>");
    }
    
    private synchronized static File certTempEmptyile() {
//        String dirPath = SystemConfig.getProperty("down_settle_file.temp_path");
//        FileUtil.mkDir(dirPath);
//        String newFileName = System.currentTimeMillis() + ".txt";
//        String filePath = dirPath + File.separator + newFileName;
//        File file = new File(filePath);
        return null;
    }
    
    /**
     * 关闭连接 
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                this.sftp = null;
                log.info("sftp is closed already");
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                this.sshSession = null;
                log.info("sshSession is closed already");
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
//        File file=new File("a\\a.txt");
//        file.createNewFile();
        SftpUtil util = new SftpUtil("test", "1234", "192.168.88.128", 22);
        util.uploadFile("/file","/a/a.txt","xf.txt");
    }
    
    
} 