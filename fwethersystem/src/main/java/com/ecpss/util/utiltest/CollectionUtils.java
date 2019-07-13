package com.ecpss.util.utiltest;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/7/12.
 */
public class CollectionUtils {
    
    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if (map == null) {
            return true;
        }
        return map.isEmpty();
    }
    
    /**
     * 判断map是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
    
    /**
     * list == null 是否有集合
     * list.isEmpty() 集合内是否有元素
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null ? true : list.isEmpty();
    }
    
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}
