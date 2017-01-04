package de.htwg.se.connectfour.view

import scala.swing._
import scala.swing.event._
import de.htwg.se.connectfour.controller.Connect4Controller
import de.htwg.se.connectfour.model.impl.Cell

class CellPanel(row: Int, col: Int, controller: Connect4Controller) extends Panel {

  preferredSize = new Dimension(100, 100)
  def myCell: Cell = controller.getCell(row, col)

  listenTo(mouse.clicks)

  override def paintComponent(g: Graphics2D) {
    val padding: Int = 5
    val color: Color = myCell.content match {
      case Some(coin) => coin.player.color
      case None => new Color(255, 255, 255)
    }

    g.setColor(color)
    g.fillOval(0 + padding, 0 + padding, g.getClipBounds.width.toInt - padding, g.getClipBounds.height.toInt - padding)
  }

  reactions += {
    case MouseClicked(src, pt, mod, clicks, pops) => {
      controller.insertCoin(col, controller.getActivePlayer)
    }
  }

}