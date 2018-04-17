package cs3500.animator.adapters;

import cs3500.animator.model.Animations;
import cs3500.animator.model.IAnimations;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

public class AnimationAdapter extends Animations implements IAnimation {
  private IAnimations animations;

  AnimationAdapter(IAnimations iAnimations) {
    this.animations = iAnimations;
    /*switch (animations.getAnimateType()) {
      case MOVE:
        this.animations = new MoveShape(animations.getChangedShape(), animations.getPosition1(),
                animations.getPosition2(), animations.getTime1(), animations.getTime2());
        break;
      case APPEAR:
        this.animations = new ShapeAppears(animations.getChangedShape(), animations.getTime1());
        break;
      case DISAPPEAR:
        this.animations = new ShapeDisappears(animations.getChangedShape(), animations.getTime1());
        break;
      case STILL:
        this.animations = new StillShape(animations.getChangedShape(), animations.getTime1());
        break;
      case CHANGESIZE:
        this.animations = new ChangeShapeSize(animations.getChangedShape(),
                animations.getSizeParams1(), animations.getSizeParams2(), animations.getTime1(),
                animations.getTime2());
        break;
      case CHANGECOLOR:
        this.animations = new ChangeShapeColor(animations.getChangedShape(),
                animations.getColor1(), animations.getColor2(), animations.getTime1(),
                animations.getTime2());
    }*/
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
      switch (animations.getAnimateType()) {
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

    switch (curShape.getAnimateType()) {
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
    return animations.getAnimateType().toString();
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
    switch (this.type) {
      case MOVE:
        return "move";

      case CHANGESIZE:
        return "scale";

      default:
        throw new IllegalArgumentException("Wrong SVG type request.");
    }
  }

  @Override
  public String getSVGStart() {
    switch (this.type) {
      case MOVE:
        return super.getPosition1().getX().toString();

      case CHANGESIZE:
        return super.getSizeParams1().get(0).toString();

      default:
        throw new IllegalArgumentException("Wrong SVG type request.");
    }
  }

  @Override
  public String getSVGEnd() {
    switch (this.type) {
      case MOVE:
        return super.getPosition2().getX().toString();

      case CHANGESIZE:
        return super.getSizeParams2().get(0).toString();

      default:
        throw new IllegalArgumentException("Wrong SVG type request.");
    }
  }

  @Override
  public String getSVGStart2() {
    switch (this.type) {
      case MOVE:
        return super.getPosition1().getY().toString();

      case CHANGESIZE:
        return super.getSizeParams1().get(1).toString();

      default:
        throw new IllegalArgumentException("Wrong SVG type request.");
    }
  }

  @Override
  public String getSVGEnd2() {
    switch (this.type) {
      case MOVE:
        return super.getPosition2().getY().toString();

      case CHANGESIZE:
        return super.getSizeParams2().get(1).toString();

      default:
        throw new IllegalArgumentException("Wrong SVG type request.");
    }
  }

  /**
   * This method calculates the tweening value of an animation at a given time.
   * This method is used for move, change color, and change size animations.
   *
   * @param initVal   initial value
   * @param finalVal  final value
   * @param initTick  time of initial value
   * @param finalTick time of final value
   * @param tick      current time
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