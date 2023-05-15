package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the FileTypeVisitor class and its associated methods
 */
class FileTypeVisitorTest {

  ArrayList<String> onlyMd;
  ArrayList<String> withPdf;
  Path path;
  Path arraysPath;
  File arrays;
  File notes;
  File test;
  File vectors;
  File duedates;
  File java;
  ArrayList<File> mdFiles;
  ArrayList<File> bothFiles;
  FileTypeVisitor md;
  FileTypeVisitor both;
  FileVisitResult result;
  BasicFileAttributes attrs;

  /**
   * Sets the initial values used for testing
   *
   */
  @BeforeEach
  public void setup() {
    onlyMd = new ArrayList<>(List.of(".md"));
    withPdf = new ArrayList<>(Arrays.asList(".md", ".pdf"));
    path = Path.of("src/tests/resources/notes-root");
    arraysPath = Path.of("src/tests/resources/notes-root/arrays.md");
    arrays = arraysPath.toFile();
    notes = Path.of("src/tests/resources/notes-root/notes.pdf").toFile();
    test = Path.of("src/tests/resources/notes-root/test.md").toFile();
    vectors = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    duedates = Path.of("src/tests/resources/notes-root/lecture-notes/duedates.pdf").toFile();
    java = Path.of("src/tests/resources/notes-root/lecture-notes/java.md").toFile();
    mdFiles = new ArrayList<>(Arrays.asList(arrays, java, test, vectors));
    bothFiles = new ArrayList<>(Arrays.asList(arrays, duedates, java, notes, test, vectors));
    md = new FileTypeVisitor(onlyMd);
    both = new FileTypeVisitor(withPdf);
    result = FileVisitResult.CONTINUE;
    try {
      attrs = Files.readAttributes(arraysPath, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Tests the getFiles method
   *
   */
  @Test
  public void testGetFiles() {
    assertThrows(IllegalStateException.class, () -> md.getFiles());
    try {
      Files.walkFileTree(path, md);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    ArrayList<File> mdOutput = md.getFiles();
    for (File f : mdOutput) {
      assertTrue(mdFiles.contains(f));
    }
    try {
      Files.walkFileTree(path, both);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    ArrayList<File> bothOutput = both.getFiles();
    for (File f : bothOutput) {
      assertTrue(bothFiles.contains(f));
    }
  }

  /**
   * Tests the visit methods inherited from FileVisitor
   *
   */
  @Test
  public void testVisit() {
    assertEquals(result, md.preVisitDirectory(path, attrs));
    assertEquals(result, md.postVisitDirectory(path, new IOException()));
    assertThrows(IOException.class, () -> md.visitFileFailed(path, new IOException()));
    assertEquals(result, md.visitFile(path, attrs));
  }
}