package cs3500.animator.adapters;

import cs3500.animator.model.Animations;
import cs3500.animator.model.IAnimations;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

public class AnimationAdapter extends Animations implements IAnimation {
  private IAnimations animations;

  AnimationAdapter(IAnimations iAnimations) {
    this.animations = iAnimations;
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
    switch (animations.getAnimateType()) {
      case MOVE:
        return "move";
      case CHANGESIZE:
        return "scale";
      default:
        return "";
    }
  }

  @Override
  public String getSVGStart() {
    switch (animations.getAnimateType()) {
      case MOVE:
        return animations.getPosition1().getX().toString();
      case CHANGESIZE:
        return animations.getSizeParams1().get(0).toString();
      default:
        return "";
    }
  }

  @Override
  public String getSVGEnd() {
    switch (animations.getAnimateType()) {
      case MOVE:
        return animations.getPosition2().getX().toString();
      case CHANGESIZE:
        return animations.getSizeParams2().get(0).toString();
      default:
        return "";
    }
  }

  @Override
  public String getSVGStart2() {
    switch (animations.getAnimateType()) {
      case MOVE:
        return animations.getPosition1().getY().toString();
      case CHANGESIZE:
        return animations.getSizeParams1().get(1).toString();
      default:
        return "";
    }
  }

  @Override
  public String getSVGEnd2() {
    switch (animations.getAnimateType()) {
      case MOVE:
        return animations.getPosition2().getY().toString();
      case CHANGESIZE:
        return animations.getSizeParams2().get(1).toString();
      default:
        return "";
    }
  }
}