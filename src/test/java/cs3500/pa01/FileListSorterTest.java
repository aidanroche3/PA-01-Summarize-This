package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
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
  ArrayList<MarkDownFile> modified;
  ArrayList<MarkDownFile> created;

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
        FileTime.from(Instant.parse("2023-05-12T00:22:45.8787901Z")),
        FileTime.from(Instant.parse("2023-05-12T00:23:08.4171844Z")));
    testMd = new MarkDownFile(test,
        FileTime.from(Instant.parse("2023-05-12T04:27:13.8361739Z")),
        FileTime.from(Instant.parse("2023-05-12T04:27:31.2998209Z")));
    vectorsMd = new MarkDownFile(vectors,
        FileTime.from(Instant.parse("2023-05-12T00:23:20.9349153Z")),
        FileTime.from(Instant.parse("2023-05-12T04:28:10.2716696Z")));
    javaMd = new MarkDownFile(java,
        FileTime.from(Instant.parse("2023-05-12T23:50:38.4657258Z")),
        FileTime.from(Instant.parse("2023-05-13T01:28:07.4298933Z")));
    Files.walkFileTree(path, visitor);
    files = visitor.getFiles();
    mdFiles = MarkDownFile.listToMarkDownFiles(files);
    name = new ArrayList<>(Arrays.asList(arraysMd, javaMd, testMd, vectorsMd));
    created = new ArrayList<>(Arrays.asList(arraysMd, vectorsMd, testMd, javaMd));
    modified = new ArrayList<>(Arrays.asList(arraysMd, testMd, vectorsMd, javaMd));
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
    for (int i = 0; i < created.size(); i++) {
      assertEquals(created.get(i).getDateCreated(), createdOutput.get(i).getDateCreated());
    }
  }

  /**
   * Tests the getSortedList method with "modified" order flag
   */
  @Test
  public void testModifiedSort() {
    FileListSorter fls = new FileListSorter(mdFiles, "modified");
    ArrayList<MarkDownFile> modifiedOutput = fls.getSortedList();
    for (int i = 0; i < modified.size(); i++) {
      assertEquals(modified.get(i).getLastModified(), modifiedOutput.get(i).getLastModified());
    }
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