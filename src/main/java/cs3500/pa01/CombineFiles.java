package cs3500.pa01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for combining a list of files into a String
 */
public class CombineFiles {

  private ArrayList<File> fileList;

  /**
   *
   * @param fileList a list of files
   */
  public CombineFiles(ArrayList<File> fileList) {
    this.fileList = fileList;
  }

  /**
   * Combines a list of files into a string with a new line between each file
   *
   * @return a String of the combined contents of each file in fileList
   * @throws IOException on I/O failures
   */
  public String getCombinedFiles() throws IOException {

    StringBuilder combinedFiles = new StringBuilder();
    for (File f : fileList) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(f));
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
      StringBuilder content = new StringBuilder();
      while (sc.hasNextLine()) {
        content.append(sc.nextLine()).append("\n");
      }
      content.append("\n");
      combinedFiles.append(content);
    }
    return combinedFiles.toString();
  }
}
