package cs3500.animator.provider.hw5.shapes.visitor;

import cs3500.animator.provider.hw5.shapes.IShape;

/**
 * This interface represent a visitor over all implementations of shapes.
 */
public interface IShapeVisitor<T> {
  /**
   * Returns a result of applying this visitor to an oval.
   * @param oval a shape that accepts a visitor
   * @return result of applying this visitor to the oval
   */
  T visitOval(IShape oval);

  /**
   * Returns a result of applying this visitor to a rectangle.
   * @param rectangle a shape that accepts a visitor
   * @return result of applying this visitor to the oval
   */
  T visitRectangle(IShape rectangle);
}
