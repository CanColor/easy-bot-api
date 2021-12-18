package net.cancolor.easybotapi.model.message;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.mamoe.mirai.message.data.ImageType;

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
public class ImageMessage implements Serializable {

    private static final long serialVersionUID = -790212966059064768L;

    private int height;

    private String imageId;

    private ImageType imageType;

    private String originUrl;

    private String filePath;





}
