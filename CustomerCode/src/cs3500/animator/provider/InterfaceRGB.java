package cs3500.animator.provider;


/**
 * This interface represents colors in RGB format. Specific implementation may choose different
 * values ranges, i.e. 0 - 1, 0 - 255.
 */
public interface InterfaceRGB {

  /**
   * Returns a red value of this color in a range specified by a class.
   */
  double getRed();

  /**
   * Returns a green value of this color in a range specified by a class.
   */
  double getGreen();

  /**
   * Returns a blue value of this color in a range specified by a class.
   */
  double getBlue();

  /**
   * Returns a color information in a format "(%.1f, %.1f, %.1f)".
   */
  String toString();

  /**
   * Returns true if this color RGB values are the same as of a given object.
   */
  boolean equals(Object o);

  /**
   * Returns a hash code for this interface implementation such that it matches equals method.
   */
  int hashCode();

}
