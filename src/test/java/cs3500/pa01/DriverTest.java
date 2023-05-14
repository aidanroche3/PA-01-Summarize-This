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
    String[] args = {"notes-root", "filename", "outputDirectory/summary.md"};
    assertDoesNotThrow(() -> Driver.main(args));
  }

  /**
   * Tests the validateArgs method
   */
  @Test
  public void testValidateArgs() {
    String[] invalidLength = {"notes-root", "filename"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidLength));
    String[] invalidRoot = {"fake-root", "modified", "outputDirectory/summary.md"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidRoot));
    String[] nonDirectory = {"fake.md", "modified", "outputDirectory/summary.md"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(nonDirectory));
    String[] invalidFlag = {"notes-root", "invalid", "outputDirectory/summary.md"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidFlag));
    String[] invalidRelativePath = {"notes-root", "created", "fakeDirectory/new"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(invalidRelativePath));
    String[] notInMd = {"notes-root", "modified", "outputDirectory/summary.pdf"};
    assertThrows(IllegalArgumentException.class, () -> Driver.validateArgs(notInMd));
    String[] newFile = {"notes-root", "created", "outputDirectory/newfile.md"};
    assertDoesNotThrow(() -> Driver.validateArgs(newFile));
    String[] validArgs = {"notes-root", "filename", "outputDirectory/summary.md"};
    assertDoesNotThrow(() -> Driver.validateArgs(validArgs));
  }

}