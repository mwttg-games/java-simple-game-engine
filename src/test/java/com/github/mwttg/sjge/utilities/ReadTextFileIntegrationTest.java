package com.github.mwttg.sjge.utilities;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadTextFileIntegrationTest {

    @Test
    public void testFrom() throws Exception {
        final var filename = "valid-textfile.txt";
        final var actual = ReadTextFile.fromResources(filename);
        final var expected = List.of("1. line", "2. line", "3. line");

        assertThat(actual).isEqualTo(expected);
    }

    @Test(expectedExceptions = IOException.class)
    public void testFrom_FileDoesNotExist() throws Exception {
        final var filename = "does-not-exist.txt";
        ReadTextFile.fromResources(filename);
    }
}