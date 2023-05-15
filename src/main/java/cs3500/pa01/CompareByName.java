package cs3500.pa01;

import java.util.Comparator;

/**
 * Class for comparing files based on their filename
 */
public class CompareByName implements Comparator<MarkDownFile> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return positive number if the filename of one is lower in the alphabet or
   negative number if the filename of two is lower in the alphabet
   */
  @Override
  public int compare(MarkDownFile one, MarkDownFile two) {
    String first = one.getFilename();
    String second = two.getFilename();
    return first.compareTo(second);
  }
}
