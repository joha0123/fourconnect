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
  val players = Vector(p1, p2)
  var grid1 = new Grid(6, 7)
  var grid2 = new Grid(6, 7)
  grid1.setPlayers(players)
  val commandManager = new CommandManager()
  val controller = new Connect4Controller(grid1, commandManager)
  val controller2 = new Connect4Controller(grid2, commandManager)

  it should "be possible to add players" in {
    var grid5 = new Grid(6, 7)
    val controller3 = new Connect4Controller(grid5, commandManager)
    controller3.setPlayers(players)
    assert(controller3.getPlayer(0) == p1)
    assert(controller3.getPlayer(1) == p2)
  }

  it should "validate  number" in {
    assert(controller.isValid(0).equals(true))
    assert(controller.isValid(-1).equals(false))
    assert(controller.isValid(15).equals(false))
  }

  it should "insert coin" in {
    controller.insertCoin(1, p1)
    controller2.insertCoin(1, p1)
    grid2 = grid2.insertCoinCol(1, p1)
    assert(controller.grid.equals(grid2))
    controller.insertCoin(2, p1)
    controller2.insertCoin(2, p1)
    grid2 = grid2.insertCoinCol(2, p1)
    assert(controller.grid.equals(grid2))
    controller.insertCoin(5, p2)
    controller2.insertCoin(5, p2)
    grid2 = grid2.insertCoinCol(5, p2)
    assert(controller.grid.equals(grid2))
  }

  it should "draw a new grid" in {
    controller.insertCoin(1, p1)
    controller.insertCoin(1, p1)
    controller.insertCoin(1, p1)
    controller.insertCoin(1, p1)
    assert(p1.gamesWon.equals(0))

  }

  it should "get Cell" in {
    val cell = grid1.cell(0, 0)
    assert(cell.equals(controller.getCell(0, 0)))
  }

  it should "get dimensions" in {
    assert(controller.getGridHeight().equals(6))
    assert(controller.getGridWidth().equals(7))

  }

  it should "validate the input" in {
    assert(controller.isValid(3).equals(true))

  }

  it should "hand over the players" in {
    assert(controller.getActivePlayer().equals(p1))
    controller.changeActivePlayer()
  }

  it should "redo and undo command" in {
    var grid3 = controller.grid
    var grid4 = controller2.grid

    //assert(grid3.equals(grid4))
    controller.undo
    grid3 = controller.grid

    assert(!grid3.equals(grid4))
    controller.redo
    grid3 = controller.grid
    //assert(grid3.equals(grid4))
    var grid5 = new Grid(6, 7)
    var command5 = new CommandManager();
    val controller3 = new Connect4Controller(grid5, command5);

    controller3.undo
    // assert(controller3.grid.equals(grid5))
    controller3.redo
    //assert(controller3.grid.equals(grid5))

  }

  it should "restart" in {
    controller.restart()
    assert(controller.grid == new Grid(6, 7))
  }

  behavior of "a full grid"
  var grid5 = new Grid(1, 1)
  var command5 = new CommandManager();
  val controller3 = new Connect4Controller(grid5, command5);

  it should "do nothing" in {
    intercept[NoSuchElementException] {
      command5.redo
      command5.undo
    }
    controller3.insertCoin(0, p1)

    controller3.insertCoin(0, p1)

  }

}