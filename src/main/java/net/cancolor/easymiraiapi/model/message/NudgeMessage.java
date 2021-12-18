package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.cancolor.easymiraiapi.model.message.role.Friend;

import java.io.Serializable;

/**
 * @author Soar
 * @title: NudgeMessageDTO
 * @projectName easy-mirai
 * @description: 用户dto
 * @date 2021/12/11 0:26
 */
@Data
@Accessors(chain = true)
@ToString(callSuper =true)
public class NudgeMessage implements Serializable {


    private static final long serialVersionUID = -3821891266223470569L;


    //被戳人
    private Friend target;
    //动作
    private String action;
    //后缀
    private String suffix;



}
