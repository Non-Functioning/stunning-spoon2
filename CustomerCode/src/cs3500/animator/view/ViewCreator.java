package cs3500.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.adapters.ControllerAdapter;
import cs3500.animator.adapters.ModelAdapter;
import cs3500.animator.adapters.ShapeAdapter;
import cs3500.animator.controller.Controller;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.provider.hw5.shapes.IShape;
import cs3500.animator.provider.model.IEasyAnimatorModel;
import cs3500.animator.provider.view.HybridView;
import cs3500.animator.provider.view.IAnimatorView;
import cs3500.animator.provider.view.SVGAnimationView;
import cs3500.animator.provider.view.TextualAnimationView;

/**
 * This class represents a factory of views. It takes in a view type
 * and creates the corresponding view.
 */
public class ViewCreator {
  /**
   * This method creates a view based on the given parameters.
   *
   * @param type   view type
   * @param model  model the view is based on
   * @param tempo  ticks per sec
   * @param output location of where to send the view to
   */
  public static void create(ViewType type, SimpleAnimationModel model, int tempo,
                            String output) throws IOException {
    switch (type) {
      case TEXT:
        if (output.equals("out")) {
          new TextView(model, tempo);
        } else {
          new TextView(model, tempo, output);
        }
        break;
      case VISUAL:
        new VisualView(model, tempo);
        break;
      case SVG:
        if (output.equals("out")) {
          new SVGView(model, tempo);
        } else {
          new SVGView(model, tempo, output);
        }
        break;
      case INTERACTIVE:
        ViewInterface viewI = new InteractiveView(model, tempo);
        if (!output.equals("out")) {
          new Controller(model, viewI, output);
        }
        else {
          new Controller(model, viewI);
        }
        break;
      case PROVIDERHYBRID:
        //cs3500.animator.provider.controller.IController controller
        //        = new ControllerAdapter(model, new HybridView());
        //controller.run(output, tempo);
        new HybridView();
        break;
      case PROVIDERTEXT:
        IAnimatorView viewT = new TextualAnimationView();
        viewT.setTicksPerSecondTo(tempo);
        viewT.setDescription(model.printAnimation());
        String viewText = viewT.getDescription();
        if (output.equals("out")) {
          System.out.print(viewText);
        }
        else {
          try (PrintWriter out = new PrintWriter(output)) {
            out.println(viewText);
          } catch (FileNotFoundException e) {
            // Nothing here
          }
        }
        break;
      case PROVIDERSVG:
        IAnimatorView viewSVG = new SVGAnimationView();
        List<IShape> iShapes = new ArrayList<>();
        for (int i = 0; i < model.getShapes().size(); i++) {
          iShapes.add(new ShapeAdapter(model.getShape(i)));
        }
        viewSVG.setShapes(iShapes);
        String svgText = viewSVG.getDescription();
        if (output.equals("out")) {
          System.out.print(svgText);
        }
        else {
          try (PrintWriter out = new PrintWriter(output)) {
            out.println(svgText);
          } catch (FileNotFoundException e) {
            // Nothing here
          }
        }
        break;
      case PROVIDERVISUAL:
        break;
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }

  /**
   * An enumeration for the representation of different view types.
   */
  public enum ViewType {
    TEXT, VISUAL, SVG, INTERACTIVE, PROVIDERHYBRID, PROVIDERTEXT, PROVIDERSVG, PROVIDERVISUAL, NULL
  }
}
