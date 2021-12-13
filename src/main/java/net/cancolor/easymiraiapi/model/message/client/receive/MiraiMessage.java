package net.cancolor.easymiraiapi.model.message.client.receive;

import net.cancolor.easymiraiapi.model.message.role.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


import java.io.Serializable;

/**
 * @author Soar
 * @title: MiraiMessageDTO
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 22:04
 */
@Data
@Accessors(chain = true)
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper =true)
public class MiraiMessage extends ClientMessage implements Serializable {


    private static final long serialVersionUID = 8275073859504706496L;

    //群组
    private Group group;
    //消息主题
    private String miraiCode;

    private int level;


}
