package cs3500.pa01;

import java.util.ArrayList;

/**
 * Class for sorting a list of files given an order flag
 */
public class FileListSorter {

  private final ArrayList<MarkDownFile> fileList;
  private final String orderFlag;

  /**
   *
   * @param fileList the list of files to be sorted
   * @param orderFlag the order flag of how to sort the files
   */
  public FileListSorter(ArrayList<MarkDownFile> fileList, String orderFlag) {
    this.fileList = fileList;
    this.orderFlag = orderFlag;
  }

  /**
   * Returns a sorted list of files based on the order flag
   *
   * @return the sorted list of files
   */
  public ArrayList<MarkDownFile> getSortedList() {

    switch (orderFlag) {
      case "filename" -> fileList.sort(new FileNameComparator());
      case "created" -> fileList.sort(new DateCreatedComparator());
      case "modified" -> fileList.sort(new LastModifiedComparator());
      default -> throw new IllegalStateException("Cannot sort list.");
    }
    return fileList;
  }

}
