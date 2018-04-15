package cs3500.animator.model.provider_implements;

import java.util.List;

import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.model.IEasyAnimatorModel;

public class ProviderModel extends SimpleAnimation implements IEasyAnimatorModel {
  @Override
  public void addShape(IShape shape) {

  }

  @Override
  public void addAnimation(IAnimation animation, int shapeIndex) throws IllegalArgumentException {

  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public List<IShape> getStateAtTime(int t) {
    return null;
  }
}
