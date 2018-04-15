package cs3500.animator.model;

public interface IRGB {

  Double getRed();

  Double getGreen();

  Double getBlue();

  /**
   * This method converts the RGB value into a format that is readable
   * by the SVG.
   * @return  string SVG color
   */
  String toStringSVG();
}
