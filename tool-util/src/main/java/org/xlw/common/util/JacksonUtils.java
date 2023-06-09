package org.xlw.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: json处理工具-important，尽量不用fastJson，有很多bug
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/5/31 17:29
 */
@Slf4j
public class JacksonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    // 日起格式化
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * 对象转Json格式字符串
     * @param obj 对象
     * @return Json格式字符串
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Parse Object to String error : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Parse Object to String error : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc jsonStr转化为具体类实例处理
     * 对于集合对象有坑，谨慎使用
     * @date 2023/6/1 11:40
     * @param clazz 类
     * @param json json String
     * @return T
     * @throws
     * @since
    */
    public static <T>T parseJson(Class<T> clazz, String json) {
        if (StringUtils.isBlank(json) || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
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
        if (jsonObject == null) {
            return null;
        }
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
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("jacksonUtil error: ", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * @desc String to 自定义对象转换（集合对象支持）
     *
     * @date 2023/6/13 14:21
     * @param str
     * @param typeReference
     * @return T
     * @throws
     * @since
    */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc String to 集合对象转换
     *
     * @date 2023/6/13 14:21
     * @param str
     * @param collectionClazz
     * @param elementClazzes
     * @return T
     * @throws
     * @since
    */
    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse String to Object error : {}" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc 实例Obj转Map
     *
     * @date 2023/7/6 16:21
     * @param obj
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws
     * @since
    */
    public static Map<String, Object> obj2Map(Object obj) {
        try {
            Map<String, Object> map;
            map = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            return map;
        } catch (IllegalArgumentException e) {
            log.warn("Parse Object to Map error", e.getMessage());
            throw e;
        }
    }

}
