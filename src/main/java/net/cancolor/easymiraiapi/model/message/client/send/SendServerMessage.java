package net.cancolor.easymiraiapi.model.message.client.send;

import net.cancolor.easymiraiapi.model.message.*;
import net.cancolor.easymiraiapi.model.message.dto.SendServerFileMessageDTO;
import net.cancolor.easymiraiapi.model.message.dto.SendServerImageMessageDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Soar
 * @title: SendMessageDTO
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 23:08
 */
@Data
@ToString
//接受客户端消息实体
public class SendServerMessage implements Serializable {

    private static final long serialVersionUID = -258876265869827087L;
    //普通消息
    private String message;
    //消息类型 At
    private AtMessage atMessage;
    //戳一戳/pc震动
    private PokeMessage pokeMessage;
    //图片
    private List<SendServerImageMessageDTO> sendImageMessageList;
    //表情
    private List<FaceMessage> faceMessageList;
    //文件
    private SendServerFileMessageDTO sendFileMessage;
    //外链
    private SimpleServiceMessage simpleServiceMessage;
    //加强戳一戳
    private VipFaceMessage vipFaceMessage;




}
