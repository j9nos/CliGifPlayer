package com.j9nos.cli_gif_player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public final class PixelCache {
    private static final ArrayList<CliPixel> PIXELS = new ArrayList<>(List.of(
            new CliPixel("\u001B[30m", new Color(0, 0, 0)),
            new CliPixel("\u001B[31m", new Color(255, 0, 0)),
            new CliPixel("\u001B[32m", new Color(0, 255, 0)),
            new CliPixel("\u001B[33m", new Color(255, 255, 0)),
            new CliPixel("\u001B[34m", new Color(0, 0, 255)),
            new CliPixel("\u001B[35m", new Color(255, 0, 255)),
            new CliPixel("\u001B[36m", new Color(0, 255, 255)),
            new CliPixel("\u001b[37m", new Color(255, 255, 255))
    ));

    private static final Printable BREAK_LINE = System.out::println;

    private PixelCache() {
    }

    public static Printable breakLine() {
        return BREAK_LINE;
    }


    private static double distance(final Color color1, final Color color2) {
        final double redDifference = color1.getRed() - color2.getRed();
        final double greenDifference = color1.getGreen() - color2.getGreen();
        final double blueDifference = color1.getBlue() - color2.getBlue();
        return Math.sqrt(Math.pow(redDifference, 2) + Math.pow(greenDifference, 2) + Math.pow(blueDifference, 2));
    }

    public static Printable closest(final Color color) {
        final TreeMap<Double, CliPixel> pixels = new TreeMap<>();
        for (final CliPixel cliPixel : PIXELS) {
            final double distance = distance(color, cliPixel.color());
            pixels.put(distance, cliPixel);
        }
        return pixels.firstEntry().getValue();
    }


}
