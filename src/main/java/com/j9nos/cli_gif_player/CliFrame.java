package com.j9nos.cli_gif_player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CliFrame {

    private final ArrayList<Printable> pixels;

    public CliFrame(final BufferedImage image) {
        pixels = new ArrayList<>();
        final int w = image.getWidth();
        final int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pixels.add(PixelCache.closest(new Color(image.getRGB(x, y))));
            }
            pixels.add(PixelCache.breakLine());
        }
    }


    public void print() {
        for (final Printable printable : pixels) {
            printable.print();
        }
    }


}
