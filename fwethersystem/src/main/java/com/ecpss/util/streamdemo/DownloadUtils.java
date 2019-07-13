package com.ecpss.util.streamdemo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description:下载方法
 * @Author:xgchen
 * @Date:2015-04-10 14:12
 * @Version: V0.0.1
 */
public class DownloadUtils {
    /**
     * 获得response输出流，用于下载
     *
     * @param fileName
     * @param response
     * @return
     */
    public static OutputStream getResponseOutput(String fileName, HttpServletResponse response) {
        // Set the content type
        response.setContentType("application/x-msdownload");
        try {
            //Set the content-disposition
            response.addHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso-8859-1"));
            //// Get the outputstream
            return response.getOutputStream();
        } catch (Exception e) {
            new RuntimeException(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 关闭response输出流
     *
     * @param response
     */
    public static void closeResponseOutput(HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            os.flush();
            os.close();
        } catch (IOException e) {
            new RuntimeException(e.getMessage(), e);
        }
    }
}
