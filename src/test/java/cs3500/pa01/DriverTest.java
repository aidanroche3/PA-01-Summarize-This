package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class for testing Drive and its associated methods
 */
class DriverTest {

  /**
   * Tests the main method
   */
  @Test
  public void testMain() {
    String[] args = {"src/tests/resources/notes-root",
        "filename", "src/tests/resources/outputDirectory/summary.md"};
    assertDoesNotThrow(() -> Driver.main(args));
    // add test to check that the output is correct
  }

  /**
   * Tests valid arguments
   */
  @Test
  public void testValidateArgs() {
    String[] invalidLength = {"src/tests/resources/notes-root", "filename"};
    assertThrows(RuntimeException.class, () -> Driver.main(invalidLength));
    String[] invalidRoot = {"fake-root",
        "modified", "src/tests/resources/outputDirectory/summary.md"};
    assertThrows(RuntimeException.class, () -> Driver.main(invalidRoot));
    String[] nonDirectory = {"src/tests/resources/notes-root/arrays.md", "modified",
        "src/tests/resources/outputDirectory/summary.md"};
    assertThrows(RuntimeException.class, () -> Driver.main(nonDirectory));
    String[] invalidFlag = {"src/tests/resources/notes-root",
        "invalid", "src/tests/resources/outputDirectory/summary.md"};
    assertThrows(RuntimeException.class, () -> Driver.main(invalidFlag));
    String[] invalidRelativePath = {"src/tests/resources/notes-root",
        "created", "src/tests/resources/fakeDirectory/new"};
    assertThrows(RuntimeException.class, () -> Driver.main(invalidRelativePath));
    String[] notInMd = {"src/tests/resources/notes-root",
        "modified", "src/tests/resources/outputDirectory/summary.pdf"};
    assertThrows(RuntimeException.class, () -> Driver.main(notInMd));
    String[] invalidOutputMd = {"src/tests/resources/notes-root",
        "created", "/fake/nonexistent.md"};
    assertThrows(RuntimeException.class, () -> Driver.main(invalidOutputMd));
    String[] newFile = {"src/tests/resources/notes-root",
        "created", "src/tests/resources/outputDirectory/newfile.md"};
    assertDoesNotThrow(() -> Driver.main(newFile));
    String[] validArgs = {"src/tests/resources/notes-root",
        "filename", "src/tests/resources/outputDirectory/summary.md"};
    assertDoesNotThrow(() -> Driver.main(validArgs));
  }

}