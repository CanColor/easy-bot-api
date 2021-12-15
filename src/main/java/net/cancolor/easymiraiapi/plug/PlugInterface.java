package net.cancolor.easymiraiapi.plug;

import net.cancolor.easymiraiapi.model.message.dto.SendClientMessageDTO;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;

/**
 * @author SoarDao
 * @title: ApiInterface
 * @projectName canColor
 * @description: TODO
 * @date 2021/12/15 20:50
 */
public interface PlugInterface {
    //初始化插件
    Plug init();

    //插件执行入口
    SendServerMessageDTO run(SendClientMessageDTO sendClientMessageDTO);


}
