package net.nseveryns.chatclient.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.nseveryns.chatclient.ChatClient;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private final ChatClient client;

    public ServerHandler(ChatClient client) {
        this.client = client;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        client.displayText(msg);
    }
}
