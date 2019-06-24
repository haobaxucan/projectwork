package com.ecpss.util.file;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by xc on 2019/6/17.
 */
public class SFTPUpload {
    private static Logger logger = LogManager.getLogger(SFTPUpload.class);
    
    
    public boolean putFile(InputStream inputStream, String SFTPFileName, String SFTPFilePath) {
        logger.info("商户昨天数据文件上传:" + SFTPFileName + SFTPFilePath);
        try {
            SFTPChannel channel = new SFTPChannel();
            ChannelSftp chSftp;
            
            chSftp = channel.getChannel(10000,"192.168.88.128","22","test","1234");
            
            
            String[] folders = SFTPFilePath.replace("\\","/").split( "/" );
            for ( String folder : folders ) {
                if ( folder.length() > 0 ) {
                    try {
                        chSftp.cd( folder );
                    }
                    catch ( SftpException e ) {
                        chSftp.mkdir( folder );
                        chSftp.cd( folder );
                    }
                }
            }
            OutputStream out = chSftp.put(SFTPFileName, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
            byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
            int read;
            if (out != null) {
                System.out.println("Start to read input stream");
//                InputStream is = new FileInputStream(filePath);
                do {
                    read = inputStream.read(buff, 0, buff.length);
                    if (read > 0) {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
                System.out.println("input stream read done.");
            }
            chSftp.quit();
            channel.closeChannel();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SFTPUtil上传文件失败：" + e);
            return false;
        }
    }
    
    public static void main(String[] args) {
        String name="xx";
        String path="/file";
        InputStream byteArrayInputStream = new ByteArrayInputStream("aa".getBytes());
        SFTPUpload sftpUpload=new SFTPUpload();
        sftpUpload.putFile(byteArrayInputStream,name,path);
        
    }
}
