package com.jadencode.main.renderengine.particles;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.toolbox.InsertionSort;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.util.*;

/**
 * Created by gtrpl on 7/8/2016.
 */
public class ParticleMaster {
    private static Map<ParticleTexture, List<Particle>> particles = new HashMap<>();
    private static ParticleRenderer renderer;

    public static void init(Loader loader, Matrix4f projection) {
        renderer = new ParticleRenderer(loader, projection);
    }
    public static void update(Camera camera) {
        Iterator<Map.Entry<ParticleTexture, List<Particle>>> mapIterator = particles.entrySet().iterator();

        while(mapIterator.hasNext()) {
            Map.Entry<ParticleTexture, List<Particle>> entry = mapIterator.next();
            List<Particle> list = entry.getValue();
            Iterator<Particle> listIterator = list.iterator();
            while(listIterator.hasNext()) {
                Particle p = listIterator.next();
                if(p.update(camera)) {
                    listIterator.remove();
                    if(list.isEmpty()) mapIterator.remove();
                }
            }
            if(entry.getKey().getBlendingFunction() == GL11.GL_ONE_MINUS_SRC_ALPHA) InsertionSort.sortHighToLow(list);
        }
    }
    public static void renderParticles(Camera camera) {
        renderer.render(particles, camera);
    }
    public static void cleanup() {
        renderer.cleanUp();
    }
    public static void addParticle(Particle particle) {
        particles.putIfAbsent(particle.getTexture(), new ArrayList<>());
        particles.get(particle.getTexture()).add(particle);
    }
}