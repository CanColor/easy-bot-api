package net.cancolor.easymiraiapi.model.message.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Soar
 * @title: SendFileMessageDTO
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 23:08
 */
@Data
@ToString
public class SendServerFileMessageDTO implements Serializable {


    private static final long serialVersionUID = 1192749331359332433L;


    private String path;

    private String fileName;
}
