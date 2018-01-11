package com.main.plugins.modules;

import com.main.constants.Icons;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.component.BuilderLabel;
import com.main.plugins.pipeline.PipelineObjectIcon;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleIconCreator extends Module<PipelineObjectIcon> {
    private static final String DISPLAY_LABEL = "displayLabel";

    public ModuleIconCreator() {
        super("Icons", -2, Plugin::getIcons);
    }
    @Override
    public PipelineObjectIcon createItem(ContentEditor<PipelineObjectIcon> contentEditor, String name) {
        String base64 = contentEditor.getComponent(DISPLAY_LABEL).getAsLabel().getDataString();
        return new PipelineObjectIcon(name, base64);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectIcon> contentEditor) {
        contentEditor.addComponent(DISPLAY_LABEL, new BuilderLabel(), H_E, V_S, 500, 500);

        JButton selectImage = new JButton("Select Image");
        selectImage.setLocation(H_S, V_E);
        selectImage.setSize(H_L, H_BTN);
        contentEditor.add(selectImage);
        selectImage.addActionListener(event -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".png");
                }
                @Override
                public String getDescription() {
                    return "PNG Files";
                }
            });
            chooser.showOpenDialog(null);
            File iconFile = chooser.getSelectedFile();
            if (iconFile == null) return;
            try {
                BufferedImage image = ImageIO.read(iconFile);
                Image scaled = image.getScaledInstance(image.getWidth() * 8, image.getHeight() * 8, 0);
                String base64 = DatatypeConverter.printBase64Binary(FileUtils.readFileToByteArray(iconFile));
                contentEditor.getComponent(DISPLAY_LABEL).getAsLabel().setDataString(base64);
                contentEditor.getComponent(DISPLAY_LABEL).getAsLabel().setIcon(new ImageIcon(scaled));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectIcon> contentEditor, PipelineObjectIcon pipelineObject) {
        String base64 = pipelineObject.getBase64();
        if (base64 == null) return;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byte[] imageBytes = Base64.decode(base64);
            byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            if (image == null) return;
            ImageIcon scaled = new ImageIcon(image.getScaledInstance(image.getWidth() * 8, image.getHeight() * 8, 0));
            contentEditor.getComponent(DISPLAY_LABEL).getAsLabel().setIcon(scaled);
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(byteArrayInputStream);
        }
    }
    @Override
    public PipelineObjectIcon getDefault() {
        return new PipelineObjectIcon("", "");
    }

    @Override
    public void consume(PipelineObjectIcon object) {
        BufferedImage image = decodeToImage(object.getBase64());
        Icons.register(object.getName(), image);
    }
    private static BufferedImage decodeToImage(String imageString) {
        try {
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}