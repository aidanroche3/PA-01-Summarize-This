package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the lastModifiedComparator and its related methods
 */
public class LastModifiedComparatorTest {

  /**
   * Tests the compare method
   */
  @Test
  public void testCompare() {
    File one = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    File two = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    MarkDownFile mdOne = new MarkDownFile(one);
    MarkDownFile mdTwo = new MarkDownFile(two);
    LastModifiedComparator lmc = new LastModifiedComparator();
    assertEquals(-1, lmc.compare(mdOne, mdTwo));
    assertEquals(1, lmc.compare(mdTwo, mdOne));
  }
}