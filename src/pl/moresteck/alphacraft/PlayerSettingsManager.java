package pl.moresteck.alphacraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerSettingsManager {

	public String player;
    public static Logger a = Logger.getLogger("Minecraft");
    public Properties setter = new Properties();
    private File c;

    public PlayerSettingsManager(String name, File file1) {
        this.c = file1;
        this.player = name;
        if (file1.exists()) {
            try {
                this.setter.load(new FileInputStream(file1));
            } catch (Exception exception) {
                a.log(Level.WARNING, "Failed to load settings file " + file1, exception);
                this.a();
            }
        } else {
            a.log(Level.WARNING, "Settings file " + file1 + " does not exist");
            this.a();
        }
    }

    public void a() {
        a.log(Level.INFO, "Generating new settings file for " + this.player);
        this.b();
    }

    public void b() {
        try {
            this.setter.store(new FileOutputStream(this.c), "Player settings " + this.player);
        } catch (Exception exception) {
            a.log(Level.WARNING, "Failed to save " + this.c, exception);
        }
    }

    public String a(String s, String s1) {
        if (!this.setter.containsKey(s)) {
            this.setter.setProperty(s, s1);
            this.b();
        }

        return this.setter.getProperty(s, s1);
    }

    public int a(String s, int i) {
        try {
            return Integer.parseInt(this.a(s, "" + i));
        } catch (Exception exception) {
            this.setter.setProperty(s, "" + i);
            return i;
        }
    }

    public boolean a(String s, boolean flag) {
        try {
            return Boolean.parseBoolean(this.a(s, "" + flag));
        } catch (Exception exception) {
            this.setter.setProperty(s, "" + flag);
            return flag;
        }
    }
}
