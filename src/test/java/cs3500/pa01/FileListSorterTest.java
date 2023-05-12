package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
  File notes;
  File test;
  File vectors;
  ArrayList<File> files;
  ArrayList<File> name;
  ArrayList<File> modified;
  ArrayList<File> created;

  /**
   * Initializes the data to be tested
   *
   * @throws IOException on walkFileTree
   */
  @BeforeEach
  public void setup() throws IOException {
    path = Path.of("notes-root");
    validTypes = new ArrayList<>(Arrays.asList(".md", ".pdf"));
    visitor = new FileTypeVisitor(validTypes);
    arrays = Path.of("notes-root/arrays.md").toFile();
    notes = Path.of("notes-root/notes.pdf").toFile();
    test = Path.of("notes-root/test.md").toFile();
    vectors = Path.of("notes-root/vectors.md").toFile();
    Files.walkFileTree(path, visitor);
    files = visitor.getFiles();
    name = new ArrayList<>(Arrays.asList(arrays, notes, test, vectors));
    modified = new ArrayList<>(Arrays.asList(arrays, test, vectors, notes));
    created = new ArrayList<>(Arrays.asList(arrays, vectors, test, notes));
  }

  /**
   * Tests the getSortedList method with "filename" order flag
   */
  @Test
  public void testFilenameSort() {
    FileListSorter fls = new FileListSorter(files, "filename");
    assertEquals(name, fls.getSortedList());
  }

  /**
   * Tests the getSortedList method with "created" order flag
   */
  @Test
  public void testCreatedSort() {
    FileListSorter fls = new FileListSorter(files, "created");
    assertEquals(created, fls.getSortedList());
  }

  /**
   * Tests the getSortedList method with "modified" order flag
   */
  @Test
  public void testModifiedSort() {
    FileListSorter fls = new FileListSorter(files, "modified");
    assertEquals(modified, fls.getSortedList());
  }

  /**
   * Tests the getSortedList with an unsupported order flag
   *
   */
  @Test
  public void testUnsupportedSort() {
    FileListSorter fls = new FileListSorter(files, "filesize");
    assertThrows(IllegalStateException.class, fls::getSortedList);
  }

}