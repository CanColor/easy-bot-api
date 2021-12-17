package net.cancolor.easymiraiapi.model.message.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.cancolor.easymiraiapi.model.message.Message;
import net.cancolor.easymiraiapi.model.message.role.Bot;
import net.cancolor.easymiraiapi.model.message.role.Friend;
import net.cancolor.easymiraiapi.model.message.role.Group;

import java.io.Serializable;
import java.util.List;

/**
 * @author SoarDao
 * @title: SendClientMessage
 * @projectName easy-bot-api
 * @description: TODO
 * @date 2021/12/12 18:23
 */
@Data
@ToString
@Accessors(chain = true)
public class SendClientMessageDTO implements Serializable {
    //发送QQid
    private Friend friend;
    //机器id
    private Bot bot;
    //发送群id
    private Group group;
    //消息类型
    private String comond;
    //2非匿名 0匿名 是否实名
    private Integer level;
    //是否使用miraiCode
    private Integer isUseMiraiCode=0;

    private List<Message> messageList;

    private String miraiCode;

    private String systemMessage;

    private Long time = System.currentTimeMillis();



}
