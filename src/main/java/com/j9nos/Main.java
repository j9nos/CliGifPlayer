package com.j9nos;

import com.j9nos.cli_gif_player.CliGif;

import java.io.IOException;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws IOException, InterruptedException {
        final CliGif cliGif = new CliGif(args[0]);
        final int maxLoop = Integer.parseInt(args[1]);
        for (int i = 0; i < maxLoop; i++) {
            cliGif.play();
        }
    }
}