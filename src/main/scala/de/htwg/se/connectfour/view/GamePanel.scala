package de.htwg.se.connectfour.view

import scala.swing._
import scala.swing.event._
import de.htwg.se.connectfour.controller._
import java.awt.Color

class GamePanel(controller: Connect4Controller) extends GridPanel(controller.getGridHeight(), controller.getGridWidth()) {
  val gridHeight = controller.getGridHeight()
  val gridWidth = controller.getGridWidth()
  background = new Color(0, 0, 255) //Blue
  listenTo(controller)

  //Draw Cells
  for (viewRow <- (0 until gridHeight) reverse; viewCol <- (0 until gridWidth))
    contents += new CellPanel(viewRow, viewCol, controller)

  reactions += {
    case GridChanged() => repaint
    case PlayerHasWon() => Dialog.showMessage(this, controller.getActivePlayer().name + " has won the game!", "Congratulations")
  }

}