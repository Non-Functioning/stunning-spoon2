The Hybrid View
	Runs the animation and the user can click buttons to:
		- play
		- pause
		- restart
		- increase speed
		- decrease speed
		- reselect all shapes
		- check looping on/off
		- save svg file

	For selection of shapes in our hybrid view, all shapes start as select. Clicking
	a shape on the screen will deselect and hide it. We have a button that will reselect
	all shapes so that they all reappear.

Visual View

	Runs the animation of the given file with the specified speed, no looping.

SVG View

	Outputs a string of the animation in SVG format, no looping, and either writes the
	string to a file or prints it in the console.

Text View

	Outputs a string description of the animation and either writes the string to
	a file or prints it in the console.

All views have a list of shapes, which are the shapes in the animation.
Each shape then has its own list of animations that act on it. In our design a lot of
methods work their work through this list to piece things together for the final output.


IShape explanation:

There is an abstract shape class implementing IShape that holds everything a shape needs

	variables:
		final IShape originalShape // the shape as it is constructed at the beginning
		String name
		double width
		double height
		RGB rgb
		Position2D pos
		int appear
		int disappear
		List<IAnimation> animations

IAnimation:

There is an abstract animation class implementing everything common among animations

	variables:
		final int startTime
		final int endTime
		String shapeName // name of the shape this animation acts on


Some basic classes we have that don't have interfaces include:

Position2D
	Represents a 2D position, mainly copied from the class code.
	Each shape has one.

RGB
	Represents RGB values, each from 0.0 to 1.0

	Each shape has one.

	methods:

		double getRed():
			returns the red value

		double getGreen():
			returns the green value

		double getBlue():
			returns the blue value

		String convertTo256():
			return the rgb values in range 0 to 255, for use with svg

MouseClickDetection
	Extends MouseListener
	For use with clicking shapes to deselect them.

	Has variables:
	IAnimatorView view;
	int tick

	Overrides the void mouseClicked(MouseEvent e) method