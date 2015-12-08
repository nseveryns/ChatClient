package net.nseveryns.chatclient.themes;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nseveryns
 */
public class ThemeLoader {
    private final static ThemeLoader INSTANCE = new ThemeLoader();
    private final Gson gson;
    private final List<Theme> themes;
    private Theme currentTheme;

    public static ThemeLoader getInstance() {
        return INSTANCE;
    }

    private ThemeLoader() {
        this.gson = new Gson();
        this.themes = ImmutableList.copyOf(loadThemes());
        this.currentTheme = new DefaultTheme();
    }

    public Theme loadTheme(File file) {
        Preconditions.checkArgument(file.getName().endsWith(".json"));

        try {
            return gson.fromJson(new FileReader(file), Theme.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Theme> loadThemes() {
        File directory = new File("themes");

        if (!(directory.exists() || directory.isDirectory())) {
            if (!directory.mkdirs()) {
                throw new NullPointerException();
            }
        }

        File[] files = directory.listFiles((dir, name) -> {
            return name.endsWith(".json");
        });

        List<Theme> themes = new ArrayList<>();

        for (File file : files) {
            themes.add(loadTheme(file));
        }
        return themes;
    }

    public Theme getTheme() {
        return currentTheme;
    }
}
