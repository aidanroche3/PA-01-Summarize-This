package cs3500.pa01;

import java.io.File;
import java.util.Comparator;

/**
 * Class for comparing files based on their filename
 */
public class FileNameComparator implements Comparator<File> {

  /**
   * @param one the first object to be compared.
   * @param two the second object to be compared.
   * @return positive number if the filename of one is lower in the alphabet or
   * negative number if the filename of two is lower in the alphabet
   */
  @Override
  public int compare(File one, File two) {
    return one.getName().compareTo(two.getName());
  }
}
