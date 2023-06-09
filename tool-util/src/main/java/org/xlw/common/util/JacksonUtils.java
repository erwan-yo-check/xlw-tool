package org.xlw.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * Description: json处理工具-important，尽量不用fastJson，有很多bug
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/5/31 17:29
 */
@Slf4j
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
    public static <T>T parseJson(Class<T> clazz, String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //json字符串转成对象
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("jacksonUtil error: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc jsonObj转化为json字符串
     *
     * @date 2023/6/9 13:30
     * @param jsonObject 结构体
     * @return java.lang.String
     * @throws
     * @since
    */
    public static String parseJsonObj(Object jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            log.error("jacksonUtil error: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc map转JsonString
     *
     * @date 2023/6/9 13:40
     * @param map hashMap
     * @return java.lang.String
     * @throws
     * @since
    */
    public static String parseMap(HashMap<String,Object> map) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("jacksonUtil error: ", e);
            throw new RuntimeException(e);
        }
    }
}
