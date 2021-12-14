package net.cancolor.easymiraiapi.model.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author Soar
 * @title: PokeMessage
 * @projectName easy-mirai
 * @description: TODO
 * @date 2021/12/11 15:59
 */
@Data
@ToString
public class PokeMessage {


    private String name;
    private int pokeType;
    private int id;

    public PokeMessage() {
    }

    public PokeMessage(String name, int pokeType, int id) {
        this.id = id;
        this.name = name;
        this.pokeType = pokeType;
    }
}
