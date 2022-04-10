package com.netcetera.ncau.java17.overthehood;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.Test;

class FileSystemsTests {

  @Test
  void oldWay() throws IOException, URISyntaxException {
    Path jarFile = getJarFile();
    assertTrue(Files.exists(jarFile));

    try (FileSystem fileSystem = FileSystems.newFileSystem(new URI("jar:" + jarFile.toUri()), Map.of())) {
      Path manifest = fileSystem.getPath("META-INF", "MANIFEST.MF");
      assertTrue(Files.exists(manifest));
    }
  }

  @Test
  void newWay() throws IOException {
    Path jarFile = getJarFile();
    assertTrue(Files.exists(jarFile));

    try (FileSystem fileSystem = FileSystems.newFileSystem(jarFile)) {
      Path manifest = fileSystem.getPath("META-INF", "MANIFEST.MF");
      assertTrue(Files.exists(manifest));
    }
  }

  private Path getJarFile() {
    Path userHome = Paths.get(System.getProperty("user.home"));
    Path m2Repo = userHome.resolve(".m2/repository");
    return m2Repo.resolve("org/junit/jupiter/junit-jupiter-api/5.8.2/junit-jupiter-api-5.8.2.jar");
  }

}
