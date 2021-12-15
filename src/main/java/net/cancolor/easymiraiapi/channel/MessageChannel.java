package net.cancolor.easymiraiapi.channel;


import net.cancolor.easymiraiapi.model.message.MusicShareMessage;
import net.cancolor.easymiraiapi.model.message.PokeMessage;
import net.mamoe.mirai.message.data.VipFace;

import java.util.List;

/**
 * @author SoarDao
 * @title: service
 * @projectName canColor
 * @description: 发送消息接口
 * @date 2021/12/13 21:36
 */
public interface MessageChannel extends ImageMessageChannel {
    /**
     * 戳一戳
     * 只能单独使用不能和别的信息一起用
     */
    MessageChannel nudge() throws Exception;


    /**
     * 群专用
     * 禁言
     */
    MessageChannel mute(Integer minute) throws Exception;

    /**
     * 解除禁言
     * 群专用
     */
    MessageChannel unmute() throws Exception;

    /**
     * 群专用
     * 踢人
     */
    MessageChannel kick(String message, Boolean block) throws Exception;


    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 纯文本
     */
    MessageChannel addPlainText(String text);



    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 提及某人
     */
    MessageChannel addAt();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 提及全体成员
     */
    MessageChannel addAtAll();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 原生表情
     */
    MessageChannel addFace(Integer faceId);

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 原生表情
     */
    MessageChannel addFaceList(List<Integer> faceIdList);

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 戳一戳消息（消息非动作）
     */
    MessageChannel addPokeMessage(PokeMessage pokeMessage);


    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: VIP 表情
     */
    MessageChannel addVipFace(VipFace.Kind vipFaceConstant);

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 小程序
     */
    MessageChannel addLightApp();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: （不稳定）服务消息
     */
    MessageChannel addSimpleServiceMessage();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 魔法表情骰子
     */
    MessageChannel addDice();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 音乐分享
     */
    MessageChannel addMusicShare(MusicShareMessage MusicShare);

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 语音
     */
    MessageChannel addAudio();


    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 合并转发
     */
    MessageChannel addForwardMessage();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 商城表情
     */
    MessageChannel addMarketFace();

    /**
     * 发送消息
     */
    void send();

}
