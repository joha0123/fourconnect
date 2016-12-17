package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.model.Player
import de.htwg.se.connectfour.util.Observeable

class Connect4Controller(var grid: Grid, player: Array[Player]) extends Observeable {

  def restart() {
    //restart game
    //evtl Design Pattern?
  }

  def getDimension: String = return "[0," + grid.width + "]"

  def hasWon(): Boolean = grid.hasWon()

  def isValid(number: Int): Boolean = grid.checkInput(number)

  def insertCoin(number: Int, playerID: Int) = {
    grid = grid.insertCoinCol(number, player(playerID - 1))
    println("Player " + player(playerID - 1).name + " inserts " + number)
    notifyObservers()
  }

  def printout(): String = grid.printout()

}