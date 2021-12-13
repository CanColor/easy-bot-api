package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Soar
 * @title: AtMessage
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 12:43
 */
@Data
@ToString
public class AtMessage implements Serializable {

    private static final long serialVersionUID = -7830270583331637118L;
    //类型
    private String type;



}
