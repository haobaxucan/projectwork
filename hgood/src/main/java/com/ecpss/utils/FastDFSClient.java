//package com.ecpss.utils;
//
//import org.csource.fastdfs.ClientGlobal;
//import org.csource.fastdfs.StorageClient;
//import org.csource.fastdfs.TrackerClient;
//import org.csource.fastdfs.TrackerServer;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
///**
// * Created by xc on 2019/9/3.
// */
//public class FastDFSClient {
//
//    public static String uploadFile(MultipartFile multipartFile) {
////        上传文件的url
//        String fileUrl = "http://192.168.88.130";
//
//        // 上传图片到服务器
//        // 配置fdfs的全局链接地址
//        String tracker = FastDFSClient.class.getResource("/tracker.conf").getPath();// 获得配置文件的路径
//
//        try {
//            ClientGlobal.init(tracker);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        TrackerClient trackerClient = new TrackerClient();
//
//        // 获得一个trackerServer的实例
//        TrackerServer trackerServer = null;
//        try {
//            trackerServer = trackerClient.getConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 通过tracker获得一个Storage链接客户端
//        StorageClient storageClient = new StorageClient(trackerServer, null);
//
//        try {
//
//            byte[] bytes = multipartFile.getBytes();// 获得上传的二进制对象
//
//            // 获得文件后缀名
//            String originalFilename = multipartFile.getOriginalFilename();// a.jpg
//            System.out.println(originalFilename);
//            int i = originalFilename.lastIndexOf(".");
//            String extName = originalFilename.substring(i + 1);
//
//            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);
//
//            for (String uploadInfo : uploadInfos) {
//                fileUrl += "/" + uploadInfo;
//            }
//            System.out.println("文件的url="+fileUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return fileUrl;
//    }
//}
