package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Soar
 * @title: Message
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 0:43
 */
@Data
@ToString
@Accessors(chain = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 6753084781033110478L;
    //2非匿名 0匿名 是否实名
    private Integer level;
    //消息
    private String content;
    //时间
    private Integer time;
    //消息类型 At
    private AtMessage atMessage;
    //戳一戳/pc震动
    private PokeMessage pokeMessage;
    //图片
    private List<ImageMessage> imageMessageList;
    //表情
    private List<FaceMessage> faceMessageList;
    //文件
    private FileMessage fileMessage;
    //外链
    private SimpleServiceMessage simpleServiceMessage;
    //加强戳一戳
    private VipFaceMessage vipFaceMessage;

}
