package com.ecpss.util.utiltest;

import lombok.extern.slf4j.Slf4j;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by xc on 2019/7/12.
 */
@Slf4j
public class ZipUtil {
    
    public static void main(String[] args) throws Exception{
        String s = "C:\\Users\\Raytine\\Desktop\\众多\\java学习\\分布式和集群.docx";
//        File f = ZipUtil.zip(s);
//        System.out.println(f);
        File file=new File("a.txt");
        file.createNewFile();
        String absolutePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        System.out.println(absolutePath);
        
    }
    
    public ZipUtil() {
    }
    
    /**
     *   压缩 流文件
     * @param name 源文件的名称
     * @param zipName 压缩后的路劲
     * @throws Exception
     */
    public static  void zipInstream(InputStream inputStream,String name,String zipName) throws Exception{
        String absolutePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();//系统路径
        String path=absolutePath+"\\"+zipName+".zip";//压缩后路径
        File zipFile = new File(path);
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ZipEntry entry = new ZipEntry( name);
        zos.putNextEntry(entry);
        int count;
        byte[] buf = new byte[1024];
        while ((count = bis.read(buf)) != -1) {
            zos.write(buf, 0, count);
        }
        IOUtil.close(zos,fos);
     
    }
    
    /**
     *  压缩文件
     * @param filePath    待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zip(String filePath) {
        File target = null;
        File source = new File(filePath);
        if (source.exists()) {
            // 压缩文件名=源文件名.zip
            String zipName = source.getName() + ".zip";
            target = new File(source.getParent(), zipName);
            if (target.exists()) {
                target.delete(); // 删除旧的文件
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                // 添加对应的文件Entry
                addEntry("/", source, zos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtil.closeQuietly(zos, fos);
            }
        }
        return target;
    }
    
    /**
     * 扫描添加文件Entry
     * @param base  基路径
     * @param source  源文件
     * @param zos  Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos)
            throws IOException {
        // 按目录分级，形如：/aaa/bbb.txt
        String entry = base + source.getName();
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                // 递归列出目录下的所有文件，添加文件Entry
                addEntry(entry + "/", file, zos);
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                IOUtil.closeQuietly(bis, fis);
            }
        }
    }
    
    /**
     * 解压文件
     * @param filePath 解压路径
     */
    public static void unzip(String filePath) {
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null
                        && !entry.isDirectory()) {
                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists()) {
                        // 创建文件父目录
                        target.getParentFile().mkdirs();
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtil.closeQuietly(zis, bos);
            }
        }
    }
    
    
    
}
