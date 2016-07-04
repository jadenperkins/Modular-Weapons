package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectIcon;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class IconEditor extends ContentEditor<ContentObjectIcon> {

    private final JButton selectImage;
    private final JLabel displayLabel;
    private final JFileChooser imageChooser = new JFileChooser();
    private String base64String;

    public IconEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);

        GuiHelper helper = GuiHelper.above(this);

        this.displayLabel = helper.add(new JLabel(), "Display", H_E, V_S, 500, 500, GuiHelper.Align.ABOVE);
        this.imageChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "PNG Files";
            }
        });

        this.selectImage = helper.add(new JButton("Select Image"), H_S, V_E, H_L, H_BTN);
        this.selectImage.addActionListener(e -> {
            this.imageChooser.showOpenDialog(null);
            if (this.imageChooser.getSelectedFile() != null) {
                File iconFile = this.imageChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(iconFile);
                    Image scaled = image.getScaledInstance(image.getWidth() * 8, image.getHeight() * 8, 0);

                    this.displayLabel.setIcon(new ImageIcon(scaled));
                    this.base64String = DatatypeConverter.printBase64Binary(FileUtils.readFileToByteArray(iconFile));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void populate(ContentObjectIcon item) {
        this.base64String = item.getBase64();
        try {
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(this.base64String);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            if (image != null) {
                this.displayLabel.setIcon(new ImageIcon(image.getScaledInstance(image.getWidth() * 8, image.getHeight() * 8, 0)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContentObjectIcon createItem(String name, String owner) {
        return new ContentObjectIcon(name, owner, this.base64String);
    }

    @Override
    public ContentObjectIcon getDefault() {
        return new ContentObjectIcon("", "", "");
    }

    @Override
    public ContentObjectIcon consume(String name, JsonObject json, String owner) {
        return new ContentObjectIcon(name, owner, new JsonHelper(json).getString("base64"));
    }
}