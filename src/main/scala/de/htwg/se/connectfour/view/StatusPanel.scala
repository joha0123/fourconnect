package de.htwg.se.connectfour.view

import scala.swing._
import de.htwg.se.connectfour.controller._

class StatusPanel(controller: Connect4Controller) extends GridPanel(4, 1) {
  preferredSize = new Dimension(100, 600)
  listenTo(controller)

  reactions += {
    case e: GridChanged => redrawBorder()
    case e: PlayerHasWon => redraw()
  }

  val p0 = controller.getPlayer(0)
  val p1 = controller.getPlayer(1)

  val l00 = new Label {
    text = p0.name
    font = new Font("Verdana", 1, 15)
  }
  val bp00 = new BorderPanel {
    add(l00, BorderPanel.Position.Center)
  }

  val l01 = new Label {
    text = p0.gamesWon.toString()
    font = new Font("Verdana", 1, 36)
  }
  val bp01 = new BorderPanel {
    add(l01, BorderPanel.Position.North)
  }

  val l10 = new Label {
    text = p1.name
    font = new Font("Verdana", 1, 15)
  }
  val bp10 = new BorderPanel {
    add(l10, BorderPanel.Position.Center)
  }

  val l11 = new Label {
    text = p1.gamesWon.toString()
    font = new Font("Verdana", 1, 36)
  }
  val bp11 = new BorderPanel {
    add(l11, BorderPanel.Position.North)
  }

  contents += bp00
  contents += bp01
  contents += bp10
  contents += bp11

  //Set border for the first active player
  redrawBorder()

  def redrawBorder(): Unit = {
    if (controller.getActivePlayer() == p0) {
      bp00.border = Swing.MatteBorder(5, 5, 0, 5, p0.color)
      bp01.border = Swing.MatteBorder(0, 5, 5, 5, p0.color)
      bp10.border = Swing.EmptyBorder
      bp11.border = Swing.EmptyBorder
    } else {
      bp10.border = Swing.MatteBorder(5, 5, 0, 5, p1.color)
      bp11.border = Swing.MatteBorder(0, 5, 5, 5, p1.color)
      bp00.border = Swing.EmptyBorder
      bp01.border = Swing.EmptyBorder
    }
    repaint()
  }

  def redraw(): Unit = {
    val p0 = controller.getPlayer(0)
    val p1 = controller.getPlayer(1)
    l00.text = p0.name
    l01.text = p0.gamesWon.toString

    l10.text = p1.name
    l11.text = p1.gamesWon.toString
    repaint()
  }

}