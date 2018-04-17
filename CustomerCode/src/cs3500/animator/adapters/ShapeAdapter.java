package cs3500.animator.adapters;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimatedShape;
import cs3500.animator.model.IAnimatedShape;
import cs3500.animator.model.IAnimations;
import cs3500.animator.model.IPosition2D;
import cs3500.animator.model.IRGB;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.RGB;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.enums.ShapeType;
import cs3500.animator.provider.InterfaceRGB;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.hw5.shapes.visitor.IShapeVisitor;

public class ShapeAdapter extends AnimatedShape implements IShape {
  private List<IAnimation> animations;
  private IAnimatedShape shape;
  private SimpleAnimationModel model;

  public ShapeAdapter(SimpleAnimationModel model, IAnimatedShape shape) {
    super(shape.getShapeName(), shape.getShapeType(), shape.getInitialColor(),
            shape.getInitialPosition(), shape.getInitialSize(), shape.getAppearTime(),
            shape.getDisappearT());
    animations = new ArrayList<>();
    this.shape = shape;
    this.model = model;
    for (int i = 0; i < this.model.getAnimations().size(); i++) {
      if (this.model.getAnimations().get(i).getChangedShape().equals(shape)) {
        addAnimation(new AnimationAdapter(this.model.getAnimations().get(i)));
      }
    }
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
    List<IAnimations> animationsAtTime = model.getTimeline().get(t);
    IRGB color = null;
    IPosition2D pos = null;
    List<Double> size = new ArrayList<>();

    for (int i = 0; i < animationsAtTime.size(); i++) {
      IAnimations a = animationsAtTime.get(i);
      if (a.getChangedShape().getShapeName().equals(shape.getShapeName())) {
        switch (a.getAnimateType()) {
          case MOVE:
            double x = calcTweening(a.getPosition1().getX(), a.getPosition2().getX(), a.getTime1(),
                    a.getTime2(), t);
            double y = calcTweening(a.getPosition1().getY(), a.getPosition2().getY(), a.getTime1(),
                    a.getTime2(), t);
            pos = new Position2D(x, y);
            break;
          case CHANGECOLOR:
            double r = calcTweening(a.getColor1().getRed(), a.getColor2().getRed(), a.getTime1(),
                    a.getTime2(), t);
            double g = calcTweening(a.getColor1().getGreen(), a.getColor2().getGreen(),
                    a.getTime1(), a.getTime2(), t);
            double b = calcTweening(a.getColor1().getBlue(), a.getColor2().getBlue(),
                    a.getTime1(), a.getTime2(), t);
            color = new RGB(r, g, b);
            break;
          case CHANGESIZE:
            for (int j = 0; j < a.getSizeParams1().size(); j++) {
              double s = calcTweening(a.getSizeParams1().get(j), a.getSizeParams2().get(j),
                      a.getTime1(), a.getTime2(), t);
              size.add(s);
            }
            break;
          case APPEAR:
          case DISAPPEAR:
          case STILL:
            break;
          default:
            throw new IllegalArgumentException("Invalid animation type");
        }
      }
    }
    if (color == null) {
      color = animationsAtTime.get(0).getColor1();
    }
    if (pos == null) {
      pos = animationsAtTime.get(0).getPosition1();
    }
    if (size.isEmpty()) {
      size = animationsAtTime.get(0).getSizeParams1();
    }

    IAnimatedShape aShape = new AnimatedShape(this.shapeName, this.shapeType, color, pos, size,
            this.appearTime, this.disappearTime);

    IShape shape = new ShapeAdapter(this.model, aShape);
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
        return visitor.visitRectangle(this);
      case OVAL:
        return visitor.visitOval(this);
      default:
        throw new IllegalArgumentException("Invalid shape type");
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
    return this.animations;
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
    return this.disappearTime;
  }

  @Override
  public IShape getOriginal() {
    return new ShapeAdapter(this.model, this.shape);
  }

  @Override
  public String getSVGType() {
    return null;
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
      if (a.getType() == b.getType()) {
        if ((((a.getStartTime() >= b.getStartTime()) & (a.getStartTime() <= b.getEndTime()))
                | ((a.getEndTime() >= b.getStartTime()) & (a.getEndTime() <= b.getEndTime())))) {
          return false;
        }
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
