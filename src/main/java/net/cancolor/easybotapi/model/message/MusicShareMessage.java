package net.cancolor.easybotapi.model.message;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.mamoe.mirai.message.data.MusicKind;


@Data
@ToString
@Accessors(chain = true)
public class MusicShareMessage {
    //播放器
    private MusicKind player;
    //标题
    private String titile;
    //歌手
    private String summary;
    //主页
    private String jumpUrl;
    //主图
    private String pictureUrl;
    //声源
    private String musicUrl;

    public MusicShareMessage() {
    }

    public MusicShareMessage(MusicKind player, String titile, String summary, String jumpUrl, String pictureUrl, String musicUrl) {
        this.player = player;
        this.titile = titile;
        this.summary = summary;
        this.jumpUrl = jumpUrl;
        this.pictureUrl = pictureUrl;
        this.musicUrl = musicUrl;
    }
}

