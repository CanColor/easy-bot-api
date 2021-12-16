package net.cancolor.easymiraiapi.init;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import net.cancolor.easymiraiapi.config.ServerConfig;
import net.cancolor.easymiraiapi.constant.MessageConstant;
import net.cancolor.easymiraiapi.handler.WebSocketClientHandler;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easymiraiapi.utils.SendServerMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ImClientInit {
    private Channel channel;


    @Autowired
    ServerConfig serverConfig;

    /**
     * 初始化客户端
     */
    public boolean init() {
        //连接服务端
        return connection();
    }

    public Channel getchannel() {
        //连接服务端
        return channel;
    }

    Logger logger = LoggerFactory.getLogger(ImClientInit.class);

    /**
     * 连接服务端
     */
    public boolean connection() {
        //netty基本操作，线程组
        EventLoopGroup group = new NioEventLoopGroup();
        //netty基本操作，启动类
        Bootstrap boot = new Bootstrap();
        // 创建连接
        boot.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(group)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("http-codec", new HttpClientCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(1024 * 1024 * 10));
                        pipeline.addLast("hookedHandler", new WebSocketClientHandler());
                    }
                });
        //websocke连接的地址，/hello是因为在服务端的websockethandler设置的
        URI websocketURI = null;
        try {
            websocketURI = new URI("ws://" + serverConfig.getIp() + ":" + serverConfig.getPort() + "/bot");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        //进行握手
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, null, true, httpHeaders);
        //客户端与服务端连接的通道，final修饰表示只会有一个
        try {
            this.channel = boot.connect(websocketURI.getHost(), websocketURI.getPort()).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebSocketClientHandler handler = (WebSocketClientHandler) channel.pipeline().get("hookedHandler");
        handler.setHandshaker(handshaker);
        handshaker.handshake(channel);
        //阻塞等待是否握手成功
        try {
            handler.handshakeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        logger.info("握手成功");
        login(channel);
        //给服务端发送的内容，如果客户端与服务端连接成功后，可以多次掉用这个方法发送消息
        return true;
    }

    public void login(Channel channel) {
        SendServerMessageDTO sendServerMessageDTO = new SendServerMessageDTO();
        sendServerMessageDTO.setComond(MessageConstant.LOGIN);
        sendServerMessageDTO.setClientId(1);
        sendServerMessageDTO.setClientName("sb");
        SendServerMessageUtil.send(channel, sendServerMessageDTO);
    }


}


