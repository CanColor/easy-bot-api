package net.cancolor.easybotapi.model.message;

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
public class UrlMessage implements Serializable {

    private static final long serialVersionUID = -790212911059064768L;

    private String url;
    private String title;
    private String content;
    private String coverUrl;


}
