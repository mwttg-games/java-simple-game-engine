package com.github.mwttg.sjge.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Read a text file
 */
public class ReadTextFile {

    private static final Logger LOG = LoggerFactory.getLogger(ReadTextFile.class);

    /**
     * reads a text file from the resource folder
     *
     * @param filename path to the resource file
     * @return the file's content
     * @throws IOException if read fails for any reason
     */
    public static Collection<String> fromResources(final String filename) throws IOException {
        LOG.debug("Try to read file: " + filename);
        final var classLoader = ClassLoader.getSystemClassLoader();
        try (final InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IOException("No InputStream available for file: " + filename);
            }

            try (final InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
                try (final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    return bufferedReader
                            .lines()
                            .collect(Collectors.toList());
                }
            }
        }
    }
}
