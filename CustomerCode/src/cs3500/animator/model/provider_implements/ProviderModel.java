package cs3500.animator.model.provider_implements;

import java.util.List;

import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.model.IEasyAnimatorModel;

public class ProviderModel extends SimpleAnimation implements IEasyAnimatorModel {
  /**
   * Add a new shape to keep track of in the model.
   *
   * @param shape The given shape.
   */
  @Override
  public void addShape(IShape shape) {

  }

  /**
   * Add a new animation to a shape. If the animation interferes with another already on the shape,
   * then throw an error.
   *
   * @param animation  The given animation.
   * @param shapeIndex The index of the shape to add the animation to, beginning at 0.
   * @throws IllegalArgumentException if the animation can't be added to the shape.
   */
  @Override
  public void addAnimation(IAnimation animation, int shapeIndex) throws IllegalArgumentException {

  }

  /**
   * Returns a description of the entire animation. It first lists the shapes
   * used in the animation and describes them. Next it lists the different animation steps that
   * are performed on the shapes.
   *
   * @return A string containing the description.
   */
  @Override
  public String getDescription() {
    return null;
  }

  /**
   * Returns a list of the shapes at time t.
   *
   * @param t The given time.
   * @return the shapes at time t.
   */
  @Override
  public List<IShape> getStateAtTime(int t) {
    return null;
  }
}
