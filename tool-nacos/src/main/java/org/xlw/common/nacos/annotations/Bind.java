package org.xlw.common.nacos.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 绑定具体配置对象实例，更新变化内容
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 14:22
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bind {
    /**
     * Nacos 配置中心的 dataId
     */
    String dataId();

    /**
     * Nacos 配置中心的 groupId
     */
    String group();

    /**
     * 配置描述
     */
    String desc();
}
