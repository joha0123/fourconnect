package de.htwg.se.connectfour.util

import de.htwg.se.connectfour.model.IGrid
import de.htwg.se.connectfour.model.impl.Player

class InserCoinCommand(grid: IGrid, number: Int, player: Player) extends Command {
  private var prevGrid: IGrid = grid
  private var nextGrid: IGrid = null

  def execute: IGrid = { nextGrid = grid.insertCoinCol(number, player); return nextGrid; }
  def undo: IGrid = prevGrid

}

