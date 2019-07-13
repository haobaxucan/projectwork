package com.ecpss.util.utiltest;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by xc on 2019/7/12.
 */
public class IOUtil {
  
    
    /**
     *关闭一个或多个流对象
     * @param closeables
     */
    public static void closeQuietly(Closeable... closeables) {
        try {
            close(closeables);
        } catch (IOException e) {
            // do nothing
        }
    }
    
    public static void close(Closeable... closeables) throws IOException {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        }
    }
    
}
