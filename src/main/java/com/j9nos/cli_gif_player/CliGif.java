package com.j9nos.cli_gif_player;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CliGif {
    private static final int MAX_BOUNDARY = 100;
    private static final int MIN_BOUNDARY = 5;
    private static final long SLEEP = 100;
    private final ArrayList<CliFrame> frames;

    public CliGif(final String url) throws IOException {
        frames = new ArrayList<>();
        process(url);
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
        return Math.max(
                Math.min(original, MAX_BOUNDARY),
                MIN_BOUNDARY
        );
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
                    final BufferedImage rawFrame = imageReader.read(i);
                    final BufferedImage scaledFrame = scale(rawFrame);
                    final CliFrame cliFrame = new CliFrame(scaledFrame);
                    frames.add(cliFrame);
                }
            }
        }
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void play() throws InterruptedException {
        for (final CliFrame cliFrame : frames) {
            cliFrame.print();
            Thread.sleep(SLEEP);
            clear();
        }
    }

}
