package org.xlw.common.nacos.aop;

import com.alibaba.nacos.api.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.xlw.common.nacos.annotations.Bind;
import org.xlw.common.nacos.listener.NacosConfigListener;

import java.lang.reflect.Field;

/**
 * Description: 消费nacos配置的AOP
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 14:31
 */
@Aspect
@Component
@Slf4j
public class NacosConfigAop {

    @Autowired
    @Qualifier("nacosConfigService")
    private ConfigService nacosConfigService;

    @Around("@annotation(org.xlw.common.nacos.annotations.NacosConfigClass)")
    public void dealConfig(ProceedingJoinPoint proceedingJoinPoint) {
        Class clazz = proceedingJoinPoint.getClass();
        Field[] fields = clazz.getFields();

        // 设置监听
        nacosConfigService.addListener(dataId, group, new NacosConfigListener(dataId, group, clazz));
        for (Field field: fields) {
            boolean isInstance = field.isAnnotationPresent(Bind.class);
            if (isInstance) {
                // 是需要修改的config实例
            }
        }
    }

}
