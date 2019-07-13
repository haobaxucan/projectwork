package com.ecpss.util.utiltest;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.ByteArrayBuffer;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Created by xc on 2019/7/12.
 */
@Slf4j
public class FileUtils {
    private static final String TAG = "FileUtils";
    
    
    /**
     * 将文件输入流写入文件
     * @param inStream
     * @param path 路径+文件名
     * @return
     */
    public static boolean writeFileFromInputStream(InputStream inStream, String path) {
        boolean result = true;
        try {
            File file = createNewFile(path);
            FileOutputStream out = new FileOutputStream(file);
            byte[] data = new byte[1024];
            int num = 0;
            while ((num = inStream.read(data, 0, data.length)) != -1) {
                out.write(data, 0, num);
            }
            out.close();
            data = null;
        } catch (Exception e) {
            result = false;
            log.error(TAG, e.getMessage());
        }
        return result;
    }
    
    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/cc.txt
     * @param newPath String 复制后路径 如：f:/zz.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            
        }
        
    }
    
    
    /**
     * 赋值多个文件
     * 根据给出路径自动选择复制文件或整个文件夹
     * @param src :源文件或文件夹路径
     * @param dest :目标文件或文件夹路径
     * */
    public static void copyFiles(String src, String dest) {
        File srcFile = new File(src);
        if (srcFile.exists()) {
            if (srcFile.isFile()) {
                writeFileFromInputStream(readFileToInputStream(src), dest);
            } else {
                File[] subFiles = srcFile.listFiles();
                if (subFiles.length == 0) {
                    File subDir = new File(dest);
                    subDir.mkdirs();
                } else {
                    for (File subFile : subFiles) {
                        String subDirPath = dest + System.getProperty("file.separator") + subFile.getName();
                        copyFiles(subFile.getAbsolutePath(), subDirPath);
                    }
                }
            }
        }
    }
    
    /**
     * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则删除旧文件
     *
     * @param path :待创建文件路径
     */
    public static File createNewFile(String path) {
        File file = new File(path);
        try {
            if (!file.getParentFile().exists()) {
                System.out.println(file.getParentFile() + "1");
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            log.error(TAG, e.getMessage());
        }
        return file;
    }
    
   
    
    /**
     * 递归删除文件或子文件夹
     *
     * @param path 路径
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        
        if (file.isFile()) {
            file.delete();
            return;
        }
        
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFile(f.getAbsolutePath());
            }
            file.delete();
        } else {
            file.delete();
        }
    }
    
    /**
     * 获取文件输入流
     * */
    public static InputStream readFileToInputStream(String path) {
        InputStream inputStream = null;
        try {
            File file = new File(path);
            inputStream = new FileInputStream(file);
        } catch (IOException e) {
            log.error(TAG, e.getMessage());
        }
        return inputStream;
    }
    /**
     * 读取文件字节数组
     * */
    public static byte[] readFileToBytes(String path) {
        InputStream inputStream = readFileToInputStream(path);
        if (inputStream != null) {
            byte[] data = new byte[1024];
            ByteArrayBuffer buffer = new ByteArrayBuffer(1024);
            int n = 0;
            try {
                while ((n = inputStream.read(data)) != -1) {
                    buffer.append(data, 0, n);
                }
                data = null;
                inputStream.close();
            } catch (IOException e) {
                log.error(TAG, e.getMessage());
            }
            return buffer.toByteArray();
        }
        return null;
    }
    
    /**
     * 读取文件内容
     * 编码格式 utf-8
     * @param path
     * @return
     */
    public static String readFileToString(String path) {
        byte[] dataBytes = readFileToBytes(path);
        if (dataBytes != null) {
            try {
                return new String(dataBytes,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
                buffer.append(tempString).append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            log.error(TAG, e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    log.error(TAG, e.getMessage());
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    log.error(TAG, e.getMessage());
                }
            }
        }
        return buffer.toString();
    }
    
    /**
     * 转换文件大小
     * */
    public static String FormetFileSize(long size) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String result = "";
        if (size < 1024) {
            result = df.format((double) size) + "B";
        } else if (size < 1048576) {
            result = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            result = df.format((double) size / 1048576) + "M";
        } else {
            result = df.format((double) size / 1073741824) + "G";
        }
        return result;
    }
    
}
