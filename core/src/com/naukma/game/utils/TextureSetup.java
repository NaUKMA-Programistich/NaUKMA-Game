package com.naukma.game.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Class setup texture
 */
public class TextureSetup {

    /**
     * Start method process
     *
     * @param args cl
     */
    public static void main(String[] args) {
        TexturePacker.process("/home/programistich/Projects/Game/core/assets/images", "/home/programistich/Projects/Game/core/assets/images/textures", "textures.pack");
    }
}