package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.model.IGrid
import de.htwg.se.connectfour.model.impl.Player
import de.htwg.se.connectfour.model.impl.Cell
import de.htwg.se.connectfour.util.CommandManager
import de.htwg.se.connectfour.util.InserCoinCommand
import scala.swing.Publisher
import scala.swing.event.Event

case class GridChanged() extends Event
case class PlayerHasWon() extends Event

class Connect4Controller(var grid: IGrid, commandManager: CommandManager) extends Publisher {

  def getGridHeight(): Int = grid.height
  def getGridWidth(): Int = grid.width
  def isValid(number: Int): Boolean = grid.isWithinGrid(number)
  def getActivePlayer(): Player = grid.getActivePlayer()
  def changeActivePlayer(): Unit = grid.changeActivePlayer()
  def getCell(row: Int, col: Int): Cell = grid.cell(row, col)
  def getPlayer(index: Int): Player = grid.getPlayer(index)

  def insertCoin(col: Int, player: Player) = {
    grid = commandManager.executeCommand(new InserCoinCommand(grid, col, player))
    if (grid.hasWon(player)) { publish(new GridChanged()); publish(new PlayerHasWon()) }
    else {
      grid = grid.changeActivePlayer()
      publish(new GridChanged())
    }
  }

  def restart() {
    grid = grid.restart()
    publish(new GridChanged())
  }

  def undo: Unit = {
    if (commandManager.isUndoAvailable) {
      grid = commandManager.undo
    }
    publish(new GridChanged())

  }

  def redo: Unit = {
    if (commandManager.isRedoAvailable) {
      grid = commandManager.redo
    }
    publish(new GridChanged())
  }

}