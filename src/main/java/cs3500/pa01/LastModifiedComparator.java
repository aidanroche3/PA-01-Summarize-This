package cs3500.pa01;

import java.io.File;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Class for comparing files based on their last modified date
 */
public class LastModifiedComparator implements Comparator<File> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return a positive number is file one's last modified date is more recent
   or a negative number if file two's last modified date is more recent
   */
  @Override
  public int compare(File one, File two) {
    FileTime first = new MarkDownFile(one).getLastModified();
    FileTime second = new MarkDownFile(two).getLastModified();
    return first.compareTo(second);
  }
}
