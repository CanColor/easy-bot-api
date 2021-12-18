package net.cancolor.easybotapi.model.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Soar
 * @title: FileMessage
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 13:57
 */
@Data
@ToString
public class FileMessage implements Serializable {
    private static final long serialVersionUID = 8657004423233075112L;


    private Integer busId;

    private String id;

    private Integer internalId;

    private String name;

    private Long size;

    private String path;

    private String fileName;
}
