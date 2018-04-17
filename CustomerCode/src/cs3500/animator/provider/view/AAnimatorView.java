package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.model.Position2D;
import cs3500.animator.provider.hw5.animations.IAnimation;
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

    String description = "<svg width=\"5000\" height=\"5000\" version=\"1.1\"\n"
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
      //builder.append(s.getSVG(this.ticksPerSecond, this.isLooping()) + "\n");
      builder.append(getSVGShapeHelper(s) + "\n");
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

  // gets svg for given shape
  private String getSVGShapeHelper(IShape shape) {

    StringBuilder builder = new StringBuilder();

    String shapeInfo = "<" + shape.getSVGType() + " " + shape.getSVGPinhole()[0] + "=\""
            + shape.getPos().getX() + "\" " + shape.getSVGPinhole()[1] + "=\"" + shape.getPos().getY() + "\" " + shape.getSVGSides()[0] + "=\""
            + shape.getWidth() + "\" " + shape.getSVGSides()[1] + "=\"" + shape.getHeight() + "\" fill=\"rgb"
            + shape.getRGB().convertTo256() + "\" visibility=\"visible\" >\n";

    builder.append(shapeInfo);

    for (IAnimation a : shape.getAnimations()) {

      builder.append(getSVGAnimationHelper(a, shape));

    }

    if (isLooping()) {

      builder.append("<animate attributeType=\"xml\" begin=\"base.end-1ms\" dur=\"1ms\" " +
              "attributeName=\"fill\" to=\"rgb" + shape.getOriginal().getRGB().convertTo256()
              + "\" fill=\"freeze\" />\n");

      builder.append("<animate attributeType=\"xml\" begin=\"base.end-1ms\" dur=\"1ms\" "
              + "attributeName=\"" + shape.getOriginal().getSVGPinhole()[0] + "\" to=\""
              + shape.getOriginal().getPos().getX()
              + "\" fill=\"freeze\" />\n");

      builder.append("<animate attributeType=\"xml\" begin=\"base.end-1ms\" dur=\"1ms\" "
              + "attributeName=\"" + shape.getOriginal().getSVGPinhole()[1] + "\" to=\""
              + shape.getOriginal().getPos().getY()
              + "\" fill=\"freeze\" />\n");

      builder.append("<animate attributeName=\"" + shape.getOriginal().getSVGSides()[0]
              + "\" attributeType=\"XML\" "
              + "begin=\"base.end-1ms\" dur=\"1ms\" fill=\"freeze\" to=\""
              + shape.getOriginal().getWidth() + "\" />\n");

      builder.append("<animate attributeName=\"" + shape.getOriginal().getSVGSides()[1]
              + "\" attributeType=\"XML\" "
              + "begin=\"base.end-1ms\" dur=\"1ms\" fill=\"freeze\" to=\""
              + shape.getOriginal().getHeight() + "\" />\n");



    }


    return builder.toString() + "</" + shape.getSVGType() + ">\n";

  }

  // gets svg for given animation
  private String getSVGAnimationHelper(IAnimation animation, IShape shape) {

    double begin = animation.getStartTime() / ticksPerSecond;
    double dur = (animation.getEndTime() / ticksPerSecond) - begin;
    String loopingPart = Double.toString(begin);

    if (isLooping()) {
      loopingPart = "base.begin+" + begin;
    }

    if (animation.getSVGType().equals("move")) {

      String result = "<animate attributeName=\"" + shape.getSVGPinhole()[0]
              + "\" attributeType=\"XML\" begin=\""
              + loopingPart + "s\" dur=\"" + dur + "s\" fill=\"freeze\" from=\""
              + animation.getSVGStart() + "\" to=\""
              + animation.getSVGEnd() + "\" />\n"

              + "<animate attributeName=\"" + shape.getSVGPinhole()[1]
              + "\" attributeType=\"XML\" begin=\""
              + loopingPart + "s\" dur=\"" + dur + "s\" fill=\"freeze\" from=\""
              + animation.getSVGStart2() + "\" to=\""
              + animation.getSVGEnd2() + "\" />\n";

      return result;

    }
    else if (animation.getSVGType().equals("scale")) {

      String result = "<animate attributeName=\"" + shape.getSVGSides()[0]
              + "\" attributeType=\"XML\" begin=\""
              + loopingPart + "s\" dur=\"" + dur + "s\" fill=\"freeze\" from=\""
              + animation.getSVGStart() + "\" to=\""
              + animation.getSVGEnd() + "\" />\n"

              + "<animate attributeName=\"" + shape.getSVGSides()[1]
              + "\" attributeType=\"XML\" begin=\""
              + loopingPart + "s\" dur=\"" + dur + "s\" fill=\"freeze\" from=\""
              + animation.getSVGStart2() + "\" to=\""
              + animation.getSVGEnd2() + "\" />\n";

      return result;

    }


    String result = "<animate attributeName=\"" + animation.getSVGType() + "\" attributeType=\"XML\" begin=\""
            + loopingPart + "s\" dur=\"" + dur + "s\" fill=\"freeze\" from=\""
            + animation.getSVGStart() + "\" to=\""
            + animation.getSVGEnd() + "\" />\n";

    return result;

  }



}
