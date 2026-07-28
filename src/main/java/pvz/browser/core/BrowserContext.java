package pvz.browser.core;

import com.badlogic.gdx.files.FileHandle;
import pvz.libpvz.pam.PamPlayer;
import pvz.libpvz.textures.TextureBank;

public class BrowserContext {
    public static TextureBank textures;
    public static PamPlayer player;
    public static BrowserAppSettings settings;

    public static void init() {
        // Point the browser at a PvZ2 asset dump via PVZ_ASSETS (env) or -Dpvz.assets=... ; otherwise fall
        // back to the layout relative to the project root (asset dump is the parent of this repo).
        String root = System.getenv("PVZ_ASSETS");
        if (root == null || root.isEmpty()) {
            root = System.getProperty("pvz.assets");
        }
        if (root != null && !root.isEmpty()) {
            settings = new BrowserAppSettings(
                    root + "/Base Assets/IMAGES", root + "/Assets/RESOURCES.json",
                    root + "/Base Assets/ATLASES", root + "/Exports");
        } else {
            settings = new BrowserAppSettings(
                    "/home/parsa/Projects/PVZ2 Assets/Base Assets/IMAGES", "/home/parsa/Projects/PVZ2 Assets/Assets/RESOURCES.json", "/home/parsa/Projects/PVZ2 Assets/Base Assets/ATLASES", "../Exports");
        }

        // Pass the base asset directory to the library. The library will automatically look for
        // 'pam', 'atlases', and 'resources.json' inside this directory.
        FileHandle assetsFolder = new FileHandle(settings.assetsRootPath).parent();
        
        textures = new TextureBank("768", assetsFolder);
        player = new PamPlayer(textures, assetsFolder);
    }
}
