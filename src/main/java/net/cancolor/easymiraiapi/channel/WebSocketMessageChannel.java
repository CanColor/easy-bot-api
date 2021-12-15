package net.cancolor.easymiraiapi.channel;

import io.netty.channel.Channel;
import net.cancolor.easymiraiapi.constent.AtConstant;
import net.cancolor.easymiraiapi.constent.ContactsConstant;
import net.cancolor.easymiraiapi.constent.MessageConstant;
import net.cancolor.easymiraiapi.model.message.*;
import net.cancolor.easymiraiapi.model.message.client.send.SendServerMessage;
import net.cancolor.easymiraiapi.model.message.dto.SendServerImageMessageDTO;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easymiraiapi.utils.SendServerMessageUtil;
import net.mamoe.mirai.message.data.VipFace;

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
        sendServerMessageList = new ArrayList<>();
        this.channel = channel;
        sendServerMessageDTO.setGroupId(groupId)
                .setFriendId(friendId)
                .setBotIdList(botIdList)
                .setComond(MessageConstant.CHAT)
                .setSendServerMessageList(sendServerMessageList);
        return this;
    }


    @Override
    public WebSocketMessageChannel nudge() throws Exception {
        sendContactsMessage(ContactsConstant.NUDGE, null, null, null);
        return this;
    }

    @Override
    public MessageChannel mute(Integer minute) throws Exception {
        sendContactsMessage(ContactsConstant.MEMBER_MUTE, minute, null, null);
        return this;
    }

    @Override
    public MessageChannel unmute() throws Exception {
        sendContactsMessage(ContactsConstant.MEMBER_UNMUTE, null, null, null);
        return this;
    }

    @Override
    public MessageChannel kick(String message, Boolean block) throws Exception {
        sendContactsMessage(ContactsConstant.MEMBER_KICK, null, message, block);
        return this;
    }


    public void sendContactsMessage(String contactsConstant, Integer minute, String killMessage, Boolean block) throws Exception {
        if (sendServerMessageDTO.getGroupId() == null && !contactsConstant.equals(ContactsConstant.NUDGE)) {
            throw new Exception("群专用功能,必须填入群号！");
        }
        if (sendServerMessageList != null && sendServerMessageList.size() > 0) {
            send();
        }
        SendServerMessage sendServerMessage = new SendServerMessage();
        ContactsMessage contactsMessage = new ContactsMessage();
        contactsMessage.setAction(contactsConstant);
        if (contactsConstant.equals(ContactsConstant.MEMBER_MUTE)) {
            contactsMessage.setMinute(minute);
        } else if (contactsConstant.equals(ContactsConstant.MEMBER_KICK)) {
            contactsMessage.setKillMessage(killMessage);
            contactsMessage.setBlock(block);
        }
        sendServerMessage.setContactsMessage(contactsMessage);
        sendServerMessageList.add(sendServerMessage);
        send();
    }

    @Override
    public WebSocketMessageChannel addPlainText(String text) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        sendServerMessage.setMessage(text);
        sendServerMessageList.add(sendServerMessage);
        return this;
    }

    @Override
    public WebSocketMessageChannel addAt() {
        SendServerMessage sendServerMessage = new SendServerMessage();
        AtMessage atMessage = new AtMessage();
        atMessage.setType(AtConstant.AT);
        sendServerMessage.setAtMessage(atMessage);
        sendServerMessageList.add(sendServerMessage);
        return this;
    }

    @Override
    public WebSocketMessageChannel addAtAll() {
        SendServerMessage sendServerMessage = new SendServerMessage();
        AtMessage atMessage = new AtMessage();
        atMessage.setType(AtConstant.AT_ALL);
        sendServerMessage.setAtMessage(atMessage);
        sendServerMessageList.add(sendServerMessage);
        return this;
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
        sendServerMessageList.add(sendServerMessage);
        return this;
    }


    @Override
    public WebSocketMessageChannel addFace(Integer faceId) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        List<FaceMessage> faceMessageList = new ArrayList<>();
        FaceMessage faceMessage = new FaceMessage();
        faceMessage.setId(faceId);
        faceMessageList.add(faceMessage);
        sendServerMessage.setFaceMessageList(faceMessageList);
        sendServerMessageList.add(sendServerMessage);
        return this;
    }

    @Override
    public WebSocketMessageChannel addPokeMessage(PokeMessage pokeMessage) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        sendServerMessage.setPokeMessage(pokeMessage);
        sendServerMessageList.add(sendServerMessage);
        return this;
    }

    @Override
    public WebSocketMessageChannel addVipFace(VipFace.Kind vipFaceConstant) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        VipFaceMessage vipFaceMessage = new VipFaceMessage();
        vipFaceMessage.setKind(vipFaceConstant);
        sendServerMessage.setVipFaceMessage(vipFaceMessage);
        sendServerMessageList.add(sendServerMessage);
        sendServerMessageDTO.setSendServerMessageList(sendServerMessageList);
        return this;
    }

    @Override
    public WebSocketMessageChannel addLightApp() {
        return this;
    }

    @Override
    public WebSocketMessageChannel addSimpleServiceMessage() {
        return this;
    }

    @Override
    public WebSocketMessageChannel addDice() {
        return this;
    }

    @Override
    public WebSocketMessageChannel addMusicShare() {
        return this;
    }

    @Override
    public WebSocketMessageChannel addAudio() {
        return this;
    }

    @Override
    public WebSocketMessageChannel addForwardMessage() {
        return this;
    }

    @Override
    public WebSocketMessageChannel addMarketFace() {
        return this;
    }

    @Override
    public void send() {
        if (sendServerMessageDTO != null && sendServerMessageDTO.getComond() != null) {
            SendServerMessageUtil.sendServer(channel, sendServerMessageDTO);
        }
        sendServerMessageDTO = new SendServerMessageDTO();
    }

    @Override
    public WebSocketMessageChannel addImageByUrl(String url) {
        SendServerImageMessageDTO imageMessage = new SendServerImageMessageDTO();
        imageMessage.setOriginUrl(url);
        addImage(false, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addImageByImageId(String imageId) {
        SendServerImageMessageDTO imageMessage = new SendServerImageMessageDTO();
        imageMessage.setImageId(imageId);
        addImage(false, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addImageByImageByFilePath(String filePath) {
        SendServerImageMessageDTO imageMessage = new SendServerImageMessageDTO();
        imageMessage.setPath(filePath);
        addImage(false, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addFlashImageByUrl(String url) {
        SendServerImageMessageDTO imageMessage = new SendServerImageMessageDTO();
        imageMessage.setOriginUrl(url);
        addImage(true, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addFlashImageByImageId(String imageId) {
        SendServerImageMessageDTO imageMessage = new SendServerImageMessageDTO();
        imageMessage.setImageId(imageId);
        addImage(true, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addFlashImageByImageByFilePath(String filePath) {
        SendServerImageMessageDTO imageMessage = new SendServerImageMessageDTO();
        imageMessage.setPath(filePath);
        addImage(true, imageMessage);
        return this;
    }


    private void addImage(Boolean isFlash, SendServerImageMessageDTO imageMessage) {
        SendServerMessage sendServerMessage = new SendServerMessage();
        if (isFlash) {
            sendServerMessage.setFlashImageMessage(imageMessage);
        } else {
            sendServerMessage.setImageMessage(imageMessage);
        }
        sendServerMessageList.add(sendServerMessage);
        sendServerMessageDTO.setSendServerMessageList(sendServerMessageList);
    }
}
