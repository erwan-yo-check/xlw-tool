package org.xlw.common.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public ConfigService nacosConfigService() {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            return NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            log.error(e.getErrMsg());
            throw new RuntimeException(e);
        }
    }
}
