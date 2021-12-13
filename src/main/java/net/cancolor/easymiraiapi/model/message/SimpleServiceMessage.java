package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;
import net.mamoe.mirai.message.data.MessageKey;
import net.mamoe.mirai.message.data.RichMessage;

/**
 * @author Soar
 * @title: SimpleUrlMessage
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 14:15
 */
@ToString
@Data
public class SimpleServiceMessage {
    private String content;
    private MessageKey<RichMessage> key;
    private int serviceId;


}
