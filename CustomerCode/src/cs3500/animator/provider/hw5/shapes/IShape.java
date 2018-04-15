package cs3500.animator.provider.hw5.shapes;

import java.util.List;

import cs3500.animator.model.Position2D;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.visitor.IShapeVisitor;

/**
 * This interface specifies the possible interactions with a 2D shape.
 */
public interface IShape {

  /**
   * Sets the width and height of the 2D shape, though they may not be called that.
   */
  void setWidthHeight(double width, double height);

  /**
   * Set the RGB values (0.0 to 1.0) of the 2D shape.
   *
   * @param rgb The given RGB values.
   */
  void setRGB(cs3500.hw5.shapes.InterfaceRGB rgb);

  /**
   * Set the X and Y position of the 2D shape.
   *
   * @param pos the given Position2D value.
   */
  void setPos(Position2D pos);

  /**
   * Return a description of what all the animations on this shape will do.
   */
  String getAnimDesc();

  /**
   * Return what type of shape this is, such as 'rectangle' or 'circle'.
   */
  String getType();

  /**
   * Return the point of reference for this shape, such as 'lower-left corner' or 'center'.
   */
  String getReference();

  /**
   * Return a description of the dimensions of this shape.
   */
  String getDimensionsDesc();

  /**
   * Get the width of this shape.
   *
   * @return the width.
   */
  double getWidth();

  /**
   * Get the height of this shape.
   *
   * @return the height.
   */
  double getHeight();

  /**
   * Get the RGB values of this shape.
   *
   * @return the RGB values.
   */
  cs3500.hw5.shapes.InterfaceRGB getRGB();

  /**
   * Get the XY Position of this shape.
   *
   * @return the position.
   */
  Position2D getPos();


  /**
   * Add an animation to the list of animations performed on the 2D shape. Throw an error if the
   * animation is the same type as another animation in the list and the start and end times are
   * overlapping each other. Also sort the list of animations so that they are order by start time.
   *
   * @param animation the given Animation.
   * @throws IllegalArgumentException if animations can not be performed at the same time.
   */
  void addAnimation(IAnimation animation) throws IllegalArgumentException;


  /**
   * Get the Shape that this 2D shape will be according to its list of animations at time t.
   *
   * @param t the given time.
   * @return the shape with animations performed up to time t.
   */
  IShape shapeAtTime(int t);


  /**
   * Creates an SVG description for this shape and its animations.
   * @return an SVG formatted description of this shape
   */
  String getSVG(double ticksPerSecond, boolean isLooping);

  /**
   * Applies a given visitor to this shape.
   * @param visitor a shape visitor to be applied to this shape.
   * @return result of application the given visitor to this shape.
   */
  <T> T accept(IShapeVisitor<T> visitor);


  /**
   * Returns a name of this shape.
   */
  String getName();

  /**
   * Returns a copy of this shape's animation list.
   */
  List<IAnimation> getAnimations();

  /**
   * Returns a pinhole naming for each of the elements in this the first element referring to
   * x pinhole name and the second element - y pinhole name.
   */
  String[] getSVGPinhole();


  String[] getSVGSides();

  /**
   * Returns the time that this shape should disappear.
   * @return the disappear time.
   */
  int getDisappearTime();

  /**
   * Returns true if the given point is within this shape.
   */
  boolean isPointWithinShape(Position2D point);


}
