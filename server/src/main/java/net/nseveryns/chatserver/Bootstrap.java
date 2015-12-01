package net.nseveryns.chatserver;

public class Bootstrap {
    public static void main(String[] args) {
        int port = 14732;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new ChatServer(port);
    }
}
