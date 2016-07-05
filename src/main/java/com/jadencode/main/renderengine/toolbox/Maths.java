package com.jadencode.main.renderengine.toolbox;

import com.jadencode.main.renderengine.entities.Camera;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class Maths {

    public static float barrycentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
        float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
        float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
        float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
        float l3 = 1F - l1 - l2;
        return l1 * p1.y + l2 * p2.y + l3 * p3.y;
    }

    public static Matrix4f createTransformationMatrix(Transform transform) {
        Vector3f translation = transform.getTranslation();
        Vector3f rotation = transform.getRotation();
        Vector3f scale = transform.getScale();
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rotation.getX()), new Vector3f(1, 0, 0));
        matrix.rotate((float) Math.toRadians(rotation.getY()), new Vector3f(0, 1, 0));
        matrix.rotate((float) Math.toRadians(rotation.getZ()), new Vector3f(0, 0, 1));
        matrix.scale(scale);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        matrix.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0));
        matrix.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0));
        matrix.rotate((float) Math.toRadians(camera.getRoll()), new Vector3f(0, 0, 1));
        Vector3f pos = camera.getPosition();
        Vector3f neg = new Vector3f(-pos.x, -pos.y, -pos.z);
        matrix.translate(neg);
        return matrix;
    }
}
