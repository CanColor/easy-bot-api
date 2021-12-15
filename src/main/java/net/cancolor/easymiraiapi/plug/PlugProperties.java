package net.cancolor.easymiraiapi.plug;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@Configuration
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
@ConfigurationProperties(prefix = "plug")
public class PlugProperties {
    private List<PlugProp> data;

//    private final AppProp app = new AppProp();

    @Data
    public static class PlugProp {
        //类名
        public String clzName;
        //插件名 用于日志打印
        public String name;
        //条件
        public String condition;
        //条件类型 1.正则 2.匹配至空格结束  3.不启用规则，一直触发
        public Integer conditionType;
        //状态 1启用 0关闭
        public Integer status;
        //事件类型
        public List<String> eventList;
    }

}