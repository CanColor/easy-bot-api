package net.cancolor.easymiraiapi.factory;

import net.cancolor.easymiraiapi.channel.MessageChannel;
import net.cancolor.easymiraiapi.channel.WebSocketMessageChannel;
import net.cancolor.easymiraiapi.constant.ChannelConstant;

/**
 * @author SoarDao
 * @title: MessageChannelFactory
 * @projectName canColor
 * @description: TODO
 * @date 2021/12/13 23:28
 */
public class MessageChannelFactory {


    public static MessageChannel getMessageChannel(String channelConstant) throws Exception {
        MessageChannel messageChannel = null;
        if (ChannelConstant.HTTP.equals(channelConstant)) {
//            MessageChannel messageChannel=new WebSocketMessageChannel();
        } else if (ChannelConstant.WEB_SOCKET.equals(channelConstant)) {
            messageChannel = new WebSocketMessageChannel();
        } else {
            throw new Exception("暂时不支持此通信协议！");
        }
        return messageChannel;
    }


}
