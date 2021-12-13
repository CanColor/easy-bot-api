package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Soar
 * @title: FaceMessage
 * @projectName easy-mirai
 * @description: QQ原生表情
 * @date 2021/12/11 13:52
 */
@ToString
@Data
public class FaceMessage implements Serializable {
    private static final long serialVersionUID = 7152831510158185863L;
    
    private Integer id;
    private String name;
}
