package com.github.mwttg.sjge.utilities;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.testng.annotations.Test;

public class FileUtilitiesTest {

  @Test
  public void testFrom() {
    final var filename = "valid-textfile.txt";
    final var actual = FileUtilities.readFromResources(filename);
    final var expected = List.of("1. line", "2. line", "3. line");

    assertThat(actual).isEqualTo(expected);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testFrom_FileDoesNotExist() {
    final var filename = "does-not-exist.txt";
    FileUtilities.readFromResources(filename);
  }
}