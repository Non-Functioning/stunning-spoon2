package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * This is an implementation of the IAnimatorView interface that uses Java Swing to draw the
 * results of the animation in a panel.
 */
public class VisualAnimationView extends AAnimatorView {
  private AnimationPanel animationPanel;


  /**
   * Default constructor.
   */
  public VisualAnimationView() {
    super();

    this.setTitle("Animation");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    // uses a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    this.animationPanel = new AnimationPanel();
    this.animationPanel.setPreferredSize(new Dimension(500,500));
    this.add(this.animationPanel,BorderLayout.CENTER);


    JScrollPane jScrollPane  = new JScrollPane(this.animationPanel);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.getContentPane().add(jScrollPane);


    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  }


  @Override
  public void setTickTo(int tick) {
    this.animationPanel.setAnimations(this.getAnimationShapes(tick));
  }

  @Override
  public String getViewType() {
    return "visual";
  }
}
