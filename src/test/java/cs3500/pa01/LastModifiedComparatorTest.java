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
    File one = Path.of("notes-root/arrays.md").toFile();
    File two = Path.of("notes-root/vectors.md").toFile();
    LastModifiedComparator lmc = new LastModifiedComparator();
    assertEquals(-1, lmc.compare(one, two));
    assertEquals(1, lmc.compare(two, one));
  }
}