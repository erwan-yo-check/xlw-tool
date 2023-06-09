package org.xlw.common.nacos;

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

    @Bind(dataId = "111", group = "jjj", desc = "测试dataId")
    private static ExampleConfig instance = new ExampleConfig();

    private String field1;
    private String field2;
    private String field3;

    public static ExampleConfig getInstance() {
        return instance;
    }
}
