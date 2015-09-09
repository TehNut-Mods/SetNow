package tehnut.setnow;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    // Settings
    public static boolean displayChatMessage;
    public static int[] blacklistDims;
    public static int[] blacklistDimsDefault = { -1, 1 };

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "General";
        displayChatMessage = config.getBoolean("displayChatMessage", category, true, "Display a chat message on successful setting of spawn.");
        blacklistDims = config.get(category, "blacklistDims", blacklistDimsDefault, "Dimensions to not allow setting in. Probably want all that cause bed explosions here.").getIntList();

        config.save();
    }
}
