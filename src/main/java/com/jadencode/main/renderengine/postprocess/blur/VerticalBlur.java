package com.jadencode.main.renderengine.postprocess.blur;

import com.jadencode.main.renderengine.postprocess.ImageRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class VerticalBlur {
	
	private ImageRenderer renderer;
	private VerticalBlurShader shader;
	
	public VerticalBlur(int targetFboWidth, int targetFboHeight){
		shader = new VerticalBlurShader();
		renderer = new ImageRenderer(targetFboWidth, targetFboHeight);
		shader.start();
		shader.TARGET_HEIGHT.load((float)targetFboHeight);
		shader.stop();
	}

	
	public void render(int texture){
		shader.start();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		renderer.renderQuad();
		shader.stop();
	}
	
	public int getOutputTexture(){
		return renderer.getOutputTexture();
	}
	
	public void cleanUp(){
		renderer.cleanUp();
		shader.cleanUp();
	}
}