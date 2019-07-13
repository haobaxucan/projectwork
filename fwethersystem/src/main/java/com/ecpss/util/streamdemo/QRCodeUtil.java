package com.ecpss.util.streamdemo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by xc on 2019/7/13.
 */
public class QRCodeUtil {
    /**
     * @param response
     * @param contents
     * @param width
     * @param height
     */
    
    public static void createQRCode(HttpServletResponse response, String contents, int width, int height) throws Exception {
        //生成二维码配置
        Map<EncodeHintType, Object> hints = new HashMap<>();
        
        //设置纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //编码类型
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        OutputStream out = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        
    }
 /*   public static String getQRCode(String contents, int width, int height) throws WriterException, IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(encode(contents, width, height), "png", out);
        return Base64.encodeBase64String(out.toByteArray());
    }*/
    
    /**
     *获取二维码，并将二维码保存到对应的位置
     * @param contents				需要编码的内容
     * @param width					生成的二维码宽度
     * @param height				生成的二维码高度
     * @param location				需要保存到的位置
     * @throws WriterException
     * @throws IOException
     */
    public static void getQRCode(String contents, int width, int height, String location) throws WriterException, IOException{
        ImageIO.write(encode(contents, width, height), "png", new File(location));
    }
    
  
    private static BufferedImage encode(String contents, int width, int height) throws WriterException{
        Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        
        return image;
    }
    public static void main(String[] args) throws Exception{
      
        QRCodeUtil.createQRCode(null,"www://baidu.com",100,200);
    }
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
}
