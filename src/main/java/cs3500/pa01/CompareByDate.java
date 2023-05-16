package cs3500.pa01;

import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Class for comparing files by date created
 */
public class CompareByDate implements Comparator<MarkDownFile> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return a positive number if file one's creation date is more recent
   or a negative number if file two's creation is more recent
   */
  @Override
  public int compare(MarkDownFile one, MarkDownFile two) {
    FileTime first = one.getDateCreated();
    FileTime second = two.getDateCreated();
    return first.compareTo(second);
  }
}
