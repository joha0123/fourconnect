package de.htwg.se.connectfour.controller

import org.scalatest.FlatSpec
import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.model.Player
import java.awt.Color

class Connect4ControllerSpec extends FlatSpec {

  behavior of "a controller"
  val p1 = new Player(1, "J", Color.RED)
  val p2 = new Player(2, "P", Color.YELLOW)
  var players = Array(p1, p2)
  var grid1 = new Grid(6, 7);
  var grid2 = new Grid(6, 7)
  val controller = new Connect4Controller(grid1, players)

  it should "have a dimension" in {
    assert(controller.getDimension.equals("[0,7]"))
  }

  it should "check if grid have winning cells" in {
    assert(grid1.hasWon().equals(controller.hasWon()))
  }

  it should "validate  number" in {
    assert(controller.isValid(0).equals(true))
    assert(controller.isValid(-1).equals(false))
    assert(controller.isValid(15).equals(false))
  }

  it should "insert coin" in {
    controller.insertCoin(1, 1)
    grid2 = grid2.insertCoinCol(1, players(0))
    assert(controller.grid.equals(grid2))
    controller.insertCoin(2, 1)
    grid2 = grid2.insertCoinCol(2, players(0))
    assert(controller.grid.equals(grid2))
    controller.insertCoin(5, 2)
    grid2 = grid2.insertCoinCol(5, players(1))
    assert(controller.grid.equals(grid2))

    intercept[ArrayIndexOutOfBoundsException] {
      controller.insertCoin(1, 5)
    }

  }

  it should "print the grid" in {
    assert(controller.printout().equals(grid2.printout()))
  }

}