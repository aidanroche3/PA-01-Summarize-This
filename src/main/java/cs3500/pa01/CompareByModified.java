package cs3500.pa01;

import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Class for comparing files based on their last modified date
 */
public class CompareByModified implements Comparator<MarkDownFile> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return a positive number is file one's last modified date is more recent
   or a negative number if file two's last modified date is more recent
   */
  @Override
  public int compare(MarkDownFile one, MarkDownFile two) {
    FileTime first = one.getLastModified();
    FileTime second = two.getLastModified();
    return first.compareTo(second);
  }
}
