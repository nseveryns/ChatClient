package net.nseveryns.chatclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.nseveryns.chatclient.gui.ChatWindow;
import net.nseveryns.chatclient.net.PipelineInitializer;

import javax.swing.JOptionPane;
import java.net.InetSocketAddress;

public class ChatClient {
    private final EventLoopGroup group;
    private final ChatWindow window;

    protected ChatClient() {
        String host = JOptionPane.showInputDialog("Host", "localhost");
        int port = Integer.parseInt(JOptionPane.showInputDialog("Port", 14732));

        this.group = new NioEventLoopGroup();
        ChannelFuture connect = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(group)
                .handler(new PipelineInitializer(this))
                .connect(new InetSocketAddress(host, port));
        Channel channel = connect.channel();

        this.window = new ChatWindow(channel);
        this.window.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    private void shutdown() {
        group.shutdownGracefully();
    }

    public void displayText(String msg) {
        window.displayText(msg);
    }
}
