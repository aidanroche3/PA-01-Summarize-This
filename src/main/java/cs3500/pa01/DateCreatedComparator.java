package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

public class DateCreatedComparator implements Comparator<File> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return a positive number is file one's creation date is more recent
   *    * or a negative number if file two's creation is more recent
   */
  @Override
  public int compare(File one, File two) {
    FileTime first = null;
    FileTime second = null;

    try {
      first = Files.readAttributes(one.toPath(), BasicFileAttributes.class).creationTime();
      second = Files.readAttributes(two.toPath(), BasicFileAttributes.class).creationTime();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error reading file attributes");
      System.exit(1);
    }
    return first.compareTo(second);
  }
}
