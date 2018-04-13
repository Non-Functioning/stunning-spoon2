package cs3500.animator.provider.controller;

/**
 * The controller interface for the EasyAnimator program. Shows an animation in the given output
 * source and with the given speed. Manages any interactions with the user.
 */
public interface IController {

  /**
   * Starts a program of Easy Animator.
   * @param output the name of the output file
   *               if null streams the output to a console
   * @param ticksPerSecond the speed of animation
   */
  void run(String output, double ticksPerSecond);

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
  String processCommand(String command);

  /**
   * Returns true if the animation is currently playing.
   */
  boolean isPlaying();

}
