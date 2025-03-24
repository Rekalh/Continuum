# Continuum
A simple and bare-bones Transport Phenomena visualizing tool (and soon-to-be numerical solver). It is mainly a passion project and is not expected to be a top of the shelf product, nor a proper replacement for your typical CFD solver.

## Subprojects
The project consists of two **subprojects**

- ## `core`
Everything is here. From rendering to solving, all will be included in this project.
- ## `lwjgl3`
Here is the setup for the typical desktop application. Things like creating the main window are handled here.

## Features and future goals for this project
Everything that has been implemented so far is:

- ImGui integration, with custom ImGui window rendering.
- Rendering in custom ImGui windows with shader programs.
- A simple project creation window. There are options for selecting basic 1D and 2D geometries (`Linear`, `Rectangular`, `Disk`), as well as inputting a project name.
- Out of these only `Linear` has been partly implemented. (So far it renders a rectangle with variable dimensions and a linear interpolation between three specified colors).

The long-term goals are:

- Fully custom geometries.
- An integrated numerical solver for both steady-state and dynamic models.
- Visualization of the numerical solutions on the specified geometry.
- Perhaps 3D support.

Of course this is not final, and the scope of the project may change a lot during development.
