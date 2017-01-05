package de.htwg.se.connectfour

import de.htwg.se.connectfour.model.impl.Grid
import de.htwg.se.connectfour.controller.Connect4Controller
import de.htwg.se.connectfour.view._
import de.htwg.se.connectfour.util.CommandManager

object Main {
  def main(args: Array[String]): Unit = {
    val grid = new Grid(6, 7)
    val commandManager = new CommandManager()
    val controller = new Connect4Controller(grid, commandManager)    
    
    val tui = new Tui(controller)
    val gui = new Gui(controller)

    while (true)
      tui.processInputLine()
  }
}

