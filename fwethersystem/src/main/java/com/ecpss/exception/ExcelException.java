package com.ecpss.exception;

/**
 * Created by xc on 2019/7/10.
 */
public class ExcelException extends Exception {
    
        public ExcelException() {
        }
        
        public ExcelException(String message) {
            super(message);
        }
        
        public ExcelException(Throwable cause) {
            super(cause);
        }
        
        public ExcelException(String message, Throwable cause) {
            super(message, cause);
        }
    
}
