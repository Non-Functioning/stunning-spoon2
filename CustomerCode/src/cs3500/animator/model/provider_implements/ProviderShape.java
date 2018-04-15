package cs3500.animator.model.provider_implements;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimatedShape;
import cs3500.animator.provider.hw5.animations.IAnimation;
import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.hw5.shapes.visitor.IShapeVisitor;

public class ProviderShape extends AnimatedShape implements IShape {

  public ProviderShape(String shapeName, ShapeType shape, RGB initialColor,
                       Position2D initialPosition, List<Double> size, Integer appearTime,
                       Integer disappearTime) {
    super(shapeName, shape, initialColor, initialPosition, size, appearTime, disappearTime);
  }

  @Override
  public void setWidthHeight(double width, double height) {
    List<Double> size = new ArrayList<>();
    size.add(width);
    size.add(height);
    this.initialSize = size;
  }

  @Override
  public void setRGB(RGB rgb) {

  }

  @Override
  public void setPos(Position2D pos) {

  }

  @Override
  public String getAnimDesc() {
    return null;
  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public String getReference() {
    return null;
  }

  @Override
  public String getDimensionsDesc() {
    return null;
  }

  @Override
  public double getWidth() {
    return 0;
  }

  @Override
  public double getHeight() {
    return 0;
  }

  @Override
  public RGB getRGB() {
    return null;
  }

  @Override
  public Position2D getPos() {
    return null;
  }

  @Override
  public void addAnimation(IAnimation animation) throws IllegalArgumentException {

  }

  @Override
  public IShape shapeAtTime(int t) {
    return null;
  }

  @Override
  public String getSVG(double ticksPerSecond, boolean isLooping) {
    return null;
  }

  @Override
  public <T> T accept(IShapeVisitor<T> visitor) {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public List<IAnimation> getAnimations() {
    return null;
  }

  @Override
  public String[] getSVGPinhole() {
    return new String[0];
  }

  @Override
  public String[] getSVGSides() {
    return new String[0];
  }

  @Override
  public int getDisappearTime() {
    return 0;
  }

  @Override
  public boolean isPointWithinShape(Position2D point) {
    return false;
  }
}
