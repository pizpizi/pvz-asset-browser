package pvz.browser.screens.atlases;

import pvz.browser.widgets.VirtualizedList;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import pvz.browser.core.BrowserContext;
import pvz.browser.core.Screens;
import pvz.browser.core.UiManager;
import pvz.libpvz.textures.ResourceIndex;

public class AtlasList extends Table {
    private static final float CARD_HEIGHT = 72f;

    private final Skin skin = UiManager.skin;
    private final ArrayList<String> atlasIds = new ArrayList<>();

    private TextField search;
    private String currentAtlas;

    private VirtualizedList<String> list;

    public AtlasList() {
        super(UiManager.skin);

        indexAtlases();
        setupUi();
        applyFilter();
    }

    private void indexAtlases() {
        ResourceIndex index = BrowserContext.textures.resourceIndex();
        for (String id : index.atlasIds()) {
            ResourceIndex.AtlasEntry atlas = index.atlas(id);
            if (atlas == null || atlas.path == null) {
                continue;
            }
            if (!displayName(id).contains("_768_") && !atlas.path.contains("_768_")) {
                continue;
            }
            if (index.isAnimationAtlas(id)) {
                continue;
            }
            atlasIds.add(id);
        }
        atlasIds.sort(Comparator.comparing(id -> id.toLowerCase(Locale.ROOT)));
    }

    private void setupUi() {
        
        setBackground("image_ui_if_bundle_reward_multiplier_bg_10");
        pad(10);


        search = new TextField("", skin);
        search.setMessageText("Search atlases...");

        list = new VirtualizedList<>(CARD_HEIGHT, new VirtualizedList.CardFactory<String>() {
            @Override
            public Actor create(String item, int index) {
                return new AtlasCard(atlasIds.get(index)) {
                    @Override
                    public void onClick() {
                        onSelect(item);
                    }
                };
            }
        });

        ScrollPane scroll = new ScrollPane(list, skin);
        scroll.setFadeScrollBars(false);
        scroll.setScrollingDisabled(true, false);

        scroll.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                getStage().setScrollFocus(scroll);
            }
        });

        add(search).growX().row();
        add(scroll).grow().row();

        search.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent e, Actor a) {
                applyFilter();
            }
        });
    }

    private void applyFilter() {
        String q = search.getText() == null ? "" : search.getText().trim().toLowerCase(Locale.ROOT);
        Array<String> items = new Array<>();
        for (String id : atlasIds) {
            String name = cleanName(id);
            if (!q.isEmpty() && !name.toLowerCase(Locale.ROOT).contains(q)) {
                continue;
            }
            items.add(id);
        }
        list.setItems(items);
    }

    private String displayName(String id) {
        ResourceIndex.AtlasEntry atlas = BrowserContext.textures.resourceIndex().atlas(id);
        if (atlas == null || atlas.path == null) {
            return id;
        }
        String path = atlas.path.replace('\\', '/');
        int slash = path.lastIndexOf('/');
        return slash >= 0 ? path.substring(slash + 1) : path;
    }

    private String cleanName(String id) {
        return displayName(id).replaceFirst("_(768|1536)_\\d+$", "");
    }

    public void onSelect(String atlasId) {

    }
}
