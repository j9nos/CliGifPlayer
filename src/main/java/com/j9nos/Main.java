package com.j9nos;

import com.j9nos.cli_gif_player.CliGif;

import java.io.IOException;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        final CliGif cliGif = new CliGif(args[0]);
        cliGif.play();
    }
}