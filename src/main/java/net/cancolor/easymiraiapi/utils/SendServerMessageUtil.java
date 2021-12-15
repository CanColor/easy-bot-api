package net.cancolor.easymiraiapi.utils;


import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.cancolor.easymiraiapi.constant.MessageConstant;
import net.cancolor.easymiraiapi.model.message.client.send.SendServerMessage;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//发送消息到服务端
public class SendServerMessageUtil {
    static Logger logger = LoggerFactory.getLogger(SendServerMessageUtil.class);

    public static void sendServer(Channel channel, SendServerMessageDTO sendServerMessageDTO) {
        send(channel, sendServerMessageDTO);
        logger.info("发送客户端消息: {}", JSONObject.toJSONString(sendServerMessageDTO));
    }


    public static void sendServer(Channel channel, String comond, Integer isUseMiraiCode, List<SendServerMessage> message) {
        //mirai消息
        SendServerMessageDTO sendServerMessageDTO = new SendServerMessageDTO();
        sendServerMessageDTO.setSendServerMessageList(message);
        sendServerMessageDTO.setComond(comond);
        sendServerMessageDTO.setIsUseMiraiCode(isUseMiraiCode);
        send(channel, sendServerMessageDTO);
        logger.info("发送客户端消息: {}", JSONObject.toJSONString(sendServerMessageDTO));
    }

    //系统消息
    public static void sendServer(Channel channel, String systemMessage) {
        SendServerMessageDTO sendServerMessageDTO = new SendServerMessageDTO();
        sendServerMessageDTO.setSystemMessage(systemMessage);
        sendServerMessageDTO.setComond(MessageConstant.SYSTEM);
        send(channel, sendServerMessageDTO);
        logger.info("发送客户端系统消息: {}", JSONObject.toJSONString(systemMessage));

    }

    public static TextWebSocketFrame wrapMessage(String systemMessage) {
        return new TextWebSocketFrame(systemMessage);
    }


    public static void send(Channel channel, SendServerMessageDTO sendServerMessageDTO) {
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JSONObject.toJSONString(sendServerMessageDTO));
        channel.writeAndFlush(textWebSocketFrame).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    logger.info("发送客户端消息成功：" + JSONObject.toJSONString(sendServerMessageDTO));
                } else {
                    logger.info("发送客户端消息失败 " + channelFuture.cause().getMessage());
                }

            }
        });
    }


}
