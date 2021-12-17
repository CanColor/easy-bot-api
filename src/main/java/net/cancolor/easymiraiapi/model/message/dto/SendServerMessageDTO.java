package net.cancolor.easymiraiapi.model.message.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.cancolor.easymiraiapi.model.message.Message;

import java.io.Serializable;
import java.util.List;

/**
 * @author SoarDao
 * @title: SendServerMessageDTO
 * @projectName easy-bot-api
 * @description: TODO
 * @date 2021/12/12 22:10
 */
@ToString
@Data
@Accessors(chain = true)
public class SendServerMessageDTO implements Serializable {

    private static final long serialVersionUID = 1192548578270770994L;

    //发送QQid
    private Long friendId;
    //机器id
    private List<Long> botIdList;
    //发送群id
    private Long groupId;
    //消息类型
    private String comond;

    //客户端名称
    private String clientName;

    //客户端标志
    private Integer clientId;


    //是否使用miraiCode
    private Integer isUseMiraiCode=0;

    private List<Message> messageList;


    private String miraiCode;

    private String systemMessage;

    private Long time = System.currentTimeMillis();

}
