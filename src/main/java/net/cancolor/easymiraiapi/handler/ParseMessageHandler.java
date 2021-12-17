package net.cancolor.easymiraiapi.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.cancolor.easymiraiapi.model.message.dto.SendClientMessageDTO;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easymiraiapi.plug.PlugLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 解析消息处理者
 */
@Component
public class ParseMessageHandler {
    @Autowired
    private PlugLocator plugLocator;


    public SendServerMessageDTO parseMessage(TextWebSocketFrame frame) {
       String text= frame.text();
        SendClientMessageDTO sendClientMessageDTO= JSONObject.parseObject(text, SendClientMessageDTO.class);
//        if(sendClientMessageDTO.getMessage())
        return null;
    }


}
