package org.xlw.common.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.xlw.common.nacos.annotations.Bind;
import org.xlw.common.nacos.annotations.NacosConfigClass;
import org.xlw.common.nacos.listener.NacosConfigListener;
import org.xlw.common.util.BeanUtils;
import org.xlw.common.util.JacksonUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 15:56
 */
@Configuration
@ComponentScan(basePackages = {"org.xlw.common.nacos"})
@Slf4j
public class NacosConfig {

    @Value("${nacos.server.addr}")
    private String serverAddr;

    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public ConfigService nacosConfigService() {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            /**
             * 1.获取所有的注解bean
             * 2.做一次每个注解类内的static instance的初始化
             * 3.初始化所有监听，并且注册
             */
            ConfigService configService = NacosFactory.createConfigService(properties);
            Map<String, Object> beansWithAnnotationMap = this.applicationContext.getBeansWithAnnotation(NacosConfigClass.class);
            for(Map.Entry<String, Object> entry: beansWithAnnotationMap.entrySet()){
                Class clazz = AopUtils.getTargetClass(entry.getValue());
                List<Field> fieldList = BeanUtils.getAnnotationField(Bind.class, clazz);
                if (CollectionUtils.isEmpty(fieldList)) {
                    log.warn("该class没有绑定@Bind注解");
                    return configService;
                }
                Field field = fieldList.stream().findFirst().orElse(null);
                if (field.isAnnotationPresent(Bind.class)) {
                    Bind bind = field.getAnnotation(Bind.class);
                    String configInitJson = configService.getConfig(bind.dataId(), bind.group(), 5000);
                    log.info("初始化nacos config |  " + bind);
                    field.setAccessible(true);
                    field.set(clazz, JacksonUtils.parseJson(clazz, configInitJson));
                    configService.addListener(bind.dataId(), bind.group(), new NacosConfigListener(clazz));
                }
            }
            return configService;
        } catch (NacosException e) {
            log.error(e.getErrMsg());
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
