package net.nseveryns.chatclient.themes;

import java.awt.Color;

/**
 * @author nseveryns
 */
public class Theme {

    private transient final Color textColor;
    private transient final Color backgroundColor;
    private final String textColorName;
    private final String backgroundColorName;

    public Theme(String textColor, String backgroundColor) {
        this.textColorName = textColor;
        this.backgroundColorName = backgroundColor;

        this.textColor = Color.getColor(textColor);
        this.backgroundColor = Color.getColor(backgroundColor);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public String getTextColorName() {
        return textColorName;
    }

    public String getBackgroundColorName() {
        return backgroundColorName;
    }
}
