package net.cancolor.easymiraiapi.model.message;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ContactsMessage implements Serializable {
    private static final long serialVersionUID = 8657004423233075222L;

    /**
     * 动作
     */
    private String action;

    /*
    禁言单位 ：秒
     */
    private int minute;

    private String killMessage = "给爷爪巴！";
    //是否永拒
    private boolean block;


}
