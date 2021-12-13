package net.cancolor.easymiraiapi.model.message.client.receive;

import net.cancolor.easymiraiapi.model.message.role.Bot;
import net.cancolor.easymiraiapi.model.message.role.Friend;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


import java.io.Serializable;

/**
 * @author SoarDao
 * @title: MessageDTO
 * @projectName easy-mirai-api
 * @description: TODO
 * @date 2021/12/12 18:14
 */
@Data
@ToString
@Accessors(chain = true)
public class ClientMessage implements Serializable {

    //QQ用户
    private Friend friend;
    //机器
    private Bot bot;

}
