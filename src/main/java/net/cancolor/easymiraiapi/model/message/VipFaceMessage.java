package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Soar
 * @title: VipFaceMessage
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 16:25
 */
@Data
@ToString
public class VipFaceMessage implements Serializable {
    private static final long serialVersionUID = -6874547382922602647L;


    private Integer count;

    private Integer id;

    private String name;
}
