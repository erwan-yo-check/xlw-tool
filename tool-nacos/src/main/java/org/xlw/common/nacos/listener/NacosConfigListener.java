package org.xlw.common.nacos.listener;

import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * Description: 根据dataId，groupId特定的监听
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 15:30
 */
@Slf4j
public class NacosConfigListener extends AbstractConfigChangeListener {
    private final String dataId;

    private final String group;

    private Class clazz;


    @Override
    public void receiveConfigChange(ConfigChangeEvent event) {
        Collection<ConfigChangeItem> configChangeItems =  event.getChangeItems();
        configChangeItems.forEach(o -> {
            System.out.println("key: " + o.getKey());
            System.out.println("oldValue: " + o.getOldValue());
            System.out.println("newValue: " + o.getNewValue());
        });

        ConfigChangeItem check = event.getChangeItem("check");

        if (check != null && "true".equalsIgnoreCase(check.getNewValue())) {
            System.out.println("这是需要处理的业务");
        }
    }

    public NacosConfigListener(String dataId, String group, Class clazz) {
        this.dataId = dataId;
        this.group = group;
        this.clazz = clazz;
    }

}
