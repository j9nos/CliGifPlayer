package com.j9nos.cli_gif_player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CliFrame {
    private static final int MAX_BOUNDARY = 100;
    private static final int MIN_BOUNDARY = 5;
    private final ArrayList<Printable> pixels;

    public CliFrame(final BufferedImage rawImage) {
        pixels = new ArrayList<>();
        final BufferedImage image = scale(rawImage);
        final int w = image.getWidth();
        final int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pixels.add(CliPixelToolbox.closest(new Color(image.getRGB(x, y))));
            }
            pixels.add(CliPixelToolbox.breakLine());
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    private BufferedImage scale(final BufferedImage image) {
        final int scaledWidth = scale(image.getWidth());
        final int scaledHeight = scale(image.getHeight());
        final BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();
        return scaledImage;
    }

    private int scale(final int original) {
        return Math.max(Math.min(original, MAX_BOUNDARY), MIN_BOUNDARY);
    }

    public void print() {
        for (final Printable printable : pixels) {
            printable.print();
        }
    }


}
