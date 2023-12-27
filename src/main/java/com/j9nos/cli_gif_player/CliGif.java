package com.j9nos.cli_gif_player;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;

public class CliGif {
    private static final long SLEEP = 100;
    private final ArrayList<CliFrame> frames;

    public CliGif(final String url) throws IOException {
        frames = new ArrayList<>();
        process(url);
    }

    private void process(final String url) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(url);
             ImageInputStream imageInputStream = ImageIO.createImageInputStream(fileInputStream)) {
            final Iterator<ImageReader> reader = ImageIO.getImageReaders(imageInputStream);
            while (reader.hasNext()) {
                final ImageReader imageReader = reader.next();
                imageReader.setInput(imageInputStream);
                final int numberOfFrames = imageReader.getNumImages(true);
                for (int i = 0; i < numberOfFrames; i++) {
                    frames.add(new CliFrame(imageReader.read(i)));
                }
            }
        }
    }

    private Runnable displayFrames() {
        return () -> {
            for (final CliFrame frame : frames) {
                frame.print();
                try {
                    Thread.sleep(SLEEP);
                } catch (final InterruptedException ignored) {
                }
                CliFrame.clear();
            }
        };
    }

    public void play() {
        Executors.newSingleThreadExecutor().execute(displayFrames());
    }

}
