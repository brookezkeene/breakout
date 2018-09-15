Breakout
====

Author: Brooke Keene

### Description

This project began as an assignment for my Computer Science class - Software Design and Implementation (CS 308) -
to develop a breakout game that included the basic elements, a ball, a paddle, blocks and power ups.
However, I wanted to continue to improve upon my basic game, its design, and add other features.

*This is still a work in progress*

### Running the Program

Main class: [Main.java](https://github.com/brookezkeene/breakout/blob/master/src/game/Main.java)

Rules:

* Clear all level maps to win the game!
* Blocks that are cracked only require one hit to be broken. Blocks that are smooth require 2 hits to be broken.
* After level 1, some blocks will contain power ups and power downs. To obtain the power, the player must hit
the icon. 

Key/Mouse inputs:

    C       - Clear Splash Screen
    Enter   - Begin Level
    Right   - Move Paddle Right
    Left    - Move Paddle Left

Cheat keys:

    R       - Reset
    L       - Extra Life
    S       - Skip Level
    Space   - Pause or Resume Game

Known Bugs:

* successive block collisions that occur in a short span of time where blocks share a border sometimes look odd
* ball behavior around the edges of the paddle sometimes causes ball to oscillate directions
* paddle does not fully reach the wall every level

#### Notes

    Currently implementing all power ups and power downs.

#### Resources Used
Images from: https://opengameart.org/sites/default/files/preview_598.png
