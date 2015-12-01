package net.nseveryns.chatclient.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import net.nseveryns.chatclient.ChatClient;

public class PipelineInitializer extends ChannelInitializer<Channel> {
    private final ChatClient client;

    public PipelineInitializer(ChatClient client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());

        pipeline.addLast(new ServerHandler(client));
    }
}
