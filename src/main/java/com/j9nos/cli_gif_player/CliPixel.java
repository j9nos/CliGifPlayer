package com.j9nos.cli_gif_player;

import java.awt.*;

public record CliPixel(String code, Color color) implements Printable {
    private static final char PIXEL_REPRESENTATION = '#';
    private static final String RESET = "\u001B[0m";

    @Override
    public void print() {
        System.out.print(code + PIXEL_REPRESENTATION + RESET);
    }
}