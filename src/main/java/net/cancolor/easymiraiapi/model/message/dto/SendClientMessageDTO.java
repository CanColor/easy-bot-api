package net.cancolor.easymiraiapi.model.message.dto;

import net.cancolor.easymiraiapi.model.message.client.receive.ClientMessage;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author SoarDao
 * @title: SendClientMessage
 * @projectName easy-mirai-api
 * @description: TODO
 * @date 2021/12/12 18:23
 */
@Data
@ToString
public class SendClientMessageDTO implements Serializable {
    //消息类型
    private String comond;

    private Integer isUseMiraiCode;
    //消息
    private ClientMessage message;


    //系统消息
    private String systemMessage;

    //时间
    private Long time = System.currentTimeMillis();



}
