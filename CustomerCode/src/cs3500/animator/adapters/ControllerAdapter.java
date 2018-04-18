package cs3500.animator.adapters;

import cs3500.animator.provider.controller.IController;
import cs3500.animator.provider.model.IEasyAnimatorModel;
import cs3500.animator.provider.view.IAnimatorView;

public class ControllerAdapter implements IController {
  IAnimatorView view;
  IEasyAnimatorModel model;

  public ControllerAdapter(IEasyAnimatorModel model, IAnimatorView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Starts a program of Easy Animator.
   * @param output the name of the output file
   *               if null streams the output to a console
   * @param ticksPerSecond the speed of animation
   */
  @Override
  public void run(String output, double ticksPerSecond) {

  }

  /**
   * Parses and executes given command.
   * @param command a command to be executed. Some of commands are:
   *                - "play" starts animation
   *                - "pause" stops animation
   *                - "restart" starts animation from the beginning
   *                - "speedMinus" decreases animation speed
   *                - "speedPlus" increases animation speed
   *                - "saveSVG" saves a current animation as svg formatted description
   *
   * @return a message about successful / unsuccessful command execution
   */
  @Override
  public String processCommand(String command) {
    switch (command) {
      case "play":
        break;
      case "pause":
        break;
      case "restart":
        break;
      case "speedMinus":
        break;
      case "speedPlus":
        break;
      case "saveSVG":
        break;
      case "showAll":
        break;
      default:
        return "Unsuccessful command execution";
    }
    return "Successful command execution";
  }

  @Override
  public boolean isPlaying() {
    return false;
  }
}
