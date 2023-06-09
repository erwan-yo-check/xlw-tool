package org.xlw.common.nacos.example;

import lombok.Data;
import org.xlw.common.nacos.annotations.Bind;
import org.xlw.common.nacos.annotations.NacosConfigClass;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/12 12:56
 */
@Data
@NacosConfigClass
public class ExampleConfig {

    private String field1;

    private Boolean field2;

    private String field3 = "yes_blank";

    private String[] arr;
    @Bind(dataId = "test.01.erwan", group = "DEFAULT_GROUP")
    public static ExampleConfig instance;

    public static ExampleConfig getInstance() {
        return instance;
    }
}
