package cs3500.animator.provider.model;

import java.util.List;

import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

/**
 * The model interface for the EasyAnimator program. Stores information about all shapes of the
 * animation and all operations on them. Allows to add new shapes and new animations, get the
 * textual description of the animation, and the state of animation at any given tick time.
 */
public interface IEasyAnimatorModel {
  /**
   * Add a new shape to keep track of in the model.
   *
   * @param shape The given shape.
   */
  void addShape(IShape shape);

  /**
   * Add a new animation to a shape. If the animation interferes with another already on the shape,
   * then throw an error.
   *
   * @param animation  The given animation.
   * @param shapeIndex The index of the shape to add the animation to, beginning at 0.
   * @throws IllegalArgumentException if the animation can't be added to the shape.
   */
  void addAnimation(IAnimation animation, int shapeIndex) throws IllegalArgumentException;

  /**
   * Returns a description of the entire animation. It first lists the shapes
   * used in the animation and describes them. Next it lists the different animation steps that
   * are performed on the shapes.
   *
   * @return A string containing the description.
   */
  String getDescription();

  /**
   * Returns a list of the shapes at time t.
   *
   * @param t The given time.
   * @return the shapes at time t.
   */
  List<IShape> getStateAtTime(int t);
}
