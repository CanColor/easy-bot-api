package net.cancolor.easybotapi.model.message.role;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Soar
 * @title: Bot
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 0:27
 */
@Data
@ToString
@Accessors(chain = true)
public class Bot implements Serializable {

    private static final long serialVersionUID = -6274457916899118634L;

    //QQ号
    private Long id;
    //名称
    private String name;


}
