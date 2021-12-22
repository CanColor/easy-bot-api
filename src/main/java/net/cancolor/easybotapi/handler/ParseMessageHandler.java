package net.cancolor.easybotapi.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.cancolor.easybotapi.constant.MessageTypeConstant;
import net.cancolor.easybotapi.model.message.Message;
import net.cancolor.easybotapi.model.message.dto.SendClientMessageDTO;
import net.cancolor.easybotapi.plug.PlugInterface;
import net.cancolor.easybotapi.plug.PlugLocator;
import net.cancolor.easybotapi.wrap.MessageWrap;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 解析消息处理者
 */
@Component
public class ParseMessageHandler {
    @Autowired
    private PlugLocator plugLocator;
    private Map<String, List<PlugInterface>> noConditionPlug;
    private Map<String, Map<String, PlugInterface>> conditionPlug;
    private List<PlugInterface> allMessagePlug;

    public void parseMessage(TextWebSocketFrame frame) {
        initConditionPlug();
        String text = frame.text();
        SendClientMessageDTO sendClientMessageDTO = JSONObject.parseObject(text, SendClientMessageDTO.class);

        if (sendClientMessageDTO.getIsUseMiraiCode() == 1) {
            MessageChain messageChain = MiraiCode.deserializeMiraiCode(sendClientMessageDTO.getMiraiCode());
            sendClientMessageDTO.setMessageList(MessageWrap.wrap(messageChain));
        }
        //事件 条件
        startConditionPlug(sendClientMessageDTO);
        //事件
        startNoConditionPlug(sendClientMessageDTO);
        //全部
        startAllMessagePlug(sendClientMessageDTO);
    }


    private void startNoConditionPlug(SendClientMessageDTO sendClientMessageDTO) {
        List<PlugInterface> plugInterfaceList = noConditionPlug.get(sendClientMessageDTO.getMessageList().get(0).getMessageType());
        if (plugInterfaceList == null || plugInterfaceList.size() == 0) {
            return;
        }

        for (PlugInterface plugInterface : plugInterfaceList) {
            plugInterface.run(sendClientMessageDTO);
        }
    }


    private void startConditionPlug(SendClientMessageDTO sendClientMessageDTO) {
        Map<String, PlugInterface> plugInterfaceList = conditionPlug.get(checkType(sendClientMessageDTO.getMessageList().get(0)));
        if (plugInterfaceList == null || plugInterfaceList.size() == 0) {
            return;
        }

        if(sendClientMessageDTO.getMessageList().get(0).getMessage()!=null){
            String condition=sendClientMessageDTO.getMessageList().get(0).getMessage().split(" ")[0];
            PlugInterface plugInterface= plugInterfaceList.get(condition);
            if (plugInterface!=null){
                plugInterface.run(sendClientMessageDTO);
            }
        }
    }
    private void startAllMessagePlug(SendClientMessageDTO sendClientMessageDTO) {
        if (allMessagePlug == null || allMessagePlug.size() == 0) {
            return;
        }
        for (PlugInterface plugInterface : allMessagePlug) {
            plugInterface.run(sendClientMessageDTO);
        }

    }
    private void initConditionPlug() {
        if (this.noConditionPlug == null) {
            this.noConditionPlug = plugLocator.getNoConditionPlug();
        }
        if (this.conditionPlug == null) {
            this.conditionPlug = plugLocator.getConditionPlug();
        }
        if (this.allMessagePlug == null) {
            this.allMessagePlug = plugLocator.getAllMessagePlug();
        }
    }

    public String checkType(Message message) {
        if (message.getMessage() != null) {
            return MessageTypeConstant.MESSAGE;
        }
        if (message.getNudgeMessage() != null) {
            return MessageTypeConstant.NUDGE;
        }
        if (message.getAtMessage().getType().equalsIgnoreCase(MessageTypeConstant.AT)) {
            return MessageTypeConstant.AT;
        }
        if (message.getAtMessage().getType().equals(MessageTypeConstant.AT_ALL)) {
            return MessageTypeConstant.AT_ALL;
        }
        if (message.getPokeMessage() != null) {
            return MessageTypeConstant.POKE;
        }
        if (message.getImageMessage() != null) {
            return "";
        }
        if (message.getFlashImageMessage() != null) {
            return "";
        }
        if (message.getFaceMessageList() != null) {
            return "";
        }
        if (message.getFileMessage() != null) {
            return "";
        }
        if (message.getSimpleServiceMessage() != null) {
            return "";
        }
        if (message.getVipFaceMessage() != null) {
            return "";
        }
        if (message.getContactsMessage() != null) {
            return "";
        }
        if (message.getMusicShare() != null) {
            return "";
        }
        if (message.getAudioMessage() != null) {
            return "";
        }
        if (message.getUrlMessage() != null) {
            return "";
        }
        return "All";

    }
}
