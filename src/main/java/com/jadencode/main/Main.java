package com.jadencode.main;

import com.jadencode.main.content.ContentLoader;
import com.jadencode.main.generate.item.ItemGenerator;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.renderengine.audio.AudioMaster;
import com.jadencode.main.renderengine.gui.GuiRenderer;
import com.jadencode.main.renderengine.gui.GuiTexture;
import com.jadencode.main.renderengine.gui.TextMaster;
import com.jadencode.main.renderengine.particles.ParticleMaster;
import com.jadencode.main.renderengine.particles.ParticleSystem;
import com.jadencode.main.renderengine.particles.ParticleTexture;
import com.jadencode.main.renderengine.postprocess.Fbo;
import com.jadencode.main.renderengine.postprocess.PostProcessing;
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
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/19/2015.
 */
public class Main {

    public static final int LEVEL_CAP = 100;

    public static final World theWorld = new World();

    public static void main(String[] args) throws InterruptedException {
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
        Light sun = new Light(new Vector3f(1000000, 1500000, -1000000), new Vector3f(1.3F, 1.3F, 1.3F));
        List<Light> lights = new ArrayList<>();
        lights.add(sun);

        Entity entity = new Entity(
                new TexturedModel(objLoader.loadObjModel("Stall"), new ModelTexture(loader.loadTexture("models/Stall"))),
                new Vector3f(0, 0, -10), new Vector3f(0, 45, 0), new Vector3f(1, 1, 1));

        Player player = new Player(entity.getModel(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        Camera camera = new Camera(player);

        List<Entity> entities = new ArrayList<>();
        entities.add(player);

        List<Terrain> terrains = new ArrayList<>();
        Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap, "hashcode".hashCode());
        terrains.add(terrain);

        Random r = new Random("hashcode".hashCode());

//        for(int i = 0; i < 100; i++) {
//            float x = r.nextFloat() * 800;
//            float z = r.nextFloat() * 800;
//            float y = terrain.getHeight(x, z);
//
//            float ry = r.nextFloat() * 360;
//            Entity entityAdd = new Entity(entity.getModel(), new Vector3f(x, y, z), new Vector3f(0, ry, 0), new Vector3f(2, 2, 2));
//            entities.addNode(entityAdd);
//        }

        Entity pommel = new Entity(
                new TexturedModel(objLoader.loadObjModel("Pommel"), new ModelTexture(loader.loadTexture("models/Pommel"))),
                new Vector3f(0, -10 - 2.8F, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), new Vector3f(0.6f, 0.4F, 0.0F));

        Entity grip = new Entity(
                new TexturedModel(objLoader.loadObjModel("Grip"), new ModelTexture(loader.loadTexture("models/Grip"))),
                new Vector3f(0, -10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), new Vector3f(0.3f, 0.3F, 0.3F));

        Entity hilt = new Entity(
                new TexturedModel(objLoader.loadObjModel("Hilt"), new ModelTexture(loader.loadTexture("models/Hilt"))),
                new Vector3f(0, -10 + 2.8F, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), new Vector3f(0.6f, 0.4F, 0.0F));

        Entity blade = new Entity(
                new TexturedModel(objLoader.loadObjModel("Blade"), new ModelTexture(loader.loadTexture("models/Blade"))),
                new Vector3f(0, -10 + 2.8F + 10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), new Vector3f(0.0F, 0.0F, 0.0F));

        entities.add(pommel);
        entities.add(grip);
        entities.add(hilt);
        entities.add(blade);

        MasterRenderer renderer = new MasterRenderer(loader, camera);
        ParticleMaster.init(loader, renderer.getProjectionMatrix());

        TexturedModel lanternModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("lantern", loader), new ModelTexture(loader.loadTexture("models/lantern")));
        lanternModel.getTexture().setShineDamper(10);
        lanternModel.getTexture().setReflectivity(0.5F);
        lanternModel.getTexture().setSpecularMap(loader.loadTexture("models/lanternS"));

        Entity lantern = new Entity(lanternModel, new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        entities.add(lantern);

        AudioMaster.init();
//        AudioMaster.setListenerData(0, 0, 0);
//        int buffer = AudioMaster.loadSound("./audio/bounce.wav");
//        AL10.alDistanceModel(AL10.AL_INVERSE_DISTANCE_CLAMPED);
//
//        Source source = new Source();
//        source.setLooping(true);
//        source.play(buffer);
//        source.setPosition(0, 0, 0);
//        float xPos = 0;
//
//        char c = ' ';
//        while(c != 'q') {
//            xPos -= 0.02F;
//            source.setPosition(xPos, 0, 0);
//            System.out.println(xPos);
//            Thread.sleep(10);
//        }
//        source.delete();
//        AudioMaster.cleanUp();
//
//        System.exit(0);




        WaterFrameBuffers fbos = new WaterFrameBuffers();
        WaterShader waterShader = new WaterShader();
        WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), fbos);
        List<WaterTile> waters = new ArrayList<>();
        WaterTile water = new WaterTile(75, -125, 1);
        waters.add(water);

        List<GuiTexture> guis = new ArrayList<>();
        GuiRenderer guiRenderer = new GuiRenderer(loader);

//        guis.addNode(new GuiTexture(renderer.getShadowMapTexture(), new Vector2f(0.5F, 0.5F), new Vector2f(0.5F, 0.5F)));

        MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
        ParticleTexture particleTexture = new ParticleTexture(loader.loadTexture("particleAtlas"), 4);
        ParticleSystem system = new ParticleSystem(particleTexture, 50, 25, 0.3F, 4);

        Fbo multisampleFbo = new Fbo(Display.getWidth(), Display.getHeight());
        Fbo outputFbo = new Fbo(Display.getWidth(), Display.getHeight(), Fbo.DEPTH_TEXTURE);
        PostProcessing.init(loader);

        while (!display.isCloseRequested()) {
            Time.update();
            camera.move();
            player.move(terrain);
            picker.update();

//            system.generateParticles(player.getTranslation());
            ParticleMaster.update(camera);

            renderer.renderShadowMap(entities, sun);

            GL11.glEnable(GL30.GL_CLIP_DISTANCE0);

            fbos.bindReflectionFrameBuffer();
            float distance = 2 * (camera.getPosition().y - water.getTranslation().getY());
            camera.getPosition().y -= distance;
            camera.invertPitch();
            renderer.renderScene(entities, terrains, lights, camera, new Vector4f(0, 1, 0, -water.getTranslation().getY() + 1F));
            camera.getPosition().y += distance;
            camera.invertPitch();

            fbos.bindRefractionFrameBuffer();
            renderer.renderScene(entities, terrains, lights, camera, new Vector4f(0, -1, 0, water.getTranslation().getY()));

            GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
            fbos.unbindCurrentFrameBuffer();

            multisampleFbo.bindFrameBuffer();
            renderer.renderScene(entities, terrains, lights, camera, new Vector4f(0, 1, 0, 10000));
            waterRenderer.render(waters, camera, sun);
            ParticleMaster.renderParticles(camera);
            multisampleFbo.unbindFrameBuffer();
//            multisampleFbo.resolveToScreen();
            multisampleFbo.resolveToFbo(outputFbo);

            PostProcessing.doPostProcessing(outputFbo.getColourTexture());

            guiRenderer.render(guis);
            TextMaster.render();

            display.update();
        }
        PostProcessing.cleanUp();
        multisampleFbo.cleanUp();
        outputFbo.cleanUp();
        ParticleMaster.cleanup();
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
