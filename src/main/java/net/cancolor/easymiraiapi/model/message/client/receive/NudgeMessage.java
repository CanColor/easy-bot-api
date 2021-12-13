package net.cancolor.easymiraiapi.model.message.client.receive;

import net.cancolor.easymiraiapi.model.message.role.Friend;
import net.cancolor.easymiraiapi.model.message.role.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


import java.io.Serializable;

/**
 * @author Soar
 * @title: NudgeMessageDTO
 * @projectName easy-mirai
 * @description: 用户dto
 * @date 2021/12/11 0:26
 */
@Data
@Accessors(chain = true)
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper =true)
public class NudgeMessage extends ClientMessage implements Serializable {


    private static final long serialVersionUID = -3821891266223470569L;


    //被戳人
    private Friend target;
    //动作
    private String action;
    //后缀
    private String suffix;
    //群组
    private Group group;


}
