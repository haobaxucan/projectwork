package com.ecpss.util.file.one;

import com.jcraft.jsch.ChannelSftp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xc on 2019/6/17.
 */
public class SFTPTest {
    public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }
    
    public static void main(String[] args) throws Exception{
        SFTPTest test = new SFTPTest();
    
        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "192.168.88.128");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "test");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "1234");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
    
        String src = "C:\\Users\\Raytine\\Desktop\\cc.txt"; // 本地文件名
        String dst = "/file/c.txt"; // 目标文件名
    
        SFTPChannel channel = test.getSFTPChannel();
        
        
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
//         chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 传输方法1
//         chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // 传输方法2
    
        OutputStream out = chSftp.put(dst, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式  传输方法3
        byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
        int read;
        if (out != null) {
            System.out.println("开始传输文件");
            InputStream is = new FileInputStream(src);
            do {
                read = is.read(buff, 0, buff.length);
                if (read > 0) {
                    out.write(buff, 0, read);
                }
                out.flush();
            } while (read >= 0);
            System.out.println("文件传输结束");
        }
    
        chSftp.quit();
        channel.closeChannel();
    }
}
