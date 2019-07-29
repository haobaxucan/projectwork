package com.ecpss.util;

import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * SFTP工具类
 *
 * @author
 * @create 2017-03-31
 **/
public class SFTPChannel {
    Session session = null;
    Channel channel = null;

    public final static String FTP_HOST="127.0.0.1";
    public final static String PORT="24";
    public final static String FTP_USER_NAME="xc";
    public final static String FTP_PASSWORD="123456";

    private Logger logger = LogManager.getLogger(SFTPChannel.class);

    public ChannelSftp getChannel(int timeout) throws JSchException {
        logger.info("sftp连接");
        
        String ftpHost = FTP_HOST;
        String port = PORT;
        String ftpUserName = FTP_USER_NAME;
        String ftpPassword = FTP_PASSWORD;

        int ftpPort = Integer.valueOf(port);
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象logger.info("[{}]-[{}]-[{}]", StringUtil.getCurrentDate(), "网安-查询-注册-文件上传到服务器", "Session created.");
        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        return (ChannelSftp) channel;
    }

    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

  
    
    public static void main(String[] args) throws Exception{
        ChannelSftp channel = new SFTPChannel().getChannel(3000);
    }

}
