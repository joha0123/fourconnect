package de.htwg.se.connectfour

import de.htwg.se.connectfour.model.impl.Grid
import de.htwg.se.connectfour.controller.Connect4Controller
import de.htwg.se.connectfour.model.impl.Player
import java.awt.Color
import de.htwg.se.connectfour.view.tui
import de.htwg.se.connectfour.util.CommandManager

object Main {
  val grid = Grid()
  val commandManager = new CommandManager()
  val controller =new Connect4Controller(grid, commandManager)

  val tui = new tui(controller)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine()) {}

  }
}

