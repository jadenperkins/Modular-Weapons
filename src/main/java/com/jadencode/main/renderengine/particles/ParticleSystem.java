package com.jadencode.main.renderengine.particles;

import com.jadencode.main.renderengine.toolbox.Time;
import org.lwjgl.util.vector.Vector3f;


public class ParticleSystem {
	
	private float pps;
	private float speed;
	private float gravityComplient;
	private float lifeLength;
    private ParticleTexture texture;
	
	public ParticleSystem(ParticleTexture texture, float pps, float speed, float gravityComplient, float lifeLength) {
		this.texture = texture;
        this.pps = pps;
		this.speed = speed;
		this.gravityComplient = gravityComplient;
		this.lifeLength = lifeLength;
	}
	
	public void generateParticles(Vector3f systemCenter){
		float delta = Time.getFrameTimeSeconds();
		float particlesToCreate = pps * delta;
		int count = (int) Math.floor(particlesToCreate);
		float partialParticle = particlesToCreate % 1;
		for(int i=0;i<count;i++){
			emitParticle(systemCenter);
		}
		if(Math.random() < partialParticle){
			emitParticle(systemCenter);
		}
	}
	
	private void emitParticle(Vector3f center){
		float dirX = (float) Math.random() * 2f - 1f;
		float dirZ = (float) Math.random() * 2f - 1f;
		Vector3f velocity = new Vector3f(dirX, 1, dirZ);
		velocity.normalise();
		velocity.scale(speed);
        new Particle(this.texture, new Vector3f(center), 0F, new Vector3f(1, 1, 1), velocity, lifeLength, gravityComplient);
	}
}