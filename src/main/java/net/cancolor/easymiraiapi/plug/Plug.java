package net.cancolor.easymiraiapi.plug;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author SoarDao
 * @title: Plug
 * @projectName canColor
 * @description: TODO
 * @date 2021/12/15 21:11
 */
@Data
@Accessors(chain = true)
@ToString
public class Plug implements Serializable {

    private static final long serialVersionUID = -5713687937520745443L;

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
