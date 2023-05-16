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
   * @param args user is required to provide a root path directory, order flag to sort the files,
   *             and an output path to write the summary
   *             Example: "src/tests/resources/notes-root
   *             filename src/tests/resources/outputDirectory/summary.md"
   */
  public static void main(String[] args) {
    // validating that the arguments passed in are valid
    validateArgs(args);
    // creates the summary of the root path files at the output path according to the order flag
    summarize(rootPath, orderFlag, outputPath);
    System.out.println("Successfully summarized files of " + rootPath + " at " + outputPath);
  }

  /**
   * Initializes the fields of main, checking that the order flag is valid
   *
   * @param args the arguments provided by the user
   */
  private static void validateArgs(String[] args) {

    if (args.length == 3) {

      // sets the root path, validity will be checked at runtime
      Driver.rootPath = Path.of(args[0]);

      // validates the order flag
      if (args[1].equals("filename") || args[1].equals("created") || args[1].equals("modified")) {
        Driver.orderFlag = args[1];
      } else {
        throw new IllegalArgumentException("Invalid order flag.");
      }

      // sets the output path, validity will be checked at runtime
      Driver.outputPath = Path.of(args[2]);

    } else {
      throw new IllegalArgumentException("Please provide valid root path, "
          + "order flag, and output path");
    }
  }

  /**
   * Summarizes the content of the root path and writes it to the output path in order of the flag
   *
   * @param rootPath the root path directory of files to summarize
   * @param orderFlag the order flag to sort the summarize files
   * @param outputPath the output path file to summarize the files
   */
  private static void summarize(Path rootPath, String orderFlag, Path outputPath) {
    // initializing a list of valid types of files for the visitor to "collect"
    ArrayList<String> validTypes = new ArrayList<>(List.of(".md"));
    // initializing the file visitor
    FileTypeVisitor fileVisitor = new FileTypeVisitor(validTypes);
    // visiting every file in the root path with the file visitor
    try {
      Files.walkFileTree(rootPath, fileVisitor);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // initializing a list of files from the visitor
    ArrayList<File> files = fileVisitor.getFiles();
    // converting the list of files into a list of markdown files
    ArrayList<MarkDownFile> mdFiles = MarkDownFile.listToMarkDownFiles(files);
    // sorting the list of markdown files based on the order flag
    FileListSorter fileSorter = new FileListSorter(mdFiles, orderFlag);
    // initializing a list of markdown files from the sorted list
    ArrayList<MarkDownFile> sortedMdFiles = fileSorter.getSortedList();
    // converting the list of markdown files to a list of files
    ArrayList<File> sortedFiles = MarkDownFile.listToFiles(sortedMdFiles);
    // initializing a new combine  files with the sorted list of files
    CombineFiles filerCombiner = new CombineFiles(sortedFiles);
    // initializing a String of the content of the sorted list of files
    String combinedFiles = filerCombiner.getCombinedFiles();
    // initializing a new file formatter with the String of combined content
    FormatFile fileFormatter = new FormatFile(combinedFiles);
    // initializing a String of the summarized content from the file formatter
    String formattedFiles = fileFormatter.summarizeContent();
    // initializing a new write files to path
    WriteFilesToPath fileWriter = new WriteFilesToPath();
    // writing the summarized content at the output path
    if (outputPath.toString().endsWith(".md")) {
      try {
        fileWriter.writeAtPath(outputPath, formattedFiles);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new IllegalArgumentException("Output path is not a .md file.");
    }
  }
}