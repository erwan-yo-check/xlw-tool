package org.xlw.common;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.xlw.common.util.JacksonUtils;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/8 09:42
 */
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            String serverAddr = "{serverAddr}";
            String dataId = "{dataId}";
            String group = "{group}";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            // json转对象，然后优化修改


            System.out.println(content);
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    // 做一些更新处理
                    System.out.println("recieve1:" + configInfo);
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });


        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}