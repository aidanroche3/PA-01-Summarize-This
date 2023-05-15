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
  }

  /**
   * Tests the validateArgs method
   */
  @Test
  public void testValidateArgs() {
    String[] invalidLength = {"src/tests/resources/notes-root", "filename"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidLength));
    String[] invalidRoot = {"fake-root",
        "modified", "src/tests/resources/outputDirectory/summary.md"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidRoot));
    String[] nonDirectory = {"fsrc/tests/resources/notes-root/arrays.md", "modified",
        "src/tests/resources/outputDirectory/summary.md"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(nonDirectory));
    String[] invalidFlag = {"src/tests/resources/notes-root",
        "invalid", "src/tests/resources/outputDirectory/summary.md"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidFlag));
    String[] invalidRelativePath = {"src/tests/resources/notes-root",
        "created", "src/tests/resources/fakeDirectory/new"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidRelativePath));
    String[] notInMd = {"src/tests/resources/notes-root",
        "modified", "src/tests/resources/outputDirectory/summary.pdf"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(notInMd));
    String[] newFile = {"src/tests/resources/notes-root",
        "created", "src/tests/resources/outputDirectory/newfile.md"};
    assertDoesNotThrow(() -> Driver.validateArgs(newFile));
    String[] validArgs = {"src/tests/resources/notes-root",
        "filename", "src/tests/resources/outputDirectory/summary.md"};
    assertDoesNotThrow(() -> Driver.validateArgs(validArgs));
  }

}