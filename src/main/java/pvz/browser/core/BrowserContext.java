package pvz.browser.core;

import com.badlogic.gdx.files.FileHandle;
import pvz.libpvz.pam.PamPlayer;
import pvz.libpvz.textures.TextureBank;

public class BrowserContext {
    public static TextureBank textures;
    public static PamPlayer player;
    public static BrowserAppSettings settings;

    public static void init() {
        String root = System.getProperty("pvz.assets");
        if (root == null || root.isEmpty()) {
            root = System.getenv("PVZ_ASSETS");
        }
        if (root == null || root.isEmpty()) {
            root = ".";
        }

        FileHandle rootDir = new FileHandle(root);

        FileHandle imagesDir = rootDir.child("IMAGES");

        FileHandle atlasesDir = rootDir.child("ATLASES");

        FileHandle resourcesFile = rootDir.child("RESOURCES.json");
        if (!resourcesFile.exists()) resourcesFile = rootDir.child("resources.json");

        FileHandle exportsDir = rootDir.child("Exports");

        settings = new BrowserAppSettings(
                imagesDir.path(),
                resourcesFile.path(),
                atlasesDir.path(),
                exportsDir.path()
        );

        System.out.println(settings.exportsRootPath);

        FileHandle assetsFolder = imagesDir.parent();
        textures = new TextureBank("768", assetsFolder);
        player = new PamPlayer(textures, assetsFolder);
    }
}
