package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Class for testing WriteFilesToPath and its associated methods
 */
class WriteFilesToPathTest {

  /**
   * Tests the writeFilesToPath method
   */
  @Test
  public void testWriteFilesToPath() {
    File arrays = Path.of("src/tests/resources/notes-root/arrays.md").toFile();
    File testOne = Path.of("src/tests/resources/notes-root/test.md").toFile();
    File vectors = Path.of("src/tests/resources/notes-root/vectors.md").toFile();
    File java = Path.of("src/tests/resources/notes-root/lecture-notes/java.md").toFile();
    ArrayList<File> files = new ArrayList<>(Arrays.asList(arrays, testOne, vectors, java));
    CombineFiles combine = new CombineFiles(files);
    String combined;
    combined = combine.getCombinedFiles();
    FormatFile formatFile = new FormatFile(combined);
    String output = formatFile.summarizeContent();
    Path sample = Path.of("src/tests/resources/outputDirectory/samplesummary.md");
    Path testTwo = Path.of("src/tests/resources/outputDirectory/test.md");
    Path fake = Path.of("src/tests/resources/fakeDirectory/nonexistent.md");
    WriteFilesToPath filesToPath = new WriteFilesToPath();
    try {
      filesToPath.writeAtPath(testTwo, output);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    try {
      assertEquals(-1, Files.mismatch(sample, testTwo));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    assertThrows(IOException.class, () -> filesToPath.writeAtPath(fake, output));
  }

}