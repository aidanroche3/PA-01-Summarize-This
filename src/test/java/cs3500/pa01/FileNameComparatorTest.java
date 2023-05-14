package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Class for testing FileNameComparator and its associated methods
 */
class FileNameComparatorTest {

  /**
   * Tests the compare method
   */
  @Test
  public void testCompare() {
    File one = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    File two = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    FileNameComparator fnc = new FileNameComparator();
    assertEquals(-21, fnc.compare(one, two));
    assertEquals(21, fnc.compare(two, one));
  }

}