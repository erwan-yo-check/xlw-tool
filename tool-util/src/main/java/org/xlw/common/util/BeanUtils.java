package org.xlw.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: bean使用工具
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/12 12:38
 */
@Slf4j
public class BeanUtils {

    /**
     * @desc 获取某类下的某注解的属性
     *
     * @date 2023/6/12 12:38
     * @param annotation 注解
     * @param clazz 类名
     * @return java.lang.reflect.Field
     * @throws
     * @since
    */
    public static List<Field> getAnnotationField(Class annotation, Class clazz) {
        List<Field> list = new LinkedList<>();
        Field[] fields = clazz.getFields();
        for (Field field: fields) {
            if (field.isAnnotationPresent(annotation)) {
                list.add(field);
            }
        }
        return list;
    }
}
