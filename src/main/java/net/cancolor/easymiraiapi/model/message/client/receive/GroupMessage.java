package net.cancolor.easymiraiapi.model.message.client.receive;

import net.cancolor.easymiraiapi.model.message.role.Group;
import net.cancolor.easymiraiapi.model.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


import java.io.Serializable;

/*
 * @author Soar
 * @date 2021-12-10
 * @description: 群消息传输实体
 */
@Data
@Accessors(chain = true)
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper =true)
public class GroupMessage  extends ClientMessage implements Serializable {

    private static final long serialVersionUID = 2126544190174877342L;

    //qq群
    private Group group;
    //消息体
    private Message message;

}
