package net.nseveryns.chatclient.gui;

import io.netty.channel.Channel;
import net.nseveryns.chatclient.themes.Theme;
import net.nseveryns.chatclient.themes.ThemeLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatWindow extends JFrame {
    private final Channel channel;
    private final JTextArea textArea;

    public ChatWindow(Channel channel) {
        Theme theme = ThemeLoader.getInstance().getTheme();

        this.channel = channel;
        this.setTitle("ChatClient");
        this.setResizable(true);
        {
            JPanel panel = new JPanel();
            JTextField textField = new JTextField("Enter message here...");
            panel.add(textField, BorderLayout.WEST);
            JButton button = new JButton("Send");
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        button.doClick();
                    }
                }
            });
            button.addActionListener(e -> {
                sendText(textField.getText());
                textField.setText("");
            });
            panel.add(button, BorderLayout.EAST);
            this.add(panel, BorderLayout.SOUTH);
        }
        {
            this.textArea = new JTextArea();
            this.textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            this.add(scrollPane);
        }
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void displayText(String message) {
        //String name = null; //TODO: Get the username of a person
        SimpleDateFormat format = new SimpleDateFormat("[K:m:s a] ");
        this.textArea.append(format.format(new Date()) //+ name
                + "> " + message + "\n");
    }

    private void sendText(String text) {
        channel.writeAndFlush(text);
    }
}
