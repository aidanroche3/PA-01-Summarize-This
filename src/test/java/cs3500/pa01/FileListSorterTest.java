package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing FileListSorter abd its associated methods
 */
class FileListSorterTest {

  Path path;
  ArrayList<String> validTypes;
  FileTypeVisitor visitor;
  File arrays;
  File test;
  File vectors;
  File java;
  MarkDownFile arraysMd;
  MarkDownFile testMd;
  MarkDownFile vectorsMd;
  MarkDownFile javaMd;
  ArrayList<File> files;
  ArrayList<MarkDownFile> mdFiles;
  ArrayList<MarkDownFile> name;

  /**
   * Initializes the data to be tested
   *
   */
  @BeforeEach
  public void setup() {
    path = Path.of("src/tests/resources/notes-root");
    validTypes = new ArrayList<>(List.of(".md"));
    visitor = new FileTypeVisitor(validTypes);
    arrays = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    test = Path.of("src/tests/resources/notes-root/test.md").toFile();
    vectors = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    java = Path.of("src/tests/resources/notes-root/lecture-notes/java.md").toFile();
    try {
      FileTime arraysCreated =
          Files.readAttributes(arrays.toPath(), BasicFileAttributes.class).creationTime();
      FileTime arraysModified =
          Files.readAttributes(arrays.toPath(), BasicFileAttributes.class).lastModifiedTime();
      arraysMd = new MarkDownFile(arrays, arraysCreated, arraysModified);
      FileTime testCreated =
          Files.readAttributes(test.toPath(), BasicFileAttributes.class).creationTime();
      FileTime testModified =
          Files.readAttributes(test.toPath(), BasicFileAttributes.class).lastModifiedTime();
      testMd = new MarkDownFile(test, testCreated, testModified);
      FileTime vectorsCreated =
          Files.readAttributes(vectors.toPath(), BasicFileAttributes.class).creationTime();
      FileTime vectorsModified =
          Files.readAttributes(vectors.toPath(), BasicFileAttributes.class).lastModifiedTime();
      vectorsMd = new MarkDownFile(vectors, vectorsCreated, vectorsModified);
      FileTime javaCreated =
          Files.readAttributes(java.toPath(), BasicFileAttributes.class).creationTime();
      FileTime javaModified =
          Files.readAttributes(java.toPath(), BasicFileAttributes.class).lastModifiedTime();
      javaMd = new MarkDownFile(java, javaCreated, javaModified);
      Files.walkFileTree(path, visitor);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    files = visitor.getFiles();
    mdFiles = MarkDownFile.listToMarkDownFiles(files);
    name = new ArrayList<>(Arrays.asList(arraysMd, javaMd, testMd, vectorsMd));
  }

  /**
   * Tests the getSortedList method with "filename" order flag
   */
  @Test
  public void testFilenameSort() {
    FileListSorter fls = new FileListSorter(mdFiles, "filename");
    ArrayList<MarkDownFile> nameOutput = fls.getSortedList();
    for (int i = 0; i < name.size(); i++) {
      assertEquals(name.get(i).getFilename(), nameOutput.get(i).getFilename());
    }
  }

  /**
   * Tests the getSortedList method with "created" order flag
   */
  @Test
  public void testCreatedSort() {
    FileListSorter fls = new FileListSorter(mdFiles, "created");
    ArrayList<MarkDownFile> createdOutput = fls.getSortedList();
    assertTrue(createdOutput.get(0).getDateCreated().compareTo(
        createdOutput.get(1).getDateCreated()) <= 0);
    assertTrue(createdOutput.get(1).getDateCreated().compareTo(
        createdOutput.get(2).getDateCreated()) <= 0);
    assertTrue(createdOutput.get(2).getDateCreated().compareTo(
        createdOutput.get(3).getDateCreated()) <= 0);
  }

  /**
   * Tests the getSortedList method with "modified" order flag
   */
  @Test
  public void testModifiedSort() {
    FileListSorter fls = new FileListSorter(mdFiles, "modified");
    ArrayList<MarkDownFile> modifiedOutput = fls.getSortedList();
    assertTrue(modifiedOutput.get(0).getLastModified().compareTo(
        modifiedOutput.get(1).getLastModified()) <= 0);
    assertTrue(modifiedOutput.get(1).getLastModified().compareTo(
        modifiedOutput.get(2).getLastModified()) <= 0);
    assertTrue(modifiedOutput.get(2).getLastModified().compareTo(
        modifiedOutput.get(3).getLastModified()) <= 0);
  }

  /**
   * Tests the getSortedList with an unsupported order flag
   *
   */
  @Test
  public void testUnsupportedSort() {
    FileListSorter fls = new FileListSorter(mdFiles, "filesize");
    assertThrows(IllegalStateException.class, fls::getSortedList);
  }

}