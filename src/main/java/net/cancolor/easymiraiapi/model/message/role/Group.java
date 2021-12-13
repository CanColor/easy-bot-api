package net.cancolor.easymiraiapi.model.message.role;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Soar
 * @title: Group
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 0:27
 */
@Data
@ToString
@Accessors(chain = true)
public class Group implements Serializable {

    private static final long serialVersionUID = 4681799342039962014L;

    //群号
    private Long id;
    //群名
    private String name;


}
