package cs3500.pa01;

/**
 * Class for formatting a String of combined files into a summary
 */
public class FormatFile {

  private final String content;

  /**
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

      Character current = content.charAt(i);
      Character previous;
      Character next;
      if (i > 0) {
        previous = content.charAt(i - 1);
      } else  {
        previous = ' ';
      }
      if (i < content.length() - 1) {
        next = content.charAt(i + 1);
      } else {
        next = ' ';
      }

      // keeps headings
      if (!heading && current.equals('#')) {
        if (previous.equals('\n')) {
          summarizedContent.append('\n');
        }
        heading = true;
      }
      if (heading) {
        summarizedContent.append(current);
      }
      if (heading && current.equals('\n')) {
        heading = false;
      }

      // keeps bracketed phrases
      if (!heading && current.equals('[') && next.equals('[')) {
        bracketed = true;
        summarizedContent.append('-');
        summarizedContent.append(' ');
      }
      if (bracketed && current.equals(']') && next.equals(']')) {
        summarizedContent.append('\n');
        bracketed = false;
      }
      if (bracketed && !(current.equals('[') && (previous.equals('[') || next.equals('[')))) {
        summarizedContent.append(current);
      }

    }
    return summarizedContent.toString();
  }

}
