package cs3500.pa01;

/**
 * Class for formatting a String of combined files into a summary
 */
public class FormatFile {

  private final String content;

  /**
   * Instantiates a file formatter with a String of content
   *
   * @param content the content to be formatted into a summary
   */
  public FormatFile(String content) {
    this.content = content;
  }

  /**
   * Summarizes the content of a string by keeping headings and bracketed content
   *
   * @return the content String summarized to only contain headings and bracketed content
   */
  public String summarizeContent() {
    StringBuilder summarizedContent = new StringBuilder();
    boolean heading = false;
    boolean bracketed = false;

    for (int i = 0; i < content.length(); i++) {
      char previous = setPrevious(i);
      char current = content.charAt(i);

      // keeps the headings
      if (!heading && current == '#') {
        if (previous == '\n') {
          summarizedContent.append('\n');
        }
        heading = true;
      }
      if (heading) {
        summarizedContent.append(current);
      }
      if (heading && current == '\n') {
        heading = false;
      }

      char next = setNext(i);

      // keeps bracketed phrases
      if (!heading && current == '[' && next == '[') {
        bracketed = true;
        summarizedContent.append('-');
        summarizedContent.append(' ');
      }
      if (bracketed && current == ']' && next == ']') {
        summarizedContent.append('\n');
        bracketed = false;
      }
      if (bracketed && !(current == '\n')
          && !(current == '[' && (previous == '[' || next == '['))) {
        summarizedContent.append(current);
      }
    }
    return summarizedContent.toString();
  }

  /**
   * Returns the previous character in the content string, ' ' if previous is out of bounds
   *
   * @param i the current index of content
   * @return the character before index i
   */
  private char setPrevious(int i) {
    if (i > 0) {
      return this.content.charAt(i - 1);
    }
    return ' ';
  }

  /**
   * Returns the next character in the content string, ' ' if next is out of bounds
   *
   * @param i the current index of content
   * @return the next character after index i
   */
  private char setNext(int i) {
    if (i < this.content.length() - 1) {
      return this.content.charAt(i + 1);
    }
    return ' ';
  }

}
