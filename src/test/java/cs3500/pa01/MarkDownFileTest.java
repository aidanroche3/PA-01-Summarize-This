package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing MarkDownFile and its associated methods
 */
class MarkDownFileTest {

  File arrays;
  File test;
  File vectors;
  File invalidFormat;
  File invalidPath;
  File java;
  MarkDownFile arraysMd;
  MarkDownFile testMd;
  MarkDownFile vectorsMd;
  MarkDownFile javaMd;

  /**
   * Initializes the date for each test
   */
  @BeforeEach
  public void setup() {
    arrays = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    test = Path.of("src/tests/resources/notes-root/test.md").toFile();
    vectors = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    invalidFormat = Path.of("src/tests/resources/notes-root/invalid.pdf").toFile();
    invalidPath = Path.of("src/tests/resources/notes-root/invalid.md").toFile();
    java = Path.of("src/tests/resources/notes-root/lecture notes/java.md").toFile();
    arraysMd = new MarkDownFile(arrays);
    testMd = new MarkDownFile(test);
    vectorsMd = new MarkDownFile(vectors);
    javaMd = new MarkDownFile(java);
  }

  /**
   * Tests the constructor for exception
   */
  @Test
  public void testConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new MarkDownFile(invalidFormat));
    assertThrows(IllegalArgumentException.class, () -> new MarkDownFile(invalidPath));
  }

  /**
   * Tests the getFilename method
   */
  @Test
  public void testGetFilename() {
    assertEquals("arrays.md", arraysMd.getFilename());
    assertEquals("test.md", testMd.getFilename());
    assertEquals("vectors.md", vectorsMd.getFilename());
  }

  /**
   * Tests the getDateCreated method
   */
  @Test
  public void testGetDateCreated() {
    assertEquals("2023-05-12T00:22:45.8787901Z", arraysMd.getDateCreated().toString());
    assertEquals("2023-05-12T04:27:13.8361739Z", testMd.getDateCreated().toString());
    assertEquals("2023-05-12T00:23:20.9349153Z", vectorsMd.getDateCreated().toString());
    System.out.println(javaMd.getDateCreated().toMillis());
    System.out.println(javaMd.getLastModified().toMillis());
  }

  /**
   * Tests the getLastModified method
   *
   */
  @Test
  public void testGetLastModified() {
    assertEquals("2023-05-12T00:23:08.4171844Z", arraysMd.getLastModified().toString());
    assertEquals("2023-05-12T04:27:31.2998209Z", testMd.getLastModified().toString());
    assertEquals("2023-05-12T04:28:10.2716696Z", vectorsMd.getLastModified().toString());
  }

}