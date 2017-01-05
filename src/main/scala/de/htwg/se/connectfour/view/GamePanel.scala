package de.htwg.se.connectfour.view

import scala.swing._
import scala.swing.event._
import de.htwg.se.connectfour.controller._
import scala.language.postfixOps
import de.htwg.se.connectfour.model.impl.Player

class GamePanel(controller: Connect4Controller) extends GridPanel(controller.getGridHeight(), controller.getGridWidth()) {

  val gridHeight = controller.getGridHeight()
  val gridWidth = controller.getGridWidth()
  background = new Color(0, 0, 255) //Blue
  listenTo(controller)

  //Draw Cells
  for (viewRow <- (0 until gridHeight) reverse; viewCol <- (0 until gridWidth))
    contents += new CellPanel(viewRow, viewCol, controller)

  reactions += {
    case e: GridChanged => repaint()
    case e: PlayerHasWon => showDialog("Congratulations", e.winner.name + " has won the game!")
    case e: Draw => showDialog("Game Draw", "No more moves possible")
  }
  
  def showDialog(title: String, message: String) = Dialog.showMessage(this, message, title)

}