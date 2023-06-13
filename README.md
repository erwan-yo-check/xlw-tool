# xlw-tool

提供springboot-nacos-dubbo的模式下开发基础工具

## 包含
### 工具类
    JacksonUtil
    DateUtil
    HttpUtil (use okHttp)
### 注解类
#### Nacos 配置动态实时更新读取注解
    @NacosConfigClass 
    @Bind
    两个注解组合完成对Nacos配置的动态读取能力
##### 使用方式（具体见配置类 org.xlw.common.nacos.example.ExampleConfig）
###### 配置
```java
@Data
@NacosConfigClass
public class ExampleConfig {

    private String field1;

    private Boolean field2;

    private String field3 = "yes_blank";

    private String[] arr;
    @Bind(dataId = "test.01.erwan", group = "DEFAULT_GROUP", desc = "测试dataId的注解")
    public static ExampleConfig instance;

    public static ExampleConfig getInstance() {
        return instance;
    }
}
```
###### 消费（直接使用静态方法）
```java
public class TestController {
    public static void main(String[] args) {
        System.out.println(ExampleConfig.getInstance().getField1());
        System.out.println(ExampleConfig.getInstance().getField2());
    }
}
```

