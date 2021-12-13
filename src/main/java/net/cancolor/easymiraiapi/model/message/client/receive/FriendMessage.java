package net.cancolor.easymiraiapi.model.message.client.receive;

import net.cancolor.easymiraiapi.model.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


import java.io.Serializable;

/**
 * @author Soar
 * @title: FriendMessageDTO
 * @projectName easy-mirai
 * @description: 用户dto
 * @date 2021/12/11 0:26
 */
@Data
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper =true)
@Accessors(chain = true)
public class FriendMessage extends ClientMessage implements Serializable  {
    private static final long serialVersionUID = 2347828656391878337L;

    //消息体
    private Message message;

}
