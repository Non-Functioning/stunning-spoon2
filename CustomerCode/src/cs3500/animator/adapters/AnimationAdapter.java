package cs3500.animator.adapters;

import java.util.List;

import cs3500.animator.model.Animations;
import cs3500.animator.model.ChangeShapeColor;
import cs3500.animator.model.ChangeShapeSize;
import cs3500.animator.model.IAnimatedShape;
import cs3500.animator.model.IPosition2D;
import cs3500.animator.model.IRGB;
import cs3500.animator.model.MoveShape;
import cs3500.animator.model.ShapeAppears;
import cs3500.animator.model.ShapeDisappears;
import cs3500.animator.model.StillShape;
import cs3500.animator.model.enums.AnimateTypes;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

public class AnimationAdapter implements IAnimation {
  Animations animations;

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
    return (animations.getTime2() < a.getStartTime()) && (a.getEndTime() < animations.getTime1());
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
}