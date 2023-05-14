package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Testing the DateCreatedComparator class and its associated methods
 */
class DateCreatedComparatorTest {

  /**
   * Tests the compare method
   */
  @Test
  public void testCompare() {
    File one = Path.of("notes-root/arrays.md").toFile();
    File two = Path.of("notes-root/vectors.md").toFile();
    File three = Path.of("notes-root/nonexistent.pdf").toFile();
    DateCreatedComparator dcc = new DateCreatedComparator();
    assertEquals(-1, dcc.compare(one, two));
    assertEquals(1, dcc.compare(two, one));
    assertThrows(IllegalStateException.class, () -> dcc.compare(one, three));
  }

}