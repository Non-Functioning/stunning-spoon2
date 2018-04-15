package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.model.Position2D;
import cs3500.animator.provider.hw5.shapes.IShape;

/**
 * An abstract implementation for the IAnimatorView interface that implements common methods
 * for view classes.
 */
public abstract class AAnimatorView extends JFrame implements IAnimatorView {
  protected List<IShape> animationShapes;
  protected double ticksPerSecond;

  // Default constructor for AAnimator view that calls initializes its fields
  protected AAnimatorView() {
    super();
    this.animationShapes = new ArrayList<>();
    this.ticksPerSecond = 1;
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setShapes(List<IShape> shapes) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("The list of shapes shouldn't be null.");
    }
    this.animationShapes = shapes;
  }

  @Override
  public void showErrorMsg(String error) {
    JOptionPane.showMessageDialog(this, error,"Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public String getDescription() {
    throw new IllegalArgumentException("This method is not supported by this type of view.");
  }

  @Override
  public void setDescription(String description) {
    throw new IllegalArgumentException("This method is not supported by this type of view.");
  }

  @Override
  public void setTickTo(int tick) {
    throw new IllegalArgumentException("This method is not supported by this type of view.");
  }

  @Override
  public void setTicksPerSecondTo(double ticksPerSecond) {
    if (ticksPerSecond < 0) {
      throw new IllegalArgumentException("Ticks per seconds must be non negative number.");
    } else {
      this.ticksPerSecond = ticksPerSecond;
    }
  }

  @Override
  public void setButtonListener(ActionListener actionListener) {
    throw new UnsupportedOperationException("Setting button listener is not supported");
  }

  @Override
  public double getTicksPerSecond() {
    return this.ticksPerSecond;
  }

  @Override
  public boolean isLooping() {
    return false;
  }

  // returns a list of shapes at the given time tick.
  protected List<IShape> getAnimationShapes(int tick) {
    List<IShape> shapeList = new ArrayList<>();

    for (IShape shape : this.animationShapes) {
      shapeList.add(shape.shapeAtTime(tick));
    }

    return shapeList;
  }

  @Override
  public void deselectShape(Position2D point, int tick) {
    throw new UnsupportedOperationException("Unsupported operation of deselecting shape");
  }


  @Override
  public void setMouseListener(MouseListener mouseListener) {
    throw new UnsupportedOperationException("Setting mouse listener is not supported");
  }

  @Override
  public List<IShape> getShapes() {
    return new ArrayList<>(this.animationShapes);
  }


  // Creates an SVG formatted text for this animation.
  protected String getSVGHelper() {
    int duration = this.getAnimEndTime();
    StringBuilder builder = new StringBuilder();

    String description = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n";

    builder.append(description);

    if (this.isLooping()) {
      builder.append("<rect>\n"
              + "   <animate id=\"base\" begin=\"0;base.end\" dur=\""
              + ((duration / this.ticksPerSecond) + 0.001) + "s\" attributeName="
              + "\"visibility\" from=\"hide\" to=\"hide\"/>\n"
              + "</rect>");
    }

    for (IShape s : this.animationShapes) {
      builder.append(s.getSVG(this.ticksPerSecond, this.isLooping()) + "\n");
    }

    return builder.toString() + "\n</svg>";
  }


  @Override
  public int getAnimEndTime() {

    int endTime = 0;

    for (IShape s : this.animationShapes) {
      if (s.getDisappearTime() > endTime) {
        endTime = s.getDisappearTime();
      }
    }

    return endTime;
  }
}
