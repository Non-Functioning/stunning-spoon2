package cs3500.animator.adapters;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimatedShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.RGB;
import cs3500.animator.model.enums.ShapeType;
import cs3500.animator.provider.InterfaceRGB;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.hw5.shapes.visitor.DrawVisitor;
import cs3500.animator.provider.hw5.shapes.visitor.IShapeVisitor;

public class ShapeAdapter extends AnimatedShape implements IShape {
  private List<IAnimation> animations;

  public ShapeAdapter(String shapeName, ShapeType type, InterfaceRGB initialColor,
                      Position2D initialPosition, List<Double> size, Integer appearTime,
                      Integer disappearTime) {
    super(shapeName, type, new RGB(initialColor.getRed(), initialColor.getGreen(),
                    initialColor.getBlue()), initialPosition, size, appearTime, disappearTime);
    animations = new ArrayList<>();
  }

  /**
   * Sets the width and height of the 2D shape, though they may not be called that.
   */
  @Override
  public void setWidthHeight(double width, double height) {
    List<Double> size = new ArrayList<>();
    size.add(width);
    size.add(height);
    this.initialSize = size;
  }

  /**
   * Set the RGB values (0.0 to 1.0) of the 2D shape.
   *
   * @param rgb The given RGB values.
   */
  @Override
  public void setRGB(InterfaceRGB rgb) {
    this.initialColor = new RGB(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
  }

  /**
   * Set the X and Y position of the 2D shape.
   *
   * @param pos the given Position2D value.
   */
  @Override
  public void setPos(Position2D pos) {
    this.initialPosition = pos;
  }

  /**
   * Return a description of what all the animations on this shape will do.
   */
  @Override
  public String getAnimDesc() {
    return this.toString();
  }

  /**
   * Return what type of shape this is, such as 'rectangle' or 'circle'.
   */
  @Override
  public String getType() {
    return this.getShapeType().toString();
  }

  /**
   * Return the point of reference for this shape, such as 'lower-left corner' or 'center'.
   */
  @Override
  public String getReference() {
    return this.getRefPoint().toString();
  }

  /**
   * Return a description of the dimensions of this shape.
   */
  @Override
  public String getDimensionsDesc() {
    return sizeParamsToString(this.getInitialSize());
  }

  /**
   * Get the width of this shape.
   *
   * @return the width.
   */
  @Override
  public double getWidth() {
    return this.initialSize.get(0);
  }

  /**
   * Get the height of this shape.
   *
   * @return the height.
   */
  @Override
  public double getHeight() {
    return this.initialSize.get(1);
  }

  /**
   * Get the RGB values of this shape.
   *
   * @return the RGB values.
   */
  @Override
  public InterfaceRGB getRGB() {
    return new RGBAdapter(this.initialColor.getRed(), this.initialColor.getGreen(),
            this.initialColor.getBlue());
  }

  /**
   * Get the XY Position of this shape.
   *
   * @return the position.
   */
  @Override
  public Position2D getPos() {
    return new Position2D(this.initialPosition.getX(),this.initialPosition.getY());
  }

  /**
   * Add an animation to the list of animations performed on the 2D shape. Throw an error if the
   * animation is the same type as another animation in the list and the start and end times are
   * overlapping each other. Also sort the list of animations so that they are order by start time.
   *
   * @param animation the given Animation.
   * @throws IllegalArgumentException if animations can not be performed at the same time.
   */
  @Override
  public void addAnimation(IAnimation animation) throws IllegalArgumentException {
    if (!isValidAnimation(animation)) {
      throw new IllegalArgumentException("Invalid animation for shape " + this.shapeName);
    }
    if (animations.isEmpty()) {
      animations.add(animation);
    }
    else {
      for (int i = 0; i < animations.size(); i++) {
        if (animation.getStartTime() <= animations.get(i).getStartTime()) {
          animations.add(i, animation);
          break;
        }
        else if (i == (animations.size() - 1)) {
          animations.add(animation);
          break;
        }
      }
    }
  }

  /**
   * Get the Shape that this 2D shape will be according to its list of animations at time t.
   *
   * @param t the given time.
   * @return the shape with animations performed up to time t.
   */
  @Override
  public IShape shapeAtTime(int t) {
    List<IAnimation> animationsAtTime = new ArrayList<>();
    InterfaceRGB color;
    Position2D pos;
    List<Double> size;

    for (int i = 0; i < animations.size(); i++) {
      if ((t >= animations.get(i).getStartTime()) & (t <= animations.get(i).getEndTime())) {
        animationsAtTime.add(animations.get(i));
      }
    }
    for (int i = 0; i < animationsAtTime.size(); i++) {
      switch (animationsAtTime.get(i).getType()) {
        case "move":
          break;
        case "scale":
          break;
        case "color":
          break;
        default:
          throw new IllegalArgumentException("Invalid animation type");
      }
    }
    IShape shape = new ShapeAdapter(this.shapeName, this.shapeType, );
    return shape;
  }

  /**
   * Creates an SVG description for this shape and its animations.
   * @return an SVG formatted description of this shape
   */
  @Override
  public String getSVG(double ticksPerSecond, boolean isLooping) {
    return null;
  }

  /**
   * Applies a given visitor to this shape.
   * @param visitor a shape visitor to be applied to this shape.
   * @return result of application the given visitor to this shape.
   */
  @Override
  public <T> T accept(IShapeVisitor<T> visitor) {
    switch(this.getShapeType()){
      case RECTANGLE:
        visitor.visitRectangle(this);
        break;

      case OVAL:
        visitor.visitOval(this);
        break;
    }
  }

  /**
   * Returns a name of this shape.
   */
  @Override
  public String getName() {
    return this.getShapeName();
  }

  /**
   * Returns a copy of this shape's animation list.
   */
  @Override
  public List<IAnimation> getAnimations() {
    return null;
  }

  /**
   * Returns a pinhole naming for each of the elements in this the first element referring to
   * x pinhole name and the second element - y pinhole name.
   */
  @Override
  public String[] getSVGPinhole() {
    return new String[0];
  }

  @Override
  public String[] getSVGSides() {
    return new String[0];
  }

  /**
   * Returns the time that this shape should disappear.
   * @return the disappear time.
   */
  @Override
  public int getDisappearTime() {
    return this.getDisappearTime();
  }

  /**
   * Returns true if the given point is within this shape.
   */
  @Override
  public boolean isPointWithinShape(Position2D point) {
    return false;
  }

  /**
   * Checks if the given Animation is valid and that it does not overlap
   * with another Animation of the same type occurring at the same time on
   * the same shape.
   *
   * @param a Animation to validate
   * @return true, if Animation is valid
   */
  private boolean isValidAnimation(IAnimation a) {
    for (int i = 0; i < animations.size(); i++) {
      IAnimation b = animations.get(i);
      if ((a.getType() == b.getType())
              & (((a.getStartTime() >= b.getStartTime()) & (a.getStartTime() <= b.getEndTime()))
              | ((a.getEndTime() >= b.getStartTime()) & (a.getEndTime() <= b.getEndTime())))) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method calculates the tweening value of an animation at a given time.
   * This method is used for move, change color, and change size animations.
   *
   * @param initVal   initial value
   * @param finalVal  final value
   * @param initTick  time of initial value
   * @param finalTick time of final value
   * @param tick  current time
   * @return value at current time
   */
  private Double calcTweening(Double initVal, Double finalVal, Integer initTick,
                              Integer finalTick, Integer tick) {
    Integer t1 = (finalTick - tick);
    Integer t2 = (tick - initTick);
    Integer t3 = (finalTick - initTick);
    Double v1 = t1.doubleValue() / t3.doubleValue();
    Double v2 = t2.doubleValue() / t3.doubleValue();
    return (initVal * v1) + (finalVal * v2);
  }
}
