package cs3500.animator.provider.view;


/**
 * This is an implementation of the IAnimatorView interface that creates a description of this
 * animation in SVG format.
 */
public class SVGAnimationView extends AAnimatorView {
  /**
   * Default constructor for SVGAnimationView.
   */
  public SVGAnimationView() {
    super();
  }

  @Override
  public String getDescription() {
    return this.getSVGHelper();
  }

  @Override
  public String getViewType() {
    return "svg";
  }
}
