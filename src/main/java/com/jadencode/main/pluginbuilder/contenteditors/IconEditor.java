package com.jadencode.main.pluginbuilder.contenteditors;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemColor;
import com.jadencode.main.pluginbuilder.items.ItemIcon;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class IconEditor extends ContentEditor<ItemIcon> {

    private final JButton selectImage;
    private final JLabel displayLabel;
    private String base64String;
    private final JFileChooser imageChooser = new JFileChooser();

    public IconEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);

        this.displayLabel = this.create(new JLabel(), 220, 10, 200, 200);
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

        this.selectImage = this.create(new JButton("Select Image"), 10, 120, 200, 40);
        this.selectImage.addActionListener(e -> {
            this.imageChooser.showOpenDialog(null);
            if(this.imageChooser.getSelectedFile() != null) {
                File iconFile = this.imageChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(iconFile);
                    Image scaled = image.getScaledInstance(image.getWidth() * 8, image.getHeight() * 8, 0);

                    this.displayLabel.setIcon(new ImageIcon(scaled));
//                    BufferedImage image = ImageIO.read(iconFile);
//                    this.displayPanel.getGraphics().drawImage(image.getScaledInstance(image.getWidth() * 4, 4 * image.getHeight(), 0), 0, 0, image.getWidth(), image.getHeight(), null);
                    this.base64String = Base64.encode(FileUtils.readFileToByteArray(iconFile));
//                    System.out.println(this.base64String);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    @Override
    public void populate(ItemIcon item) {
        this.base64String = item.getBase64();
    }
    @Override
    public ItemIcon createItem(String name) {
        return new ItemIcon(name, this.base64String);
    }
    @Override
    public ItemIcon getDefault() {
        return new ItemIcon("", "");
    }
}