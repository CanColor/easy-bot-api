package net.cancolor.easymiraiapi.model.message;


import lombok.Data;
import lombok.ToString;
import net.cancolor.easymiraiapi.model.message.role.Friend;

import java.io.Serializable;

@Data
@ToString
public class ContactsMessage implements Serializable {
    private static final long serialVersionUID = 8657004423233075222L;

    /**
     * 动作
     */
    private String action;

    private Friend from;
    /*
    禁言单位 ：秒
     */
    private int minute;

    private String killMessage;
    //是否永拒
    private boolean block;


}
