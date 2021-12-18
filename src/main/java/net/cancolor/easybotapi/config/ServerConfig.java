package net.cancolor.easybotapi.config;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author SoarDao
 * @title: ServerInfo
 * @projectName easy-bot-api
 * @description: TODO
 * @date 2021/12/12 17:37
 */
@Data
@Accessors(chain = true)
@ToString
@Configuration
public class ServerConfig implements Serializable {

    private static final long serialVersionUID = -2664916749656393695L;


    @Value("${bot.port:7777}")
    private Integer port;

    @Value("${bot.ip:127.0.0.1}")
    private String ip;

    @Value("${bot.appkey:toutouyanshu}")
    private String appkey;


}
