package cs3500.animator.provider.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.hw5.shapes.visitor.IShapeVisitor;
import cs3500.animator.provider.hw5.shapes.visitor.DrawVisitor;

/**
 * This class represents an implementation of JPanel for EasyAnimator to be used by its visual view.
 */
public class AnimationPanel extends JPanel {
  private List<IShape> animations;


  /**
   * Default constructor for AnimationPanel that initialises its list to empty new list.
   */
  public AnimationPanel() {
    super();
    this.animations = new ArrayList<>();
  }

  /**
   * Assigns given list of shapes to this panel.
   *
   * @param animations a list with all shapes of this animation
   */
  public void setAnimations(List<IShape> animations) {
    this.animations = animations;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // draws each shape of this animation on this graphics component
    for (IShape shape : this.animations) {
      IShapeVisitor<Graphics2D> visitor = new DrawVisitor(g2d);
      g2d = shape.accept(visitor);
    }
  }
}
