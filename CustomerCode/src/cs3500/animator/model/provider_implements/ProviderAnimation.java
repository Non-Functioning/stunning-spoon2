package cs3500.animator.model.provider_implements;

import cs3500.animator.model.Animations;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;

public class ProviderAnimation extends Animations implements IAnimation {
  @Override
  public IShape shapeAtTime(int t, IShape curShape) {
    return null;
  }

  @Override
  public String getAnimDesc() {
    return null;
  }

  @Override
  public boolean checkOverlap(IAnimation a) {
    return false;
  }

  @Override
  public int getStartTime() {
    return 0;
  }

  @Override
  public int getEndTime() {
    return 0;
  }

  @Override
  public String getSVG(IShape shape, double ticksPerSecond, boolean isLooping) {
    return null;
  }
}
