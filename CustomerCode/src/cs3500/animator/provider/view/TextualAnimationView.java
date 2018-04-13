package cs3500.animator.provider.view;

import java.util.Scanner;


/**
 * This is an implementation of the IAnimatorView interface that creates a description of this
 * animation in a text format.
 */
public class TextualAnimationView extends AAnimatorView {
  private String desc;

  /**
   * Constructs an instance of IAnimatorView that shows textual description of animation.
   */
  public TextualAnimationView() {
    super();
    this.desc = "";
  }

  @Override
  public String getDescription() {
    StringBuilder builder = new StringBuilder();
    Scanner scanner = new Scanner(this.desc);

    while (scanner.hasNextLine()) {
      builder.append(this.getDescriptionHelper(scanner.nextLine()).trim() + "\n");
    }

    scanner.close();

    return builder.toString();
  }

  @Override
  public void setDescription(String description) throws IllegalArgumentException {
    if (description == null) {
      throw new IllegalArgumentException("Invalid description.");
    }

    this.desc = description;
  }

  // returns a modified version of a given string with converted ticks to seconds and
  // substituted pinhole name for rectangle (if any present in a given string)
  private String getDescriptionHelper(String line) {
    StringBuilder builder = new StringBuilder();
    Scanner scanner = new Scanner(line);

    // while there are elements in the given line
    while (scanner.hasNext()) {
      // keep on getting new words - elements separated by spaces
      String word = scanner.next();

      // if the word contains symbol that indicate time, convert it to seconds
      // if it contains searched pinhole name, modify it a new value
      if (word.contains("t=")) {
        builder.append(this.convertToSeconds(word) + " ");
      } else if (word.contains("Lower-left")) {
        if (scanner.hasNext()) {
          scanner.next();
        }
        builder.append("Min-corner: ");
      } else {
        builder.append(word + " ");
      }
    }

    scanner.close();

    return builder.toString();
  }

  // returns a modified version of a given string with ticks converted to seconds (if any present)
  private String convertToSeconds(String parameter) {
    int tick = 0;
    try {
      tick = Integer.parseInt(parameter.substring(2));
    } catch (NumberFormatException e) {
      return parameter;
    }
    return "t=" + (tick / this.ticksPerSecond) + "s";
  }

  @Override
  public String getViewType() {
    return "text";
  }
}
