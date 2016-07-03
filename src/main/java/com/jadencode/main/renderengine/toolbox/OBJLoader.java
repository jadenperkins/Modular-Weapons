package com.jadencode.main.renderengine.toolbox;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.models.RawModel;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class OBJLoader {
    private final Loader loader;

    public OBJLoader(Loader loader) {
        this.loader = loader;
    }
    public RawModel loadObjModel(String fileName) {
        try {
            FileReader fileReader = new FileReader(new File(String.format("res/models/%s.obj", fileName)));
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            List<Vector3f> vertices = new ArrayList<>();
            List<Vector2f> textures = new ArrayList<>();
            List<Vector3f> normals = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();
            float[] verticesArray;
            float[] normalsArray;
            float[] textureArray;
            int[] indicesArray;

            while(true) {
                line = reader.readLine();
                String[] currentLine = line.split(" ");
                if(line.startsWith("v ")) {
                    float x = Float.parseFloat(currentLine[1]);
                    float y = Float.parseFloat(currentLine[2]);
                    float z = Float.parseFloat(currentLine[3]);
                    Vector3f vertex = new Vector3f(x, y, z);
                    vertices.add(vertex);
                } else if(line.startsWith("vt ")) {
                    float u = Float.parseFloat(currentLine[1]);
                    float v = Float.parseFloat(currentLine[2]);
                    Vector2f vertex = new Vector2f(u, v);
                    textures.add(vertex);
                } else if(line.startsWith("vn ")) {
                    float x = Float.parseFloat(currentLine[1]);
                    float y = Float.parseFloat(currentLine[2]);
                    float z = Float.parseFloat(currentLine[3]);
                    Vector3f vertex = new Vector3f(x, y, z);
                    normals.add(vertex);
                } else if(line.startsWith("f ")) {
                    textureArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }
            while(line != null) {
                if(!line.startsWith("f ")) {
                    line = reader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");
                processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
                line = reader.readLine();
            }
            reader.close();
            verticesArray = new float[vertices.size() * 3];
            indicesArray = new int[indices.size()];
            int vertexPointer = 0;
            for (Vector3f vertex : vertices) {
                verticesArray[vertexPointer++] = vertex.x;
                verticesArray[vertexPointer++] = vertex.y;
                verticesArray[vertexPointer++] = vertex.z;
            }
            for (int i = 0; i < indices.size(); i++) {
                indicesArray[i] = indices.get(i);
            }
            return loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray) {
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
        textureArray[currentVertexPointer * 2] = currentTex.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalsArray[currentVertexPointer * 3] = currentNorm.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
    }
}