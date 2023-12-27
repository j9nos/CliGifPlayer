package com.j9nos.cli_gif_player;

import java.awt.*;

public record CliPixel(String code, Color color) implements Printable {
    @Override
    public void print() {
        System.out.print(code + ' ' + CliPixelToolbox.reset());
    }
}