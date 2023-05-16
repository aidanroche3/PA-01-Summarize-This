package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

/**
 * Class for representing a markdown file
 */
public class MarkDownFile {

  private final File source;
  private final String filename;
  private final FileTime dateCreated;
  private final FileTime lastModified;

  /**
   * Instantiates a markdown file with a file
   *
   * @param file a valid .md file
   */
  public MarkDownFile(File file) {

    if (file.toString().endsWith(".md")) {
      source = file;
      filename = file.getName();
      BasicFileAttributes attrs;
      try {
        attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
      dateCreated = attrs.creationTime();
      lastModified = attrs.lastModifiedTime();
    } else {
      throw new IllegalArgumentException("Invalid markdown file.");
    }
  }

  /**
   * Instantiates a markdown file with a file, date created, and time of last modification
   *
   * @param file the source file
   * @param dateCreated the date the file was created
   * @param lastModified the last time the file was modified
   */
  public MarkDownFile(File file, FileTime dateCreated, FileTime lastModified) {
    this.source = file;
    this.filename = file.getName();
    this.dateCreated = dateCreated;
    this.lastModified = lastModified;
  }

  /**
   * Gets the filename of the current file
   *
   * @return the filename
   */
  public String getFilename() {
    return this.filename;
  }

  /**
   * Gets the date created of the current file
   *
   * @return the date created
   */
  public FileTime getDateCreated() {
    return this.dateCreated;
  }

  /**
   * Gets the last modified time of the current file
   *
   * @return the last modified time
   */
  public FileTime getLastModified() {
    return this.lastModified;
  }

  /**
   * Gets markdown file object as the original file
   *
   * @return the file of the current .md file
   */
  public File toFile() {
    return this.source;
  }

  /**
   * Returns a list of File as a list of MarkDownFile
   *
   * @param files a list of File
   * @return a list of MarkDownFile
   */
  public static ArrayList<MarkDownFile> listToMarkDownFiles(ArrayList<File> files) {

    ArrayList<MarkDownFile> mdFiles = new ArrayList<>();
    for (File f : files) {
      mdFiles.add(new MarkDownFile(f));
    }
    return mdFiles;
  }

  /**
   * Returns a list of MarkDownFile as a list of File
   *
   * @param mdFiles a list of MarkDownFiles
   * @return a list of File
   */
  public static ArrayList<File> listToFiles(ArrayList<MarkDownFile> mdFiles) {

    ArrayList<File> files = new ArrayList<>();
    for (MarkDownFile m : mdFiles) {
      files.add(m.toFile());
    }
    return files;
  }

}
