package com.jadencode.main;

import com.jadencode.main.content.ContentLoader;
import com.jadencode.main.generate.character.viking.VikingCharacterGenerator;
import com.jadencode.main.generate.character.viking.VikingSettlementGenerator;
import com.jadencode.main.generate.item.ItemGenerator;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.renderengine.gui.GuiRenderer;
import com.jadencode.main.renderengine.gui.GuiTexture;
import com.jadencode.main.renderengine.gui.TextMaster;
import com.jadencode.main.renderengine.terrain.*;
import com.jadencode.main.renderengine.toolbox.*;
import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.MasterRenderer;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.entities.Player;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.textures.ModelTexture;
import com.jadencode.main.renderengine.textures.TerrainTexture;
import com.jadencode.main.renderengine.textures.TerrainTexturePack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

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
        TextMaster.init(loader);

        FontType font = new FontType(loader.loadFontAtlas("timesEffect"), new File("res/timesEffect.fnt"));
        GuiText text = new GuiText("This is just a test", 3, font, new Vector2f(0.5F, 0.5F), 0.5F, true);
        text.setColour(0.1F, 0.1F, 0.1F);

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
        Light sun = new Light(new Vector3f(0, 1000, -7000), new Vector3f(1F, 1F, 1F));
        List<Light> lights = new ArrayList<>();
        lights.add(sun);
        lights.add(new Light(new Vector3f(185, 10, -293), new Vector3f(2, 0, 0), new Vector3f(1, 0.001F, 0.002F)));
//        lights.add(new Light(new Vector3f(370, 17, -300), new Vector3f(0, 2, 2), new Vector3f(1, 0.01F, 0.002F)));
//        lights.add(new Light(new Vector3f(293, 7, -305), new Vector3f(2, 2, 0), new Vector3f(1, 0.01F, 0.002F)));

        Entity entity = new Entity(
                new TexturedModel(objLoader.loadObjModel("Stall"), new ModelTexture(loader.loadTexture("models/Stall"))),
                new Vector3f(0, 0, -10), new Vector3f(0, 45, 0), new Vector3f(1, 1, 1));

        MasterRenderer renderer = new MasterRenderer(loader);
        List<Terrain> terrains = new ArrayList<>();

        List<Entity> entities = new ArrayList<>();
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");
        terrains.add(terrain);

        Player player = new Player(entity.getModel(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        Camera camera = new Camera(player);
        entities.add(player);
        entities.add(entity);

        List<Entity> normalMapEntities = new ArrayList<>();
        TexturedModel barrelModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("barrel", loader), new ModelTexture(loader.loadTexture("models/barrel")));
//        barrelModel.getTexture().setShineDamper(10);
//        barrelModel.getTexture().setReflectivity(0.5F);
        barrelModel.getTexture().setNormalMap(loader.loadTexture("models/barrelNormal"));

        normalMapEntities.add(new Entity(barrelModel, new Vector3f(0, 10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));

        WaterFrameBuffers fbos = new WaterFrameBuffers();

        WaterShader waterShader = new WaterShader();
        WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), fbos);
        List<WaterTile> waters = new ArrayList<>();
        WaterTile water = new WaterTile(75, -125, 1);
        waters.add(water);

        List<GuiTexture> guis = new ArrayList<>();
        GuiRenderer guiRenderer = new GuiRenderer(loader);

        MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);

        while (!display.isCloseRequested()) {
            Time.update();
            camera.move();
            player.move(terrain);
            picker.update();

            GL11.glEnable(GL30.GL_CLIP_DISTANCE0);

            fbos.bindReflectionFrameBuffer();
            float distance = 2 * (camera.getPosition().y - water.getTranslation().getY());
            camera.getPosition().y -= distance;
            camera.invertPitch();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, 1, 0, -water.getTranslation().getY() + 1F));
            camera.getPosition().y += distance;
            camera.invertPitch();

            fbos.bindRefractionFrameBuffer();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, water.getTranslation().getY()));

            GL11.glDisable(GL30.GL_CLIP_DISTANCE0);

            fbos.unbindCurrentFrameBuffer();

            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, 1, 0, 10000));
            waterRenderer.render(waters, camera, sun);
            guiRenderer.render(guis);

            TextMaster.render();

            display.update();
        }
        TextMaster.cleanUp();
        fbos.cleanUp();
        waterShader.cleanUp();
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
