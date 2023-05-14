package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Class for comparing files by date created
 */
public class DateCreatedComparator implements Comparator<File> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return a positive number is file one's creation date is more recent
   or a negative number if file two's creation is more recent
   */
  @Override
  public int compare(File one, File two) {
    FileTime first;
    FileTime second;

    try {
      first = Files.readAttributes(one.toPath(), BasicFileAttributes.class).creationTime();
      second = Files.readAttributes(two.toPath(), BasicFileAttributes.class).creationTime();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return first.compareTo(second);
  }
}
