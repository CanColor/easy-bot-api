package net.cancolor.easybotapi.channel;

import io.netty.channel.Channel;
import net.cancolor.easybotapi.constant.ContactsConstant;
import net.cancolor.easybotapi.constant.MessageConstant;
import net.cancolor.easybotapi.constant.MessageTypeConstant;
import net.cancolor.easybotapi.model.message.*;
import net.cancolor.easybotapi.model.message.dto.AudioMessageDTO;
import net.cancolor.easybotapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easybotapi.utils.SendServerMessageUtil;
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

    private List<Message> messageList = new ArrayList<>();
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
        messageList = new ArrayList<>();
        this.channel = channel;
        sendServerMessageDTO.setGroupId(groupId)
                .setFriendId(friendId)
                .setBotIdList(botIdList)
                .setComond(MessageConstant.CHAT)
                .setMessageList(messageList);
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
        if (messageList != null && messageList.size() > 0) {
            send();
        }
        Message message = new Message();
        ContactsMessage contactsMessage = new ContactsMessage();
        contactsMessage.setAction(ContactsConstant.NUDGE);
        if (contactsConstant.equals(ContactsConstant.MEMBER_MUTE)) {
            contactsMessage.setMinute(minute);
            contactsMessage.setAction(ContactsConstant.MEMBER_MUTE);
        } else if (contactsConstant.equals(ContactsConstant.MEMBER_KICK)) {
            contactsMessage.setKillMessage(killMessage);
            contactsMessage.setAction(ContactsConstant.MEMBER_KICK);
            contactsMessage.setBlock(block);
        }
        message.setContactsMessage(contactsMessage);
        messageList.add(message);
        send();
    }

    @Override
    public WebSocketMessageChannel addPlainText(String text) {
        Message message = new Message();
        message.setMessage(text);
        messageList.add(message);
        return this;
    }

    @Override
    public WebSocketMessageChannel addAt() {
        Message message = new Message();
        AtMessage atMessage = new AtMessage();
        atMessage.setType(MessageTypeConstant.AT);
        message.setAtMessage(atMessage);
        messageList.add(message);
        return this;
    }

    @Override
    public WebSocketMessageChannel addAtAll() {
        Message message = new Message();
        AtMessage atMessage = new AtMessage();
        atMessage.setType(MessageTypeConstant.AT_ALL);
        message.setAtMessage(atMessage);
        messageList.add(message);
        return this;
    }

    @Override
    public WebSocketMessageChannel addFaceList(List<Integer> faceIdList) {
        Message message = new Message();
        List<FaceMessage> faceMessageList = new ArrayList<>();
        for (int i = 0; i < faceIdList.size(); i++) {
            FaceMessage faceMessage = new FaceMessage();
            faceMessage.setId(faceIdList.get(i));
            faceMessageList.add(faceMessage);
        }
        message.setFaceMessageList(faceMessageList);
        message.setFaceMessageList(faceMessageList);
        return this;
    }


    @Override
    public WebSocketMessageChannel addFace(Integer faceId) {
        Message message = new Message();
        List<FaceMessage> faceMessageList = new ArrayList<>();
        FaceMessage faceMessage = new FaceMessage();
        faceMessage.setId(faceId);
        faceMessageList.add(faceMessage);
        message.setFaceMessageList(faceMessageList);
        return this;
    }

    @Override
    public WebSocketMessageChannel addPokeMessage(PokeMessage pokeMessage) {
        Message message = new Message();
        message.setPokeMessage(pokeMessage);
        messageList.add(message);
        return this;
    }

    @Override
    public WebSocketMessageChannel addVipFace(VipFace.Kind vipFaceConstant) {
        Message message = new Message();
        VipFaceMessage vipFaceMessage = new VipFaceMessage();
        vipFaceMessage.setKind(vipFaceConstant);
        message.setVipFaceMessage(vipFaceMessage);
        messageList.add(message);
        sendServerMessageDTO.setMessageList(messageList);
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
    public WebSocketMessageChannel addMusicShare(MusicShareMessage musicShareMessage) {
        Message message = new Message();
        message.setMusicShare(musicShareMessage);
        messageList.add(message);
        sendServerMessageDTO.setMessageList(messageList);
        return this;
    }

    @Override
    public WebSocketMessageChannel addAudioMessage(String uploadType, String resource) {
        Message message = new Message();
        AudioMessageDTO audioMessageDTO = new AudioMessageDTO();
        audioMessageDTO.setResource(resource);
        audioMessageDTO.setUploadType(uploadType);
        message.setAudioMessage(audioMessageDTO);
        messageList.add(message);
        sendServerMessageDTO.setMessageList(messageList);
        return this;
    }

    @Override
    public MessageChannel addUrlMessage(UrlMessage urlMessage) {
        Message message = new Message();
        message.setUrlMessage(urlMessage);
        messageList.add(message);
        sendServerMessageDTO.setMessageList(messageList);
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
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setOriginUrl(url);
        addImage(false, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addImageByImageId(String imageId) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setImageId(imageId);
        addImage(false, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addImageByImageByFilePath(String filePath) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFilePath(filePath);
        addImage(false, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addFlashImageByUrl(String url) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setOriginUrl(url);
        addImage(true, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addFlashImageByImageId(String imageId) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setImageId(imageId);
        addImage(true, imageMessage);
        return this;
    }

    @Override
    public MessageChannel addFlashImageByImageByFilePath(String filePath) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFilePath(filePath);
        addImage(true, imageMessage);
        return this;
    }


    private void addImage(Boolean isFlash, ImageMessage imageMessage) {
        Message message = new Message();
        if (isFlash) {
            message.setFlashImageMessage(imageMessage);
        } else {
            message.setImageMessage(imageMessage);
        }
        messageList.add(message);
        sendServerMessageDTO.setMessageList(messageList);
    }
}
