package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.hw5.shapes.Position2D;

/**
 * This is an interface that represents different kinds of view for the Easy Animation.
 * Some of the methods might not be implemented by all view types.
 */
public interface IAnimatorView {

  /**
   * Makes current view visible for user.
   */
  void setVisible();

  /**
   * Updates current view upon new event or time tick.
   */
  void refresh();

  /**
   * Sets the list of shapes of this animation to the given list.
   * @param shapes are the shapes to be displayed in animation
   * @throws IllegalArgumentException if shapes is null
   */
  void setShapes(List<IShape> shapes) throws IllegalArgumentException;

  /**
   * Shows a given error message if some error occurred during animation.
   * @param error an error message to be displayed
   */
  void showErrorMsg(String error);

  /**
   * Returns a text description of a view. Not supported for all views.
   * @return a description of all shapes and commands performed on them.
   */
  String getDescription();

  /**
   * Assigns a given text description to this view. Not supported for all views.
   * @param description a textual description of the animation
   * @throws IllegalArgumentException if given description is null
   */
  void setDescription(String description) throws IllegalArgumentException;

  /**
   * Sets all shapes of this view to their state at the given tick. Not supported for all views.
   * @param tick a desired tick of animation
   */
  void setTickTo(int tick);

  /**
   * Sets a speed of this animation to a given ticks per second.
   * @param ticksPerSecond a desired speed of animation
   */
  void setTicksPerSecondTo(double ticksPerSecond);

  /**
   * A getter method for the type of current view.
   * @return a type of view
   */
  String getViewType();

  /**
   * Assigns to a button in the animation given actionListener.
   * @param actionListener a listener for the buttons activities
   */
  void setButtonListener(ActionListener actionListener);

  /**
   * Assigns a given mouseListener to this animation.
   * @param mouseListener a listener for a mouse activities
   */
  void setMouseListener(MouseListener mouseListener);

  /**
   * Returns true if this animation is set to be looping.
   */
  boolean isLooping();

  /**
   * Returns a speed of this animation in ticks per seconds.
   */
  double getTicksPerSecond();

  /**
   * Removes a shape at given position point at a given time tick from this animation.
   * @param point a position of a shape to be removed
   * @param tick a time at which a shape at the given position is to be removed
   */
  void deselectShape(Position2D point, int tick);

  /**
   * Returns a copy of the shapes of this animation.
   */
  List<IShape> getShapes();

  /**
   * Return the time that this animation will finish.
   */
  int getAnimEndTime();
}
