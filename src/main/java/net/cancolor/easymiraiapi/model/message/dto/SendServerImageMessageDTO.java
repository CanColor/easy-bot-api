package net.cancolor.easymiraiapi.model.message.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Soar
 * @title: SendImageMessageDTO
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 13:16
 */
@ToString
@Data
@Accessors(chain = true)
public class SendServerImageMessageDTO implements Serializable {


    private static final long serialVersionUID = -8635378538779242591L;
    //imageID
    private String imageId;
    //本地地址路径
    private String path;
    //网络url、
    private String originUrl;


}
