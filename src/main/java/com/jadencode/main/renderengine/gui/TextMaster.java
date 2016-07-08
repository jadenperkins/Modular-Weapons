package com.jadencode.main.renderengine.gui;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.toolbox.FontType;
import com.jadencode.main.renderengine.toolbox.GuiText;
import com.jadencode.main.renderengine.toolbox.TextMeshData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gtrpl on 7/7/2016.
 */
public class TextMaster {
    private static Loader loader;
    private static Map<FontType, List<GuiText>> texts = new HashMap<>();
    private static FontRenderer renderer;

    public static void init(Loader l) {
        renderer = new FontRenderer();
        loader = l;
    }
    public static void render() {
        renderer.render(texts);
    }
    public static void loadText(GuiText text) {
        FontType font = text.getFont();
        TextMeshData data = font.loadText(text);
        int vao = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
        text.setMeshInfo(vao, data.getVertexCount());
        texts.putIfAbsent(font, new ArrayList<>());
        texts.get(font).add(text);
    }
    public static void removeText(GuiText text) {
        texts.get(text.getFont()).remove(text);
        if(texts.get(text.getFont()).isEmpty()) texts.remove(text.getFont());
    }
    public static void cleanUp() {
        renderer.cleanUp();
    }
}