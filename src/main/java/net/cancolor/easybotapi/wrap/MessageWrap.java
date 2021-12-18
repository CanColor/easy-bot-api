package net.cancolor.easybotapi.wrap;


import net.cancolor.easybotapi.constant.AtConstant;
import net.cancolor.easybotapi.constant.MessageTypeConstant;
import net.cancolor.easybotapi.model.message.*;
import net.mamoe.mirai.internal.message.FileMessageImpl;
import net.mamoe.mirai.internal.message.OnlineFriendImage;
import net.mamoe.mirai.internal.message.OnlineGroupImageImpl;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Soar
 * @title: MessageWrap
 * @projectName easy-mirai
 * @description: 消息包装类
 * @date 2021/12/11 0:51
 */
public class MessageWrap {
    static Logger logger = LoggerFactory.getLogger(MessageWrap.class);

    public static List<Message> wrap(MessageChain chain) {
        return setMessageFiled(chain);
    }


    public static List<Message> setMessageFiled(MessageChain chain) {
        return setMessageType(chain);
    }


    public static List<Message> setMessageType(MessageChain chain) {
        List<Message> messageList = new ArrayList<>();
        Message message = new Message();

        String content = null;
        for (int i = 0; i < chain.size(); i++) {
            Object obj = chain.get(i);
            if (obj instanceof PlainText) {
                content = ((PlainText) obj).getContent();
                if (content.contains("\n")) {
                    continue;
                }
                message.setMessage(((PlainText) obj).getContent());
            } else {
                messageList.add(checkMessageType(obj, message));
            }
        }
        //消息 QQ空间相册
        return messageList;
    }

    public static Message checkMessageType(Object obj, Message message) {
        //At
        if (obj instanceof At) {
            At at = (At) obj;
            Class clz = at.getClass();
            AtMessage atMessage = new AtMessage();
            atMessage.setType(clz.getSimpleName());
            message.setAtMessage(atMessage);
            message.setMessageType(AtConstant.AT);
        }
        //at全体
        else if (obj instanceof AtAll) {
            AtAll atAll = (AtAll) obj;
            AtMessage atMessage = new AtMessage();
            atMessage.setType(atAll.getClass().getSimpleName());
            message.setAtMessage(atMessage);
            message.setMessageType(AtConstant.AT_ALL);
        }
        //图片 gif
        else if (obj instanceof OnlineGroupImageImpl) {
            OnlineGroupImageImpl onlineGroupImage = (OnlineGroupImageImpl) obj;
            ImageMessage imageMessage = new ImageMessage();
            BeanUtils.copyProperties(onlineGroupImage, imageMessage);
            message.setImageMessage(imageMessage);
        }
        //表情
        else if (obj instanceof net.mamoe.mirai.message.data.Face) {
            List<FaceMessage> faceList = message.getFaceMessageList();
            if (faceList == null) {
                faceList = new ArrayList<>();
            }
            FaceMessage face = new FaceMessage();
            BeanUtils.copyProperties(obj, face);
            faceList.add(face);
            message.setFaceMessageList(faceList);
        }
        //上传文件,录像
        else if (obj instanceof FileMessageImpl) {
            FileMessage fileMessage = new FileMessage();
            BeanUtils.copyProperties(obj, fileMessage);
            message.setFileMessage(fileMessage);
        }
        //url外链
        else if (obj instanceof SimpleServiceMessage) {
            net.cancolor.easybotapi.model.message.SimpleServiceMessage simpleServiceMessage = new net.cancolor.easybotapi.model.message.SimpleServiceMessage();
            BeanUtils.copyProperties(obj, simpleServiceMessage);
            message.setSimpleServiceMessage(simpleServiceMessage);
        }
        //私聊gif
        else if (obj instanceof OnlineFriendImage) {
            OnlineFriendImage onlineFriendImage = (OnlineFriendImage) obj;
            ImageMessage image = new ImageMessage();
            BeanUtils.copyProperties(onlineFriendImage, image);
            message.setImageMessage(image);
        }
        //pc震动/戳一戳（私聊）
        else if (obj instanceof net.mamoe.mirai.message.data.PokeMessage) {
            PokeMessage pokeMessage = new PokeMessage();
            BeanUtils.copyProperties(obj, pokeMessage);
            message.setPokeMessage(pokeMessage);
            message.setMessage(MessageTypeConstant.POKE);
        }
        //手机戳一戳傍边那个
        else if (obj instanceof net.mamoe.mirai.message.data.VipFace) {
            net.mamoe.mirai.message.data.VipFace orignVipFace = (net.mamoe.mirai.message.data.VipFace) obj;
            VipFaceMessage vipFaceMessage = new VipFaceMessage();
            vipFaceMessage.setKind(orignVipFace.getKind());
            vipFaceMessage.setCount(orignVipFace.getCount());
            message.setVipFaceMessage(vipFaceMessage);
        }
        return message;

    }
}
