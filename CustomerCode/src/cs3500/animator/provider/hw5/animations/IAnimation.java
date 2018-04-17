package cs3500.animator.provider.hw5.animations;

import cs3500.animator.provider.hw5.shapes.IShape;

/**
 * This interface represents an animations that can be performed on a shape.
 */
public interface IAnimation {

  /**
   * Perform this animation on the given shape and return what the shape will be at the given time.
   *
   * @param t        the given time.
   * @param curShape the given shape.
   * @return the shape with this animation performed on it at time t.
   */
  IShape shapeAtTime(int t, IShape curShape);

  /**
   * Return a description of what this animation does.
   */
  String getAnimDesc();

  /**
   * Return the type of animation this is. Some examples would be "move", "scale", "color".
   */
  String getType();

  /**
   * Check if these two animations can play at the same time when acting on the same shape.
   * @param a The other animation.
   * @return True if they don't interfere, otherwise false.
   */
  boolean checkOverlap(IAnimation a);

  /**
   * Get the start time of this animation.
   * @return the start time.
   */
  int getStartTime();

  /**
   * Get the end time of this animation.
   * @return the end time.
   */
  int getEndTime();

  /**
   * Creates a description of the animation on the given shape with the given speed.
   * @return a description of animation in svg format
   */
  String getSVG(IShape shape, double ticksPerSecond, boolean isLooping);

  /**
   * Returns svg attribute name.
   * @return the attribute name
   */
  String getSVGType();

  /**
   * Returns the start value for svg.
   * @return the start value
   */
  String getSVGStart();

  /**
   * Returns the end value for svg.
   * @return the end value
   */
  String getSVGEnd();

  /**
   * Returns the 2nd start value for svg if there is one.
   * @return the start value
   */
  String getSVGStart2();

  /**
   * Returns the 2nd end value for svg if there is one.
   * @return the end value
   */
  String getSVGEnd2();


}
