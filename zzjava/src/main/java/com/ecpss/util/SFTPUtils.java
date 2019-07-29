package com.ecpss.util;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Vector;


public class SFTPUtils {
    private static Session session = null;
    private static ChannelSftp channel = null;
    
    public static ChannelSftp getConnect() {
        String ftpHost = "127.0.0.1";
        String port = "24";
        String ftpUserName = "xc";
        String ftpPassword = "123456";
        
        //默认的端口22 此处我是定义到常量类中；
        int ftpPort = 22;
        //判断端口号是否为空，如果不为空，则赋值
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }
        JSch jsch = new JSch(); // 创建JSch对象
        // 按照用户名,主机ip,端口获取一个Session对象
        try {
            session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
            if (ftpPassword != null) {
                session.setPassword(ftpPassword); // 设置密码
            }
            String ftpTO="20000";
            if (!(ftpTO==null||"".equals(ftpTO))) {
                int ftpTimeout=Integer.parseInt(ftpTO);
                session.setTimeout(ftpTimeout); // 设置timeout时候
            }
            //并且一旦计算机的密匙发生了变化，就拒绝连接。
            session.setConfig("StrictHostKeyChecking", "no");
            //默认值是 “yes” 此处是由于我们SFTP服务器的DNS解析有问题，则把UseDNS设置为“no”
            session.setConfig("UseDNS", "no");
            session.connect(); // 经由过程Session建树链接
            channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(1500); // 建树SFTP通道的连接
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        return channel;
    }
    
    public static void closeChannel() throws Exception {
        try {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        } catch (Exception e) {
            throw new Exception( "close ftp error.");
        }
    }
    
    /**
     *
     * @param localFile 本地文件
     * @param newName 远程文件名
     * @param remoteFoldPath 远程地址
     * @throws Exception
     */
    public  void uploadFile(String localFile, String newName, String remoteFoldPath) throws Exception{
        InputStream input = null;
        try {
            input = new FileInputStream(new File(localFile));
            // 改变当前路径到指定路径
            channel.cd(remoteFoldPath);
            channel.put(input, newName);
        } catch (Exception e) {
            throw new Exception( "Upload file error.");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new Exception("Close stream error.");
                }
            }
        }
    }
    
    /**
     *
     * @param remoteFile 远程文件名
     * @param remotePath 远程路径
     * @param localFile  本地地址+文件名
     * @throws Exception
     */
    public void downloadFile(String remoteFile, String remotePath, String localFile) throws Exception {
        OutputStream output = null;
        File file = null;
        try {
            file = new File(localFile);
            if (!checkFileExist(localFile)) {
                file.createNewFile();
            }
            output = new FileOutputStream(file);
            channel.cd(remotePath);
            channel.get(remoteFile, output);
        } catch (Exception e) {
            throw new Exception("Download file error.");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    throw new Exception("Close stream error.");
                }
            }
            
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public Vector listFiles(String remotePath) throws Exception {
        Vector vector = null;
        try {
            vector = channel.ls(remotePath);
        } catch (SftpException e) {
            throw new Exception("list file error.");
        }
        return vector;
    }


    
    private boolean checkFileExist(String localPath) {
        File file = new File(localPath);
        return file.exists();
    }
    
    public static void main(String[] args) throws Exception {
        
        SFTPUtils sftpUtil = new SFTPUtils();
        System.out.println("开始连接");
        ChannelSftp channeltest = sftpUtil.getConnect();
        System.out.println("开始连接");
        
        System.out.println(channeltest.isConnected());
//        sftpUtil.uploadFile("D:\\deskbook\\a.txt","b.txt","/");

        sftpUtil.downloadFile("plsql.pdf", "/", "d:\\a\\a.pdf");
        sftpUtil.closeChannel();

        System.out.println("结束连接连接");
        
    }
}
