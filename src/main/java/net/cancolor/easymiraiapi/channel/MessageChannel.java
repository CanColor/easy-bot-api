package net.cancolor.easymiraiapi.channel;


import java.util.List;

/**
 * @author SoarDao
 * @title: service
 * @projectName canColor
 * @description: 发送消息接口
 * @date 2021/12/13 21:36
 */
public interface MessageChannel {

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 纯文本
     */
    public MessageChannel addPlainText(String text);



    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 提及某人
     */
    public MessageChannel addAt();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 提及全体成员
     */
    public MessageChannel addAtAll();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 原生表情
     */
    public MessageChannel addFace(Integer faceId);

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 原生表情
     */
    public MessageChannel addFaceList(List<Integer> faceIdList);
    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 戳一戳消息（消息非动作）
     */
    public MessageChannel addPokeMessage();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: VIP 表情
     */
    public MessageChannel addVipFace();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 小程序
     */
    public MessageChannel addLightApp();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: （不稳定）服务消息
     */
    public MessageChannel addSimpleServiceMessage();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 魔法表情骰子
     */
    public MessageChannel addDice();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 音乐分享
     */
    public MessageChannel addMusicShare();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 语音
     */
    public MessageChannel addAudio();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 合并转发
     */
    public MessageChannel addForwardMessage();

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @description: 商城表情
     */
    public MessageChannel addMarketFace();

    /**
     * 发送消息
     */
    public void send();

}
