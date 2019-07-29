package com.ecpss.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;


/**
 * SFTP文件上传工具
 *
 * @author
 * @create 2017-03-30
 **/
public class SFTPUpload {
    private static Logger logger = LogManager.getLogger(SFTPUpload.class);


    public boolean putFile(InputStream inputStream, String SFTPFileName, String SFTPFilePath) {
        logger.info("商户昨天数据文件上传:" + SFTPFileName + SFTPFilePath);
        try {
            SFTPChannel channel = new SFTPChannel();
            ChannelSftp chSftp;

            chSftp = channel.getChannel(100000);

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

}
