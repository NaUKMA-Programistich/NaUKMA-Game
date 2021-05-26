package com.naukma.game.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TextureSetup {

    public static void main(String[] args) {
        TexturePacker.process("/home/programistich/Projects/Game/core/assets/images", "/home/programistich/Projects/Game/core/assets/images/textures", "textures.pack");
    }
}