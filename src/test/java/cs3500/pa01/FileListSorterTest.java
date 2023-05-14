package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
  File arraysMd;
  File testMd;
  File vectorsMd;
  File javaMd;
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
    path = Path.of("src/tests/resources/notes-root");
    validTypes = new ArrayList<>(List.of(".md"));
    visitor = new FileTypeVisitor(validTypes);
    arrays = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    test = Path.of("src/tests/resources/notes-root/test.md").toFile();
    vectors = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    java = Path.of("src/tests/resources/notes-root/lecture notes/java.md").toFile();
    arraysMd = new MarkDownFile(arrays,
        FileTime.fromMillis(1683850965878L),
        FileTime.fromMillis(1683850988417L)).toFile();
    testMd = new MarkDownFile(test,
        FileTime.fromMillis(1683865633836L),
        FileTime.fromMillis(1683865651299L)).toFile();
    vectorsMd = new MarkDownFile(vectors,
        FileTime.fromMillis(1683851000934L),
        FileTime.fromMillis(1683865690271L)).toFile();
    javaMd = new MarkDownFile(java,
        FileTime.fromMillis(1683935438465L),
        FileTime.fromMillis(1683941287429L)).toFile();
    Files.walkFileTree(path, visitor);
    files = visitor.getFiles();
    name = new ArrayList<>(Arrays.asList(arraysMd, javaMd, testMd, vectorsMd));
    modified = new ArrayList<>(Arrays.asList(arraysMd, testMd, vectorsMd, javaMd));
    created = new ArrayList<>(Arrays.asList(arraysMd, vectorsMd, testMd, javaMd));
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