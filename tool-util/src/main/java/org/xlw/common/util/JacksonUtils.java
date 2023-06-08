package org.xlw.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Description: json处理工具-important，尽量不用fastJson，有很多bug
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/5/31 17:29
 */
public class JacksonUtils {

    /**
     * @desc jsonStr转化为具体类实例处理
     * @date 2023/6/1 11:40
     * @param clazz 类
     * @param json json String
     * @return T
     * @throws
     * @since
    */
    public static <T>T parseJson(Class<T> clazz, String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //json字符串转成对象
        T res = mapper.readValue(json, clazz);
        return res;
    }
}
