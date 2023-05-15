package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
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

    File arrays = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    MarkDownFile one = new MarkDownFile(arrays,
        FileTime.fromMillis(1683850965878L),
        FileTime.fromMillis(1683850988417L));
    File vectors = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    MarkDownFile two = new MarkDownFile(vectors,
        FileTime.fromMillis(1683851000934L),
        FileTime.fromMillis(1683865690271L));
    LastModifiedComparator lmc = new LastModifiedComparator();
    assertEquals(-1, lmc.compare(one, two));
    assertEquals(1, lmc.compare(two, one));
  }
}