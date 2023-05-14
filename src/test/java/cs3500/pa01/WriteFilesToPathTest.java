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
  public void testWriteFilesToPath() throws IOException {
    File arrays = Path.of("notes-root/arrays.md").toFile();
    File testOne = Path.of("notes-root/test.md").toFile();
    File vectors = Path.of("notes-root/vectors.md").toFile();
    File java = Path.of("notes-root/lecture notes/java.md").toFile();
    ArrayList<File> files = new ArrayList<>(Arrays.asList(arrays, testOne, vectors, java));
    CombineFiles combine = new CombineFiles(files);
    String combined = combine.getCombinedFiles();
    FormatFile formatFile = new FormatFile(combined);
    String output = formatFile.summarizeContent();

    Path sample = Path.of("outputDirectory/samplesummary.md");
    Path testTwo = Path.of("outputDirectory/test.md");
    Path fake = Path.of("fakeDirectory/nonexistent.md");
    WriteFilesToPath filesToPath = new WriteFilesToPath();
    filesToPath.writeAtPath(testTwo, output);
    assertEquals(-1, Files.mismatch(sample, testTwo));
    assertThrows(RuntimeException.class, () -> filesToPath.writeAtPath(fake, output));
  }

}