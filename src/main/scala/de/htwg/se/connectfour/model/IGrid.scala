package de.htwg.se.connectfour.model

import de.htwg.se.connectfour.model.impl.Grid
import de.htwg.se.connectfour.model.impl.Cell
import de.htwg.se.connectfour.model.impl.Player

trait IGrid {
  def height(): Int
  def width(): Int
  def changeActivePlayer(): Grid
  def getActivePlayer(): Player
  def getPlayer(index: Int): Player
  def insertCoinCol(c: Int, player: Player): Grid
  def hasWon(player: Player): Boolean
  def isWithinGrid(col: Int): Boolean
  def cell(row: Int, col: Int): Cell
  def restart(): Grid
}