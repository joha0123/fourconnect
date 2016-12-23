package de.htwg.se.connectfour.model


import de.htwg.se.connectfour.model.impl.Grid

trait IGrid {
  def getPlayerName(playerID:Int):String
  def insertCoinCol(c: Int, playerID: Int): Grid
  def printout(): String
  def hasWon(): Boolean
  def isWithinGrid(col: Int): Boolean
  def height: Int
  def width: Int
}