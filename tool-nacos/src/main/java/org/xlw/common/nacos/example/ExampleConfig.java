package org.xlw.common.nacos.example;

import lombok.Data;
import org.xlw.common.nacos.annotations.Bind;
import org.xlw.common.nacos.annotations.NacosConfigClass;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/9 15:23
 */
@NacosConfigClass
@Data
public class ExampleConfig {

    @Bind(dataId = "erwan.test", group = "DEFAULT_GROUP", desc = "测试dataId")
    public static ExampleConfig instance = new ExampleConfig();

    private String test;
    private Boolean wow;
    private Integer yyy;

    public static ExampleConfig getInstance() {
        return instance;
    }
}
