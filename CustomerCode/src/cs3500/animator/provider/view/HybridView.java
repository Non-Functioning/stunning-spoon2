package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.hw5.shapes.Position2D;

/**
 * This is an implementation of the IAnimatorView interface that uses Java Swing to draw the
 * results of the animation in a panel and allows a user interactions such as pausing animation,
 * restarting, looping choice, change of speed and others.
 */
public class HybridView extends AAnimatorView {
  private AnimationPanel animationPanel;

  private JButton play;
  private JButton pause;
  private JButton restart;
  private JButton speedPlus;
  private JButton speedMinus;
  private JButton saveSVG;
  private JButton showAllShapes;

  private JCheckBox loop;
  private JLabel currentSpeed;

  /**
   * A standard constructor for a hybrid view.
   */
  public HybridView() {
    super();

    this.setTitle("Animation");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.animationPanel = new AnimationPanel();

    this.animationPanel.setPreferredSize(
            new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
                    Toolkit.getDefaultToolkit().getScreenSize().height));


    // create buttons & set Action Commands
    this.play = new JButton("Play");
    this.play.setActionCommand("play");

    this.pause = new JButton("Pause");
    this.pause.setActionCommand("pause");

    this.restart = new JButton("Restart");
    this.restart.setActionCommand("restart");

    this.speedMinus = new JButton("-");
    this.speedMinus.setActionCommand("speedMinus");

    this.speedPlus = new JButton("+");
    this.speedPlus.setActionCommand("speedPlus");

    this.saveSVG = new JButton("Save as SVG");
    this.saveSVG.setActionCommand("saveSVG");

    this.showAllShapes = new JButton("Show all shapes");
    this.showAllShapes.setActionCommand("showAll");

    this.loop = new JCheckBox("Loop");

    this.currentSpeed = new JLabel(Double.toString(1 / this.ticksPerSecond));

    // add buttons to control panel
    JPanel controlPanel = new JPanel();
    controlPanel.add(this.play);
    controlPanel.add(this.pause);
    controlPanel.add(this.restart);
    controlPanel.add(this.currentSpeed);
    controlPanel.add(this.speedPlus);
    controlPanel.add(this.speedMinus);
    controlPanel.add(this.loop);
    controlPanel.add(this.showAllShapes);
    controlPanel.add(this.saveSVG);


    // add panels to this view
    JPanel centerPanel = new JPanel(new BorderLayout());
    JScrollPane jScrollPane  = new JScrollPane(this.animationPanel);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    centerPanel.add(jScrollPane);

    this.add(centerPanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);

  }

  /**
   * Constructor to construct a hybrid view with looping on or off at the start.
   * @param isLooping a parameter that is true if this animation is looping
   */
  public HybridView(boolean isLooping) {
    this();
    this.loop.setSelected(isLooping);
  }

  @Override
  public String getViewType() {
    return "hybrid";
  }

  @Override
  public void setButtonListener(ActionListener actionListener) {
    this.play.addActionListener(actionListener);
    this.pause.addActionListener(actionListener);
    this.restart.addActionListener(actionListener);
    this.speedMinus.addActionListener(actionListener);
    this.speedPlus.addActionListener(actionListener);
    this.saveSVG.addActionListener(actionListener);
    this.showAllShapes.addActionListener(actionListener);
  }

  @Override
  public boolean isLooping() {
    return this.loop.isSelected();
  }


  @Override
  public void setTicksPerSecondTo(double ticksPerSecond) {
    super.setTicksPerSecondTo(ticksPerSecond);
    this.currentSpeed.setText(Double.toString(this.ticksPerSecond));

  }

  @Override
  public void setTickTo(int tick) {
    this.animationPanel.setAnimations(this.getAnimationShapes(tick));
  }


  @Override
  public void deselectShape(Position2D point, int tick) {
    List<IShape> shapes = this.getAnimationShapes(tick);

    int index = shapes.size();
    boolean shapeClicked = false;

    // traverse the list of shapes from the end and if you find a shape that has a point within it,
    // mark flag as true and remember the index
    for (int i = index - 1; i >= 0 ; i -= 1) {
      IShape shape = shapes.get(i);
      if (shape.isPointWithinShape(point)) {
        index = i;
        shapeClicked = true;
        break;
      }
    }

    // remove the shape from the animation
    if (shapeClicked) {
      this.animationShapes.remove(index);
    }
  }


  @Override
  public String getDescription() {
    return this.getSVGHelper();
  }

  @Override
  public void setMouseListener(MouseListener mouseListener) {
    this.animationPanel.addMouseListener(mouseListener);
  }
}
