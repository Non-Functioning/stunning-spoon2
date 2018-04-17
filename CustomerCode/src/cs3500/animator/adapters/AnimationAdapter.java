package cs3500.animator.adapters;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.Animations;
import cs3500.animator.model.ChangeShapeColor;
import cs3500.animator.model.ChangeShapeSize;
import cs3500.animator.model.IAnimatedShape;
import cs3500.animator.model.IPosition2D;
import cs3500.animator.model.IRGB;
import cs3500.animator.model.MoveShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.RGB;
import cs3500.animator.model.ShapeAppears;
import cs3500.animator.model.ShapeDisappears;
import cs3500.animator.model.StillShape;
import cs3500.animator.model.enums.AnimateTypes;
import cs3500.animator.model.enums.ShapeType;
import cs3500.animator.provider.InterfaceRGB;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

public class AnimationAdapter implements IAnimation {
  private Animations animations;

  AnimationAdapter(IAnimatedShape changedShape, AnimateTypes type, Integer time1, Integer time2,
                   IRGB color1, IRGB color2, List<Double> sizeParams1, List<Double> sizeParams2,
                   IPosition2D position1, IPosition2D position2) {
    switch (type) {
      case MOVE:
        this.animations = new MoveShape(changedShape, position1, position2, time1, time2);
        break;
      case APPEAR:
        this.animations = new ShapeAppears(changedShape, time1);
        break;
      case DISAPPEAR:
        this.animations = new ShapeDisappears(changedShape, time1);
        break;
      case STILL:
        this.animations = new StillShape(changedShape, time1);
        break;
      case CHANGESIZE:
        this.animations = new ChangeShapeSize(changedShape, sizeParams1, sizeParams2, time1, time2);
        break;
      case CHANGECOLOR:
        this.animations = new ChangeShapeColor(changedShape, color1, color2, time1, time2);
    }
  }

  /**
   * Perform this animation on the given shape and return what the shape will be at the given time.
   *
   * @param t        the given time.
   * @param curShape the given shape.
   * @return the shape with this animation performed on it at time t.
   */
  @Override
  public IShape shapeAtTime(int t, IShape curShape) {
    /*ShapeType shapeType;
    InterfaceRGB color = curShape.getRGB();
    IPosition2D pos = curShape.getPos();
    List<Double> size = new ArrayList<>();
    size.add(curShape.getHeight());
    size.add(curShape.getWidth());

    //int i = 0;
    //IAnimation a = curShape.getAnimations().get(i);
    //while (a.getStartTime() <= t) {
      switch (animations.getType()) {
        case MOVE:
          if (animations.getTime2() > t) {
            double x = calcTweening(pos.getX(), animations.getPosition2().getX(), getStartTime(),
                    getEndTime(), t);
            double y = calcTweening(pos.getY(), animations.getPosition2().getY(), getStartTime(),
                    getEndTime(), t);
            pos = new Position2D(x, y);
          }
          else {
            pos = animations.getPosition2();
          }
          break;
        case CHANGESIZE:
          if (animations.getTime2() > t) {
            for (int j = 0; j < size.size(); j++) {
              double s = calcTweening(size.get(j), animations.getSizeParams2().get(j),
                      getStartTime(), getEndTime(), t);
              size.remove(j);
              size.add(j, s);
            }
          }
          else {
            size = animations.getSizeParams2();
          }
          break;
        case CHANGECOLOR:
          if (animations.getTime2() > t) {
            double r = calcTweening(color.getRed(), animations.getColor2().getRed(), getStartTime(),
                    getEndTime(), t);
            double g = calcTweening(color.getGreen(), animations.getColor2().getGreen(),
                    getStartTime(), getEndTime(), t);
            double b = calcTweening(color.getBlue(), animations.getColor2().getBlue(),
                    getStartTime(), getEndTime(), t);
            color = new RGBAdapter(r, g, b);
          }
          else {
            color = new RGBAdapter(animations.getColor2().getRed(),
                    animations.getColor2().getGreen(), animations.getColor2().getBlue());
          }
          break;
        default:
          throw new IllegalArgumentException("Invalid animation type");
      }
      //i++;
      //a = curShape.getAnimations().get(i);
    //}

    switch (curShape.getType()) {
      case "rectangle":
        shapeType = ShapeType.RECTANGLE;
        break;
      case "oval":
        shapeType = ShapeType.OVAL;
        break;
      default:
        throw new IllegalArgumentException("Invalid shape type");
    }

    IShape shape = new ShapeAdapter(curShape.getName(), shapeType, color, pos, size,
            getStartTime(), getEndTime());
    return shape;*/
    return null;
  }

  /**
   * Return a description of what this animation does.
   */
  @Override
  public String getAnimDesc() {
    return animations.toString();
  }

  @Override
  public String getType() {
    return animations.getType().toString();
  }

  /**
   * Check if these two animations can play at the same time when acting on the same shape.
   *
   * @param a The other animation.
   * @return True if they don't interfere, otherwise false.
   */
  @Override
  public boolean checkOverlap(IAnimation a) {
    return (((animations.getTime1() >= a.getStartTime()) & (animations.getTime1() <= a.getEndTime()))
            | ((animations.getTime2() >= a.getStartTime()) & (animations.getTime2() <= a.getEndTime())));
  }

  /**
   * Get the start time of this animation.
   *
   * @return the start time.
   */
  @Override
  public int getStartTime() {
    return animations.getTime1();
  }

  /**
   * Get the end time of this animation.
   *
   * @return the end time.
   */
  @Override
  public int getEndTime() {
    return animations.getTime2();
  }

  /**
   * Creates a description of the animation on the given shape with the given speed.
   *
   * @return a description of animation in svg format
   */
  @Override
  public String getSVG(IShape shape, double ticksPerSecond, boolean isLooping) {
    return null;
  }

  @Override
  public String getSVGType() {
    return null;
  }

  @Override
  public String getSVGStart() {
    return null;
  }

  @Override
  public String getSVGEnd() {
    return null;
  }

  @Override
  public String getSVGStart2() {
    return null;
  }

  @Override
  public String getSVGEnd2() {
    return null;
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