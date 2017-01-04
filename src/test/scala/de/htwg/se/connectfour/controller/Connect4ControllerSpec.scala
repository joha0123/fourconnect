package de.htwg.se.connectfour.controller

import org.scalatest.FlatSpec
import de.htwg.se.connectfour.model.impl.Grid
import de.htwg.se.connectfour.model.impl.Player
import java.awt.Color
import de.htwg.se.connectfour.util.CommandManager

class Connect4ControllerSpec extends FlatSpec {

  behavior of "a controller"
  val p1 = new Player(1, "J", Color.RED)
  val p2 = new Player(2, "P", Color.YELLOW)
  var players = Array(p1, p2)
  var grid1 = new Grid(6, 7)
  var grid2 = new Grid(6, 7)
  val commandManager = new CommandManager()
  val controller = new Connect4Controller(grid1, commandManager)

  it should "validate  number" in {
    assert(controller.isValid(0).equals(true))
    assert(controller.isValid(-1).equals(false))
    assert(controller.isValid(15).equals(false))
  }

  it should "insert coin" in {
    controller.insertCoin(1, p1)
    grid2 = grid2.insertCoinCol(1, p1)
    assert(controller.grid.equals(grid2))
    controller.insertCoin(2, p1)
    grid2 = grid2.insertCoinCol(2, p1)
    assert(controller.grid.equals(grid2))
    controller.insertCoin(5, p2)
    grid2 = grid2.insertCoinCol(5, p2)
    assert(controller.grid.equals(grid2))

    intercept[ArrayIndexOutOfBoundsException] {
      controller.insertCoin(7, p2)
    }

  }

}