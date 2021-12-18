package net.cancolor.easybotapi.plug;

import net.cancolor.easybotapi.model.message.dto.SendClientMessageDTO;
import net.cancolor.easybotapi.model.message.dto.SendServerMessageDTO;
import org.springframework.stereotype.Component;

/**
 * @author SoarDao
 * @title: TestPlugImpl
 * @projectName canColor
 * @description: TODO
 * @date 2021/12/15 21:05
 */
@Component
public class TestPlugImpl implements PlugInterface {

    private final Plug plug;

    public TestPlugImpl() {
        plug = new Plug().setClzName(this.getClass().getName()).setName("复读机")
                .setStatus(1).setConditionType(3).setCondition(null).setEventList(null);
    }

    @Override
    public Plug init() {
        return plug;
    }

    @Override
    public SendServerMessageDTO run(SendClientMessageDTO sendClientMessageDTO) {
        return null;
    }


}
