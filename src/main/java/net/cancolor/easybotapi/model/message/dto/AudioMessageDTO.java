package net.cancolor.easybotapi.model.message.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Soar
 * @title: ImageMessage
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 13:16
 */
@ToString
@Data
@Accessors(chain = true)
public class AudioMessageDTO implements Serializable {

    private static final long serialVersionUID = -790212926059064768L;

    //资源 url path 路径名
    private String resource;

    private String uploadType;


}
