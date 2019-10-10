package com.ecpss.tkmybatismapper.Base;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseDTO implements Serializable {

    public String build(String template) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldValue = "";
            try {
                fieldValue = new Gson().toJson(field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String flagChar = "#" + fieldName + "#";
            template = template.replace(flagChar, fieldValue);
        }
        return template;
    }
}
