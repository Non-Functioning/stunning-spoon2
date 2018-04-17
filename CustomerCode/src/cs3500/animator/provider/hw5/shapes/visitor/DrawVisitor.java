package cs3500.animator.provider.hw5.shapes.visitor;

import java.awt.Graphics2D;
import java.awt.Color;

import cs3500.animator.provider.hw5.shapes.IShape;

/**
 * This class represents an implementation of a drawing visitor of the shapes.
 */
public class DrawVisitor implements IShapeVisitor<Graphics2D> {
  private Graphics2D graphics2D;

  /**
   * Default constructor that initializes its fields to the given data.
   * @param graphics2D a graphics on which we are drawing
   */
  public DrawVisitor(Graphics2D graphics2D) {
    this.graphics2D = graphics2D;

  }

  @Override
  public Graphics2D visitOval(IShape oval) {
    this.setGraphics2DColor((IShape) oval);
    this.graphics2D.fillOval(oval.getPos().getX().intValue(), oval.getPos().getY().intValue(),
            (int)oval.getWidth(), (int)oval.getHeight());

    return this.graphics2D;
  }

  @Override
  public Graphics2D visitRectangle(IShape rectangle) {
    this.setGraphics2DColor((IShape) rectangle);
    this.graphics2D.fillRect(rectangle.getPos().getX().intValue(), rectangle.getPos().getY()
                    .intValue(),
            (int)rectangle.getWidth(), (int)rectangle.getHeight());

    return graphics2D;
  }

  // setting color for this graphics element
  private void setGraphics2DColor(IShape shape) {
    this.graphics2D.setColor(new Color((float)shape.getRGB().getRed(),
            (float)shape.getRGB().getGreen(),
            (float)shape.getRGB().getBlue()));
  }
}
