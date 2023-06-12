package org.xlw.common.nacos.annotations;

import java.lang.annotation.*;

/**
 * Description: nacos 的配置绑定类注解
 * 配合 org.xlw.common.nacos.annotations.Bind 一起使用
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 14:16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NacosConfigClass {
}