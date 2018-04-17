package cs3500.animator.adapters;

import cs3500.animator.model.RGB;
import cs3500.animator.provider.InterfaceRGB;

public class RGBAdapter implements InterfaceRGB {
  private RGB rgb;
  /**
   * Initialize this object to the specified color.
   */
  public RGBAdapter(double red, double green, double blue) {
    this.rgb = new RGB(red, green, blue);
  }

  /**
   * Returns a red value of this color in a range specified by a class.
   */
  @Override
  public double getRed() {
    return rgb.getRed();
  }

  /**
   * Returns a green value of this color in a range specified by a class.
   */
  @Override
  public double getGreen() {
    return rgb.getGreen();
  }

  /**
   * Returns a blue value of this color in a range specified by a class.
   */
  @Override
  public double getBlue() {
    return rgb.getBlue();
  }

  /**
   * Returns a color information in a format "(%.1f, %.1f, %.1f)".
   */
  @Override
  public String toString() {
    return rgb.toString();
  }

  /**
   * Returns true if this color RGB values are the same as of a given object.
   */
  @Override
  public boolean equals(Object o) {
    return rgb.equals(o);
  }

  /**
   * Returns a hash code for this interface implementation such that it matches equals method.
   */
  @Override
  public int hashCode() {
    return rgb.hashCode();
  }
}
