package net.nseveryns.chatclient;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1;

    private final String content;
    private final String senderName;
    private final long sendTimestamp;

    public Message(String content, String senderName, long sendTimestamp) {
        this.content = content;
        this.senderName = senderName;
        this.sendTimestamp = sendTimestamp;
    }

    public String getContent() {
        return content;
    }

    public String getSenderName() {
        return senderName;
    }

    public long getSendTimestamp() {
        return sendTimestamp;
    }
}