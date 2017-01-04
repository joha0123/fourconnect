package de.htwg.se.connectfour.view

import de.htwg.se.connectfour.controller._
import scala.swing._


class Gui(var controller: Connect4Controller) extends Frame {
  title = "Vier Gewinnt!"

  contents = new BorderPanel {
    add(new GamePanel(controller), BorderPanel.Position.Center)
    add(new StatusPanel(controller), BorderPanel.Position.East)
  }

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      contents += new MenuItem(Action("Restart") { controller.restart() })
      contents += new MenuItem(Action("Exit") {})
    }

    contents += new Menu("Options") {
      contents += new MenuItem(Action("Redo last move") { controller.redo })
      contents += new MenuItem(Action("Undo last move") { controller.undo })
    }
  }

  resizable = false
  visible = true

  repaint()

}