package net.cancolor.easymiraiapi.model.message.role;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Soar
 * @title: Friend
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 0:27
 */
@Data
@ToString
@Accessors(chain = true)
public class Friend implements Serializable {

    private static final long serialVersionUID = -6272479221599921320L;

    //QQ号
    private Long id;
    //名称
    private String nameCard;
    //QQ名称
    private String nike;
    //QQ群自定义标签
    private String remake;

}
