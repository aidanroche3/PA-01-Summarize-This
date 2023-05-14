package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main driver of this project.
 */
public class Driver {

  private static Path rootPath;
  private static String orderFlag;
  private static Path outputPath;

  /**
   * Project entry point
   *
   * @param args - user is required to provide a root path directory, order flag to sort the files,
   *             and an output path to write the summary
   *             Example: "notes-root filename outputDirectory/summary.md"
   * @throws IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {

    validateArgs(args);
    ArrayList<String> validTypes = new ArrayList<>(List.of(".md"));
    FileTypeVisitor fileVisitor = new FileTypeVisitor(validTypes);
    Files.walkFileTree(rootPath, fileVisitor);
    ArrayList<File> files = fileVisitor.getFiles();
    FileListSorter fileSorter = new FileListSorter(files, orderFlag);
    ArrayList<File> sortedFiles = fileSorter.getSortedList();
    CombineFiles filerCombiner = new CombineFiles(sortedFiles);
    String combinedFiles = filerCombiner.getCombinedFiles();
    FormatFile fileFormatter = new FormatFile(combinedFiles);
    String formattedFiles = fileFormatter.summarizeContent();
    WriteFilesToPath fileWriter = new WriteFilesToPath();
    fileWriter.writeAtPath(outputPath, formattedFiles);
    System.out.println("Successfully summarized files of " + rootPath + " at " + outputPath);

  }

  /**
   * Validates that the arguments passed are a valid root path to a directory, a valid order flag
   * to sort the files, and a valid output file path to write the summary
   *
   * @param args the arguments provided by the user
   */
  public static void validateArgs(String[] args) {

    if (args.length == 3) {

      // validates the root path
      Path p = Path.of(args[0]);
      if (Files.exists(p) && p.toFile().isDirectory()) {
        Driver.rootPath = p;
      } else {
        throw new IllegalArgumentException("Invalid root path.");
      }

      // validates the order flag
      if (args[1].equals("filename") || args[1].equals("created") || args[1].equals("modified")) {
        Driver.orderFlag = args[1];
      } else {
        throw new IllegalArgumentException("Invalid order flag.");
      }

      // validates the output path
      String outputPath = args[2];

      // checks if the path already exists or creates a new file if the path is valid
      Path path = Path.of(outputPath);
      File file = new File(path.toString());
      if (file.getParentFile().isDirectory() && outputPath.endsWith(".md")) {
        Driver.outputPath = path;
      } else {
        throw new IllegalArgumentException("Invalid output path");
      }

    } else {
      throw new IllegalArgumentException("Please provide valid root path, "
          + "order flag, and output path");
    }
  }
}