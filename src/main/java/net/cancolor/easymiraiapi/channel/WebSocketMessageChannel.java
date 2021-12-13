package net.cancolor.easymiraiapi.channel;

import io.netty.channel.Channel;
import net.cancolor.easymiraiapi.constent.AtConstant;
import net.cancolor.easymiraiapi.constent.MessageConstant;
import net.cancolor.easymiraiapi.model.message.AtMessage;
import net.cancolor.easymiraiapi.model.message.FaceMessage;
import net.cancolor.easymiraiapi.model.message.client.send.SendServerMessage;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easymiraiapi.utils.SendServerMessageUtil;

import java.util.ArrayList;
import java.util.List;


///**
// * @author SoarDao
// * @title: WebSocketSendServerServiceImpl
// * @projectName canColor
// * @description: websocket 消息发送api
// * @date 2021/12/13 22:00
// */
public class WebSocketMessageChannel implements MessageChannel {

    private Channel channel;

    private List<SendServerMessage> sendServerMessageList = new ArrayList<>();

    private SendServerMessageDTO sendServerMessageDTO = new SendServerMessageDTO();

    private WebSocketMessageChannel webSocketMessageChannel ;





    /**
     * 群聊
     *
     * @param channel
     * @return
     */
    public WebSocketMessageChannel builder(Channel channel, List<Long> botIdList, Long groupId, Long friendId) {
        return init(channel, botIdList, groupId, friendId);
    }

    /**
     * 私聊
     *
     * @param channel
     * @param friendId
     * @return
     */
    public WebSocketMessageChannel builder(Channel channel, List<Long> botIdList, Long friendId) {
        return init(channel, botIdList, null, friendId);
    }


    public WebSocketMessageChannel builder(Channel channel, Long botId, Long groupId, Long friendId) {
        List botIdList = new ArrayList();
        botIdList.add(botId);
        return init(channel, botIdList, groupId, friendId);
    }


    public WebSocketMessageChannel builder(Channel channel, Long botId, Long friendId) {
        List botIdList = new ArrayList();
        botIdList.add(botId);
        return init(channel, botIdList, null, friendId);
    }


    private WebSocketMessageChannel init(Channel channel, List<Long> botIdList, Long groupId, Long friendId) {
        this.channel = channel;
        sendServerMessageDTO.setGroupId(groupId)
                .setFriendId(friendId)
                .setBotIdList(botIdList)
                .setComond(MessageConstant.CHAT)
                .setSendServerMessageList(sendServerMessageList);
        webSocketMessageChannel = this;
        return webSocketMessageChannel;
    }


    @Override
    public WebSocketMessageChannel addPlainText(String text) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        sendServerMessage.setMessage(text);
        sendServerMessageList.add(sendServerMessage);
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addAt() {
        SendServerMessage sendServerMessage = new SendServerMessage();
        AtMessage atMessage = new AtMessage();
        atMessage.setType(AtConstant.AT);
        sendServerMessage.setAtMessage(atMessage);
        sendServerMessageList.add(sendServerMessage);
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addAtAll() {
        SendServerMessage sendServerMessage = new SendServerMessage();
        AtMessage atMessage = new AtMessage();
        atMessage.setType(AtConstant.AT_ALL);
        sendServerMessage.setAtMessage(atMessage);
        sendServerMessageList.add(sendServerMessage);
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addFaceList(List<Integer> faceIdList) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        List<FaceMessage> faceMessageList = new ArrayList<>();
        for (int i = 0; i < faceIdList.size(); i++) {
            FaceMessage faceMessage = new FaceMessage();
            faceMessage.setId(faceIdList.get(i));
            faceMessageList.add(faceMessage);
        }
        sendServerMessage.setFaceMessageList(faceMessageList);
        return webSocketMessageChannel;
    }


    @Override
    public WebSocketMessageChannel addFace(Integer faceId) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        List<FaceMessage> faceMessageList = new ArrayList<>();
        FaceMessage faceMessage = new FaceMessage();
        faceMessage.setId(faceId);
        faceMessageList.add(faceMessage);
        sendServerMessage.setFaceMessageList(faceMessageList);
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addPokeMessage() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addVipFace() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addLightApp() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addSimpleServiceMessage() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addDice() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addMusicShare() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addAudio() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addForwardMessage() {
        return webSocketMessageChannel;
    }

    @Override
    public WebSocketMessageChannel addMarketFace() {
        return webSocketMessageChannel;
    }

    @Override
    public void send() {
        SendServerMessageUtil.sendServer(channel,sendServerMessageDTO);
        sendServerMessageDTO=null;
    }
}
