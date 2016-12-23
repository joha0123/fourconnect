package de.htwg.se.connectfour.util

import de.htwg.se.connectfour.model.IGrid


class InserCoinCommand(grid: IGrid, number: Int, playerID: Int) extends Command {
  private var prevGrid: IGrid = grid
  private var nextGrid: IGrid = null

  def execute: IGrid = { nextGrid = grid.insertCoinCol(number, playerID); return nextGrid; }
  def undo: IGrid = prevGrid

}

