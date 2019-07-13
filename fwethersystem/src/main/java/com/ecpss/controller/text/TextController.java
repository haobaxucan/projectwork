package com.ecpss.controller.text;

import com.ecpss.util.AuthCodeUtil;
import com.ecpss.util.streamdemo.DownloadUtils;
import com.ecpss.util.streamdemo.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xc on 2019/7/13.
 */
@Slf4j
@Controller
public class TextController {
    @RequestMapping("/text")
    public String text(HttpServletResponse response) {
        DownloadUtils.getResponseOutput("C:\\Users\\Raytine\\Desktop\\xc.XLSX", response);
        DownloadUtils.closeResponseOutput(response);
        return "index";
    }
    
    @RequestMapping("/qrcode")
    
    public String qrcode(HttpServletResponse response, HttpServletRequest request) throws Exception {
//        QRCodeUtil.createQRCode(response, "http:www.baidu.com", 400, 400);
        log.info("开始生成");
//        String qrCode = QRCodeUtil.getQRCode("http://www.baidu.com", 400, 400);
//        log.info("qrcode={}",qrCode);
        return "index";
    }
    
    @RequestMapping("/yzmcode")
    @ResponseBody
    public String code(HttpServletResponse response, HttpServletRequest request) throws Exception {
        log.info("开始生成");
        AuthCodeUtil.createCodeImage(response.getOutputStream());
        return "index";
    }
    
}
