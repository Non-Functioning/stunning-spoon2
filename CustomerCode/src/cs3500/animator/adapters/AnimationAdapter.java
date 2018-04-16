package cs3500.animator.adapters;

import cs3500.animator.model.Animations;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

public class AnimationAdapter extends Animations implements IAnimation {

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
    return null;
  }

  /**
   * Check if these two animations can play at the same time when acting on the same shape.
   * @param a The other animation.
   * @return True if they don't interfere, otherwise false.
   */
  @Override
  public boolean checkOverlap(IAnimation a) {
    return false;
  }

  /**
   * Get the start time of this animation.
   * @return the start time.
   */
  @Override
  public int getStartTime() {
    return time1;
  }

  /**
   * Get the end time of this animation.
   * @return the end time.
   */
  @Override
  public int getEndTime() {
    return time2;
  }

  /**
   * Creates a description of the animation on the given shape with the given speed.
   * @return a description of animation in svg format
   */
  @Override
  public String getSVG(IShape shape, double ticksPerSecond, boolean isLooping) {
    return null;
  }
}
