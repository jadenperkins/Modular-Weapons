package com.jadencode.main;

import com.jadencode.main.content.ContentLoader;
import com.jadencode.main.generate.character.viking.VikingCharacterGenerator;
import com.jadencode.main.generate.character.viking.VikingSettlementGenerator;
import com.jadencode.main.generate.item.ItemGenerator;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.renderengine.gui.GuiRenderer;
import com.jadencode.main.renderengine.gui.GuiTexture;
import com.jadencode.main.renderengine.toolbox.DisplayManager;
import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.MasterRenderer;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.entities.Player;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.terrain.Terrain;
import com.jadencode.main.renderengine.textures.ModelTexture;
import com.jadencode.main.renderengine.textures.TerrainTexture;
import com.jadencode.main.renderengine.textures.TerrainTexturePack;
import com.jadencode.main.renderengine.toolbox.OBJLoader;
import com.jadencode.main.renderengine.toolbox.Time;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 1/19/2015.
 */
public class Main {

    public static final int LEVEL_CAP = 100;

    public static final World theWorld = new World();

    public static void main(String[] args) {
        DisplayManager display = new DisplayManager();
        Loader loader = new Loader();

        TerrainTexture background = new TerrainTexture(loader.loadTexture("grassy2"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(background, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        OBJLoader objLoader = new OBJLoader(loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("grass"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        Light light = new Light(new Vector3f(0, 1000, -7000), new Vector3f(1F, 1F, 1F));
        List<Light> lights = new ArrayList<>();
        lights.add(light);
        lights.add(new Light(new Vector3f(185, 10, -293), new Vector3f(2, 0, 0), new Vector3f(1, 0.001F, 0.002F)));
//        lights.add(new Light(new Vector3f(370, 17, -300), new Vector3f(0, 2, 2), new Vector3f(1, 0.01F, 0.002F)));
//        lights.add(new Light(new Vector3f(293, 7, -305), new Vector3f(2, 2, 0), new Vector3f(1, 0.01F, 0.002F)));

        Entity entity = new Entity(
                new TexturedModel(objLoader.loadObjModel("Stall"), new ModelTexture(loader.loadTexture("models/Stall"))),
                new Vector3f(0, 0, -10), new Vector3f(0, 45, 0), new Vector3f(1, 1, 1));

        MasterRenderer renderer = new MasterRenderer(loader);
        List<Terrain> terrains = new ArrayList<>();

//        int rad = 3;
//        for (int i = -rad; i <= rad; i++) {
//            for (int j = -rad; j <= rad; j++) {
//                terrains.add(new Terrain(i, j, loader, texturePack, blendMap, "heightmap"));
//            }
//        }

        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

        Player player = new Player(entity.getModel(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        Camera camera = new Camera(player);

        List<GuiTexture> guis = new ArrayList<>();
        guis.add(new GuiTexture(loader.loadTexture("crossbow"), new Vector2f(0.5F, 0.5F), new Vector2f(0.25F, 0.25F)));
        GuiRenderer guiRenderer = new GuiRenderer(loader);

//        passTextureCoords = (textureCoords / numberOfRows) + offset;
        while (!display.isCloseRequested()) {
            Time.update();
            camera.move();
            player.move(terrain);
            renderer.processEntity(player);
            renderer.processEntity(entity);
            renderer.processTerrain(terrain);
//            terrains.forEach(renderer::processTerrain);
            renderer.render(lights, camera);
            guiRenderer.render(guis);
            display.update();
        }
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        display.destroy();

        System.exit(0);

        ContentLoader.load();

        int itemLevel = theWorld.getRNG().nextInt(50) + 1;

        Item item = new ItemGenerator().generate(theWorld.getRNG(), itemLevel);
        System.out.println(item.getDisplayName());
        item.printItemCard();
    }
}
