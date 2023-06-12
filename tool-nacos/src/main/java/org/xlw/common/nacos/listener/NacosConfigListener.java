package org.xlw.common.nacos.listener;

import com.alibaba.nacos.api.config.listener.AbstractListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.xlw.common.nacos.annotations.Bind;
import org.xlw.common.util.BeanUtils;
import org.xlw.common.util.JacksonUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Description: 根据dataId，groupId特定的类配置监听
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 15:30
 */
@Slf4j
public class NacosConfigListener extends AbstractListener {

    private Class clazz;

    public NacosConfigListener(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void receiveConfigInfo(final String configInfo) {
        List<Field> fieldList = BeanUtils.getAnnotationField(Bind.class, clazz);
        if (CollectionUtils.isEmpty(fieldList)) {
            return;
        }
        Field field = fieldList.stream().findFirst().orElse(null);
        // 更新配置实例
        field.setAccessible(true);
        try {
            field.set(clazz, JacksonUtils.parseJson(clazz, configInfo));
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
